export interface Admi {
    id: number
    name: string
    username: string
    password: string
    status: number
    phoneNumber: string
    updateTime: string 
    birthday: string
    entryDate: string // 后端服务器返回的时间结果经过JSON序列化后就是字符串了，所以直接使用字符串接收就行
}

export interface AdmiSearchResult {
    total:number
    admiList:Admi[]
}