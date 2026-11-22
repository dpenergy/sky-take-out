import axios from "axios"
import { ElMessage } from "element-plus";
import router from '@/router'
// 自定义axios实例，然后尝试使用axios进行前后端连通测试
// 然后进行请求拦截和响应拦截处理
// 处理token之类的

const request = axios.create({
    baseURL: 'api',
    // baseURL: 'http://localhost:8080',
    timeout: 600
})

// 设置请求拦截器，给请求添加token令牌
request.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token')
        if(token) {
            config.headers.token = token
        }

        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)

// 设置响应拦截器，预处理响应结果
request.interceptors.response.use(
    (response) => { // 成功回调：THHP状态码为【200，299】
        // 后端正常的返回Result(无论是成功的还是失败的Result,http状态码都是200)
        // 在业务逻辑里面data.code自行判断是否成功获取结果，然后进行处理
        return response.data
    },
    (error) => { // 失败回调：没有响应结果/有响应结果但是THHP状态码不在成功范围内
        // 后端设置了响应状态返回401未登录，这里只处理部分失败结果
        if(error.response?.status == 401) {
            const msg = error.response.data?.message || '请重新登录'
            ElMessage.error(msg)
            localStorage.removeItem('admi') // 移除本地存储的Token信息
            // 跳转到登录界面
            router.push('/login')
        }

        return Promise.reject(error)
    }
)


// 写了这行外界才可以使用import导入request实例来使用
// 如果不写这行，request 就只是 request.js 文件内部的私有变量，外部完全无法访问，整个封装就失去了意义
// export default是一个组合语法，一个js/ts文件只能有一个export default
export default request