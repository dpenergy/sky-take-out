<script setup lang="ts">
import { addAdmiApi, deleteAdmiApi, editAdmiApi, queryAdmiList } from '@/api/admi'
import type { Admi} from '@/types/admi'
import type { Result } from '@/types/common'
import { ElMessage, ElMessageBox, type FormInstance, type TableColumnCtx } from 'element-plus'
import {ref,onMounted} from 'vue'

// ------------ search ------------
const searchName = ref('')
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
    formRef.value?.clearValidate() // 清空上次的校验信息

    theAdmi.value = {id:-1,name:'',username:'',phoneNumber:'',status:1,birthday:'',entryDate:'',updateTime:'',password:''}
    passwordDisabled.value = true
    usernameDisabled.value = false

    addOperation.value = true 

    showDialog.value = true
}

// ------------ table ------------ 
const admiList = ref<Admi[]>([])

const edit = (admi:Admi) => {
    formRef.value?.clearValidate() // 清空上次的校验信息

    theAdmi.value = admi
    passwordDisabled.value = false
    usernameDisabled.value = true

    addOperation.value = false 
    theAdmi.value = JSON.parse(JSON.stringify(admi)) // 拷贝admi对象，确保临时修改不会影响原对象

    showDialog.value = true
}
const change = async (admi:any) => {
    admi.status = admi.status ? 0 : 1
    theAdmi.value = JSON.parse(JSON.stringify(admi))

    // 自动推断类型，就不用再手动声明了
    const res = await editAdmiApi(theAdmi.value)
    if(res) {
        if(res.code) {
            ElMessage.success(res.msg)
            search()
        } else {
            ElMessage.error(res.msg)
        }
    } else {
        ElMessage.error('员工状态修改异常')
    } 
}
// 对齐Element Plus中formatter的官方签名：(row: T, column: TableColumnCtx<T>, cellValue: any, index: number) => VNode | string
// TS允许少写后面的参数（这里用不到index），参数前加_表示声明了但故意不使用
const formatTime = (_row: Admi, _column: TableColumnCtx<Admi>, cellValue: string): string => {
    // 这里可以确保cellValue是一个字符串所以可以直接使用replace这个内置的String原型链上的方法
    return cellValue ? cellValue.replace('T', ' ') : ''
}

const stringTimeFromat = (time:string) => {
    return time ? time.replace('T', ' ') : ''
}


// ------------ dialog ------------ 
// llll
const showDialog = ref(false)
const passwordDisabled = ref(true)
const usernameDisabled = ref(false)
const addOperation = ref(true)
// 添加员工时员工id设置为-1表示添加员工
const theAdmi = ref<Admi>({id:-1,name:'',username:'',phoneNumber:'',status:1,birthday:'',entryDate:'',updateTime:'',password:''})

// 表单实例引用，用于提交前手动触发整体校验
const formRef = ref<FormInstance>()

const rules = ref({
  name: [
    { required: true, message: '姓名为必填项', trigger: 'blur' },
    { pattern: /^.{1,10}$/, message: '长度必须在1-10之间', trigger: 'blur' },
  ],
  username: [
    { required: true, message: '账号为必填项', trigger: 'blur' },
    { pattern: /^[a-zA-Z0-9]{1,50}$/, message: '账号只能由英文和数字组成，长度为1-50', trigger: 'blur' },
  ],
  phoneNumber: [
    { required: true, message: '手机号为必填项', trigger: 'blur' },
    { pattern: /^\d{11}$/, message: '手机号必须为11位数字', trigger: 'blur' },
  ]
})

const cancel = () => {
    showDialog.value = false
}
// confirm方法需要处理新正员工和修改员工信息两个操作
const confirm = async () => {
    // validate()校验通过时resolve为true，不通过时会reject，这里用catch兜底成false，不通过就中止提交
    const valid = await formRef.value?.validate().catch(() => false)
    if (!valid) return

    let res:Result|undefined
    if(addOperation.value) { // 新增员工
        res = await addAdmiApi(theAdmi.value)
        if(res) {
            if(res.code) {
                ElMessage.success(res.msg)
                search()
            } else {
                ElMessage.error(res.msg)
            }
        } else {
            ElMessage.error('添加员工操作异常')
        } 
    } else { // 修改员工
        res = await editAdmiApi(theAdmi.value)
        if(res) {
            if(res.code) {
                ElMessage.success(res.msg)
                search()
            } else {
                ElMessage.error(res.msg)
            }
        } else {
            ElMessage.error('编辑员工操作异常')
        } 
    }
    
    showDialog.value = false
}
const deleteAdmi = async (id:number) => {
    ElMessageBox.confirm(
    '您确删除'+theAdmi.value.name+'吗?',
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'primary',
    }
  )
    .then(async () => {
        const res = await deleteAdmiApi(id)
        if(res) {
            if(res.code) {
                ElMessage.success(res.msg)
                search()
            } else {
                ElMessage.error(res.msg)
            }
        } else {
            ElMessage.error('删除员工操作异常')
        } 
    })
    
    showDialog.value = false
}




