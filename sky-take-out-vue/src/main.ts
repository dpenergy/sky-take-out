import { createApp } from 'vue'
import App from './App.vue'
import Router from '@/router.ts'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import Store from '@/store/index.ts'
import * as ElementPlusIconsVue from '@element-plus/icons-vue' // 引入所有element-plus图标

const app = createApp(App)
app.use(Router)
app.use(ElementPlus)
app.use(Store)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) { // 注册所有elment-plus图标
  app.component(key, component)
}


app.mount('#app')