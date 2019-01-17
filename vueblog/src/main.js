import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
import './styles/element-variables.scss'
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
// import 'vue-area-linkage/dist/index.css';
import 'font-awesome/css/font-awesome.min.css'
import '../static/css/main.css'
import store from "./components/Visitor/store/store.js"
import global_ from "./utils/global"
Vue.use(ElementUI)
Vue.prototype.$appName = 'MyBlog'
// Vue.prototype.$path = API_ROOT.baseURL;
// Vue.prototype.GLOBAL = global_;
// 引用axios，并设置基础URL为后端服务api地址
var axios = require('axios')
// axios.defaults.baseURL = 'http://localhost:8089/blogsever'
// 将API方法绑定到全局
Vue.prototype.$axios = axios
// Vue.config.productionTip = false
var a = new Vue({
    el: '#app',
    router,
    template: '<App/>',
    components: { App }
})
// console.log("this1", a);