// ------------ pagination ------------ 
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
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
    <!-- {{ theAdmi }} -->
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
                <span :class="scope.row.status ? 'green':'red'">{{ scope.row.status ? '✔启用' : '×禁用' }}</span>
            </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="最后操作时间" width="180" :formatter="formatTime" align="center"/>
        <el-table-column label="操作" align="center">
            <template #default="scope">
                <el-button type="primary" :disabled="scope.row.id == 1 ? true : false" size="small" @click="edit(scope.row)">修改</el-button>
                <el-button type="danger" :disabled="scope.row.id == 1 ? true : false" size="small" @click="change(scope.row)">
                    {{ scope.row.status ? '禁用' : '启用'}}
                </el-button>
            </template>
        </el-table-column>
    </el-table>

    <!-- 对话框 -->
    <el-dialog v-model="showDialog" title="员工详情" width="500">
        <el-form ref="formRef" :model="theAdmi" :rules="rules">
            
            <el-form-item prop="name" label="员工姓名:">
                <!-- disabled属性可以设置为禁用 -->
                <el-input v-model="theAdmi.name" placeholder="请输入您的姓名" autocomplete="off"/>
            </el-form-item>
            <el-form-item prop="username" label="账号:">
                <el-input v-model="theAdmi.username" placeholder="请输用户名(不带中文或符号)" autocomplete="off" :disabled="usernameDisabled"/>
            </el-form-item>
            <el-form-item label="密码:">
                <el-input v-model="theAdmi.password" placeholder="初始默认123456，稍后可自行修改" autocomplete="off" :disabled="passwordDisabled"/>
            </el-form-item>
            <el-form-item v-if="theAdmi.id != -1" label="账号状态:">
                <el-select v-model="theAdmi.status" placeholder="选择状态">
                <el-option label="启用" :value="1" />
                <el-option label="禁用" :value="0" />
                </el-select>
            </el-form-item>
            <el-form-item prop="phoneNumber" label="手机号:">
                <el-input v-model="theAdmi.phoneNumber" placeholder="请输入您的手机号" autocomplete="off" />
            </el-form-item>
            <el-form-item label="出生日期:">
                <!-- 使用值格式value-format可以约束值的格式 -->
                <el-date-picker v-model="theAdmi.birthday" value-format="YYYY-MM-DD" type="date" placeholder="选择您的出生日期"/>
            </el-form-item>
            <el-form-item v-if="theAdmi.id != -1" label="员工id:">
                <!-- disabled属性可以设置为禁用 -->
                <span>{{ theAdmi.id }}</span>
                <!-- <el-input v-model="theAdmi.id" autocomplete="off" :disabled="true"/> -->
            </el-form-item>
            <el-form-item v-if="theAdmi.id != -1" label="入职日期:">
                <span>{{ theAdmi.entryDate }}</span>
            </el-form-item>
            <el-form-item v-if="theAdmi.id != -1" label="最后操作时间:">
                <span>{{ stringTimeFromat(theAdmi.updateTime) }}</span>
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="cancel" >取消</el-button>
                <el-button type="primary" @click="confirm">{{ addOperation ? '确认添加' : '确认修改' }}</el-button>
                <el-button type="danger" v-if="theAdmi.id != -1" @click="deleteAdmi(theAdmi.id)">删除员工</el-button>
            </div>
        </template>
    </el-dialog>

    <!-- 分页条 -->
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[5, 10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

</template>
<style scoped>
/* 表现、状态栏的字体颜色 */
.green {
    color: #67c23a;
}

.orange {
    color: #e6a23c;
}

.red {
    color: #f56c6c;
}

.gray {
    color: #909399;
}
</style>