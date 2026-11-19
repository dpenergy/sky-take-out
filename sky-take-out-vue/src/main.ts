import { createApp } from 'vue'
import App from './App.vue'
import Router from '@/router.ts'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import Store from '@/store/index.ts'

const app = createApp(App)
app.use(Router)
app.use(ElementPlus)
app.use(Store)


app.mount('#app')