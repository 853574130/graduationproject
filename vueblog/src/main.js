import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import { Message } from 'element-ui';
import axios from 'axios';
// import 'element-ui/lib/theme-chalk/index.css'
import './styles/element-variables.scss'
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
// import 'vue-area-linkage/dist/index.css';
import 'font-awesome/css/font-awesome.min.css'
import '../static/css/main.css'
// import API_ROOT from'../static/config.js'
import store from "./components/Visitor/store/store.js"
import global_ from "./utils/global"
axios.defaults.withCredentials = true; //这个是和跨域有关的
Vue.use(ElementUI)

Vue.prototype.$appName = 'MyBlog'
    /* 有关axios
    Vue.prototype.$path = API_ROOT.baseURL;
    Vue.prototype.$axios = axios;
    var axios_instance = axios.create({
        baseURL: API_ROOT.baseURL,
        transformRequest: [function (data) {
            data = Qs.stringify(data);
            return data;
        }],
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        withCredentials: true
    });

    axios_instance.interceptors.response.use(res => {
        if (res.data.code == 1002) {
            localStorage.removeItem('ms_username');
            router.push('/login');
        } else if (res.data.code == 1001) {
            Message.error(res.data.message);
        } else if (res.data.code == 1000) {
            return res;
        }
    }, error => {
        console.log(error.response);
        Message.error('请求失败');
    })
    */
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