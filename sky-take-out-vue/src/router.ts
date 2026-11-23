import {createRouter, createWebHistory, type RouteRecordRaw} from 'vue-router'

// 引入页面组件
import Layout from '@/layout/index.vue'
import Login from '@/login/index.vue'
import Workbench from '@/views/workbench/index.vue'
import Data from '@/views/data/index.vue'
import Order from '@/views/order/index.vue'
import Setmeal from '@/views/setmeal/index.vue'
import Dish from '@/views/data/index.vue'
import Classify from '@/views/classify/index.vue'
import Admi from '@/views/admi/index.vue'


// 定义路由规则
// 以/开头的路径表示根路径，不以/开头就表示相对路径
const routes:RouteRecordRaw[] = [
    { 
        path:'/login',
        name:'login',
        component:Login
    },
    {
        path:'/',
        name:'layout',   
        component:Layout,
        redirect: '/workbench',
        children:[
            {path:'workbench',name:'workbench',component:Workbench},
            {path:'data',name:'data',component:Data},
            {path:'order',name:'order',component:Order},
            {path:'setmeal',name:'setmeal',component:Setmeal},
            {path:'dish',name:'dish',component:Dish},
            {path:'classify',name:'classify',component:Classify},
            {path:'admi',name:'admi',component:Admi},
        ]
    }
]

const router = createRouter({
    history:createWebHistory(), // 使用history模式（URL没有#）
    routes
})

export default router