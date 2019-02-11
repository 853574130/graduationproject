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

//注册全局过滤器
Vue.filter("formatDate", function formatDate(value) {
    var date = new Date(value);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    return year + "-" + month + "-" + day;
});
Vue.filter("formatDateTime", function formatDateTime(value) {
    var date = new Date(value);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    return year + "-" + month + "-" + day + " " + hours + ":" + minutes;
});
var a = new Vue({
    el: '#app',
    router,
    template: '<App/>',
    components: { App }
})
// console.log("this1", a);
