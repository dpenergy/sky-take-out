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
        name:'Login',
        component:Login
    },
    {
        path:'/',
        name:'Layout',
        component:Layout,
        redirect: '/workbench',
        children:[
            {path:'workbench',name:'Workbench',component:Workbench},
            {path:'data',name:'Data',component:Data},
            {path:'order',name:'Order',component:Order},
            {path:'setmeal',name:'Setmeal',component:Setmeal},
            {path:'dish',name:'Dish',component:Dish},
            {path:'classify',name:'Classify',component:Classify},
            {path:'admi',name:'Admi',component:Admi},
        ]
    }
]

const router = createRouter({
    history:createWebHistory(), // 使用history模式（URL没有#）
    routes
})

export default router