// import vue from 'vue/dist/vue.esm.browser'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// bpmn 相关依赖
import 'bpmn-js/dist/assets/diagram-js.css'
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn.css'
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-codes.css'
import 'bpmn-js/dist/assets/bpmn-font/css/bpmn-embedded.css'
// 左边工具栏以及编辑节点的样式
import 'bpmn-js-properties-panel/dist/assets/bpmn-js-properties-panel.css'
export default ({
  Vue, // VuePress 正在使用的 Vue 构造函数
  options, // 附加到根实例的一些选项
  router, // 当前应用的路由实例
  siteData // 站点元数据
}) => {
  // if (!isServer) {
  //   import('vue-toasted' /* webpackChunkName: "notification" */).then((module) => {
  //     Vue.use(module.default)
  //   })
  // }
  Vue.use(Element)
  // window.Vue = vue // 使页面中可以使用Vue构造函数 （使页面中的vue demo生效）
}
