import request from "@/util/request" // 使用自定义的aixos实例发送异步请求
import type { LoginInfo } from "@/types/login"
import type { Result } from "@/types/common"
import { ElMessage } from "element-plus"
import router from '@/router'

// 异步方法返回值有严格的限制，必须是Promise<T>类型，我们明显需要api方法返回具体数据而不是Promise<T>所以我们必须在外包装异步同步化
export const loginApi = async (loginInfo:LoginInfo) => {
    // 这里返回的结果经过我们自定义的axios实例处理后就是我们定义的统一的Result对象
    const res:Result = await request.post('/admi/login',loginInfo)

    console.log(res);

    if(res.code) { // 这里返回的data就是token字符串
        localStorage.setItem('token',res.data.token) // 存储token
        // 跳转路由
        localStorage.setItem('name',res.data.name)
        ElMessage.success(res.msg)
        router.push('/') // 直接访问首页
    } else {
        ElMessage.error(res.msg)
    }
}

// export const testLoginApi = (loginInfo:LoginInfo) => request.post('/admi/login',loginInfo)