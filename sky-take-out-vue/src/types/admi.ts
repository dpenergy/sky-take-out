export interface Admi {
    id: number
    username: string
    password: string
    status: number
    name: string
    age: number
    phoneNumber: string
    // entryDate: Date
    // updateTime: any // 没有DateTime这种精确到毫秒的类型，所以就用any类型来接收
    entryDate: string // 后端服务器返回的时间结果经过JSON序列化后就是字符串了，所以直接使用字符串接收就行
    updateTime: string 

    // updateTime: 
    // TS中如何对接Java中的LocalDateTime时间类

    // 梳理一下晚上需要做的事情
    // 1. 完成约束定义
    // 2. 实现后端的分页查询
    // 3. 实现前端数据处理和展示
    // 4. 前端交互有效化（发送到后端能真正改变数据）
}

export interface AdmiSearchResult {
    total:number
    admiList:Admi[]
}