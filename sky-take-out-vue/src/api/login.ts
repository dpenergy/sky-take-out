import request from "@/util/request" // 使用自定义的aixos实例发送异步请求
import type { LoginInfo,Result } from "@/types/login"
import { ElMessage } from "element-plus"
import router from '@/router'

export const loginApi = async (loginInfo:LoginInfo) => {
    // 这里返回的结果经过我们自定义的axios实例处理后就是我们定义的统一的Result对象
    const res:Result = await request.post('/login',loginInfo)

    console.log(res);

    if(res.code) { // 这里返回的data就是token字符串
        localStorage.setItem('token',res.data) // 存储token
        // 跳转路由
        ElMessage.success(res.msg)
        router.push('/') // 直接访问首页
    } else {
        ElMessage.error(res.msg)
    }
}