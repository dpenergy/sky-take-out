import type { Result } from "@/types/common"
import type { AdmiSearchResult } from "@/types/admi"
import request from "@/util/request" // 使用自定义的aixos实例发送异步请求
import { ElMessage } from "element-plus"

// 异步函数如果没有显示声明返回值类型就会默认为Promise<any>类型
// 成功时返回Admi[]，失败时弹出错误提示并返回undefined
// export const queryAdmiListApi = async (searchName:string): Promise<Admi[] | undefined> => {
//     const res:Result = await request.get(`/admi/list?searchName=${searchName}`)
//     if(res.code) {
//         return res.data // 如果成功返回的Promise内部的数据对象类型就是Admi[]
//     } else {
//         ElMessage.error(res.msg) // 如果失败返回的Promise内部没有对象就是undefined
//     }
// }

export const queryAdmiList = async (searchName:string,pageSize:number,currentPage:number): Promise<AdmiSearchResult | undefined> => {
    const res:Result = await request.get(`/admi/list?searchName=${searchName}&pageSize=${pageSize}&currentPage=${currentPage}`)
    if(res.code) {
        return res.data // 如果成功返回的Promise内部的数据对象类型就是Admi[]
    } else {
        ElMessage.error(res.msg) // 如果失败返回的Promise内部没有对象就是undefined
    }
}


