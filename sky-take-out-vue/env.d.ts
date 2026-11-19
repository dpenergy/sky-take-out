/// <reference types="vite/client" />

// vuex 4.1.0 的 package.json exports 未映射 types 条件，
// 在 bundler 解析模式下 TS 找不到其类型声明，此处手动桥接
declare module 'vuex' {
  export * from 'vuex/types/index.d.ts'
}
