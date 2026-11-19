import {createRouter, createWebHistory, type RouteRecordRaw} from 'vue-router'

// 引入页面组件
import Layout from '@/layout/index.vue'
import Login from '@/login/index.vue'
import Workbench from '@/views/workbench/index.vue'
import Admi from '@/views/admi/index.vue'
import Customer from '@/views/customer/index.vue'
import Order from '@/views/order/index.vue'

// 定义路由规则
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
            {path:'/workbench',name:'Workbench',component:Workbench},
            {path:'/customer',name:'Customer',component:Customer},
            {path:'/admi',name:'Admi',component:Admi},
            {path:'/order',name:'Order',component:Order}
        ]
    }
]

const router = createRouter({
    history:createWebHistory(), // 使用history模式（URL没有#）
    routes
})

export default router