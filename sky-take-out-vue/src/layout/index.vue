<script setup lang="ts">
import {ref} from 'vue'
import { Back } from '@element-plus/icons-vue'
import { ElMessageBox,ElMessage } from 'element-plus'
import router from '@/router'

const status = ref('primary')
const promotion = ref('营业中')
const name = ref(localStorage.getItem('name'))

const change = () => {
    if(status.value == 'primary') {
        status.value = 'warning'
        promotion.value = '已下班'
    } else {
        status.value = 'primary'
        promotion.value = '营业中'
    }
}

const exit = () => {
    ElMessageBox.confirm(
    '您确定退出登录？',
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'primary',
    }
  )
    .then(() => {
      ElMessage({
        type: 'success',
        message: '已退出登录',
      })

      localStorage.removeItem('token') //退出登录，移除token
      localStorage.removeItem('name')
      router.push('/login')
    })
}

</script>
<template>
    <div class="common-layout">
        <el-container>
            <!-- header -->
            <el-header>
                <!-- 默认title是back,手动设置title就是手动设置的值 -->
                <el-page-header :icon="Back" title="退出登录" @back="exit">
                    <template #content>
                        <div class="flex items-center">
                            <span> {{ name }} </span>
                            <el-avatar :size="32"
                                src="../../public/happyeating.jpg" />
                            <span> 美美吃饭 </span>
                            <el-tag :type="status">{{ promotion }}</el-tag>
                        </div>
                    </template>
                    <template #extra>
                        <div class="flex items-center">
                            <el-button :type="status" @click="change">工作<el-icon><Switch /></el-icon>休息</el-button>
                        </div>
                    </template>
                </el-page-header>
            </el-header>
            <el-container>
                <!-- Aside -->
                <el-aside width="200px">
                    <!-- default-active设置默认的激活选项 -->
                    <el-menu router default-active="workbench">
                        <el-menu-item index="workbench">
                            <el-icon><HomeFilled /></el-icon><span>工作台</span>
                        </el-menu-item>
                        <el-menu-item index="data">
                            <el-icon><InfoFilled /></el-icon><span>数据统计</span>
                        </el-menu-item>
                        <el-menu-item index="order">
                            <el-icon><Checked /></el-icon><span>订单管理</span>
                        </el-menu-item>
                        <el-menu-item index="setMeal">
                            <el-icon><Menu /> </el-icon><span>套餐管理</span>
                        </el-menu-item>
                        <el-menu-item index="dish">
                            <el-icon><DishDot /></el-icon><span>菜品管理</span>
                        </el-menu-item>
                        <el-menu-item index="classify">
                            <el-icon><Grid /></el-icon><span>分类管理</span>
                        </el-menu-item>
                        <el-menu-item index="admi">
                            <el-icon><Avatar /></el-icon><span>员工管理</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>

                <!-- Main -->
                <el-main><RouterView /></el-main>
            </el-container>
        </el-container>
    </div>

</template>
<style scoped></style>