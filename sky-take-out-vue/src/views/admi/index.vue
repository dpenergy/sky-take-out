<script setup lang="ts">
import { queryAdmiList } from '@/api/admi'
import type { Admi} from '@/types/admi'
import type { TableColumnCtx } from 'element-plus'
import {ref,onMounted} from 'vue'

// ------------ search ------------
const searchName = ref('')
// const search = async () => {
//     // searchApi是async函数，直接调用拿到的是Promise，必须await等待后端响应后才能拿到真正的Admi[]
//     // 查询失败时searchApi内部已经弹出错误提示并返回undefined，用??兜底成空数组
//     const data = await queryAdmiListApi(searchName.value) // 这里应该是自动推断类型
//     admiList.value = data ?? [] // ??是空值合并运算符如果左侧是null或undefined就返回右侧值，反之返回左侧值（三目运算符的简化版本）
// }
const search = async () => {
    const data = await queryAdmiList(searchName.value,pageSize.value,currentPage.value) // 这里应该是自动推断类型
    total.value = data ? data.total : 0
    admiList.value = data ? data.admiList : [] // ??是空值合并运算符如果左侧是null或undefined就返回右侧值，反之返回左侧值（三目运算符的简化版本）
}
const clear = () => {
    searchName.value = ''
    search()
}
const add = () => {
    showDialog.value = true
}

// ------------ table ------------ 
const admiList = ref<Admi[]>([])

const edit = () => {
    showDialog.value = true
}
const change = (admi:any) => {
    admi.status = admi.status ? 0 : 1
}
// 对齐Element Plus中formatter的官方签名：(row: T, column: TableColumnCtx<T>, cellValue: any, index: number) => VNode | string
// TS允许少写后面的参数（这里用不到index），参数前加_表示声明了但故意不使用
const formatTime = (_row: Admi, _column: TableColumnCtx<Admi>, cellValue: string): string => {
    // 这里可以确保cellValue是一个字符串所以可以直接使用replace这个内置的String原型链上的方法
    return cellValue ? cellValue.replace('T', ' ') : ''
}


// ------------ dialog ------------ 
const showDialog = ref(false)
const theAdmi = ref({id:1,name:'超级管理员',username:'admin',phoneNumber:'11111111111',status:1,updateTime:'2026-11-08 20:17:54'})

const cancel = () => {
    showDialog.value = false
}
const confirm = () => {
    showDialog.value = false
}

// ------------ pagination ------------ 
const currentPage = ref(1)
const pageSize = ref(5)
const total = ref(10)
const handleSizeChange = () => {
    search()
}
const handleCurrentChange = () => {
    search()
}
 

// ------------ 钩子函数 ------------ 
onMounted(() => {
    search()
})

</script>
<template>
    <!-- 搜索栏 -->
    <el-form :inline="true">
        <el-form-item label="员工姓名">
        <el-input v-model="searchName" placeholder="请输入员工姓名"/>
        </el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
        <el-button type="danger" @click="clear">清空</el-button>
        <el-button type="primary" @click="add">+添加员工</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table :data="admiList" stripe style="width: 100%">
        <el-table-column prop="name" label="员工姓名" width="180" align="center"/>
        <el-table-column prop="username" label="账号" width="180" align="center"/>
        <el-table-column prop="phoneNumber" label="手机号" width="180" align="center"/>
        <el-table-column label="账号状态" width="180" align="center">
            <template #default="scope">
                {{ scope.row.status ? '启用' : '禁用' }}
            </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="最后操作时间" width="180" :formatter="formatTime" align="center"/>
        <el-table-column label="操作" align="center">
            <template #default="scope">
                <el-button type="primary" size="small" @click="edit">修改</el-button>
                <el-button type="danger" size="small" @click="change(scope.row)">
                    {{ scope.row.status ? '禁用' : '启用'}}
                </el-button>
            </template>
        </el-table-column>
    </el-table>

    <!-- 对话框 -->
    <el-dialog v-model="showDialog" title="员工详情" width="500">
        <el-form :model="theAdmi">
            <el-form-item label="员工姓名">
                <el-input v-model="theAdmi.name" autocomplete="off" />
            </el-form-item>
            <el-form-item label="账号">
                <el-input v-model="theAdmi.username" autocomplete="off" />
            </el-form-item>
            <el-form-item label="手机号">
                <el-input v-model="theAdmi.phoneNumber" autocomplete="off" />
            </el-form-item>
            <el-form-item label="账号状态">
                <el-select v-model="theAdmi.status" placeholder="选择状态">
                <el-option label="启用" :value="1" />
                <el-option label="禁用" :value="0" />
                </el-select>
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="cancel">取消</el-button>
                <el-button type="primary" @click="confirm">确认</el-button>
            </div>
        </template>
    </el-dialog>

    <!-- 分页条 -->
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[5, 10, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

</template>
<style scoped>
</style>