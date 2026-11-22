<script setup lang="ts">
import {ref} from 'vue'
import { loginApi } from '@/api/login'
import type { LoginInfo } from '@/types/login'

const loginInfo = ref<LoginInfo>({username:'',password:''})

const login = () => {
    // 自动拼接为一个字符串
    loginApi(loginInfo.value)
    console.log('发送登录请求:',loginInfo.value)
}

// const testLogin = async () => {
//     // 自动拼接为一个字符串
//     const res:Result = await testLoginApi(loginInfo.value)
//     console.log('发送登录请求:',loginInfo.value)
// }
</script>
<template>
    {{ loginInfo.username }}
    <br>
    {{ loginInfo.password }}
    <div>login组件</div>
    <el-input v-model="loginInfo.username" placeholder="请输入用户名"/>
    <el-input v-model="loginInfo.password" placeholder="请输入密码"/>
    <el-button type="primary" @click="login">登录</el-button>
</template>
<style scoped>
</style>