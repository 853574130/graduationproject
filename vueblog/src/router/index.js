import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Console/Login'
import Home from '@/components/Console/Home'
import ArticleList from '@/components/Console/Article/ArticleList'
import CateMana from '@/components/Console/CateMana'
// import DataCharts from '@/components/Console/dataStatistics/DataCharts'
import DataCharts from '../components/Console/dataStatistics/DataCharts'
import dashboard from '../components/Console/dataStatistics/dashboard'
import Attach from '../components/Console/Attach'
// import dashboard from '../components/Console/dashboard'
import PostArticle from '@/components/Console/Article/PostArticle'
import UserMana from '@/components/Console/usermanager/UserMana'
import UserInfo from '@/components/Console/UserInfo'
import editpwd from '@/components/Console/editpassword'
import Main from '@/components/Visitor/Main'
import BlogSetting from '../components/Console/blogSetting/BlogSetting'
import stylesetting from '../components/Console/blogSetting/stylesetting'
import blog from '../components/Visitor/page/blog'
import index from '../components/Visitor/page/index'
import main from '../components/Visitor/Main'

Vue.use(Router)

export default new Router({
    routes: [{
        path: '/',
        redirect: '/login'
    },

    {
        path: '*',
        redirect: '/404'

    },
    {
        path: "/404",
        component: resolve => require(["../common/404.vue"], resolve)
    },
    {
        path: '/login',
        // name: '登录',
        // hidden: true,
        component: Login
    },
    {
        path: "/",
        component: resolve => require(["../components/Console/Home.vue"], resolve),
        meta: {
            title: "自述文件"
        },
        children: [{
            path: "/dashboard",
            component: resolve =>
                require(["../components/Console/dataStatistics/dashboard"], resolve),
            meta: {
                title: "系统首页"
            }
        },
        {
            path: '/articleList',
            // component: ArticleList,
            component: resolve => require(["../components/Console/Article/ArticleList"], resolve),
            meta: {
                keepAlive: true,
                name: '文章列表',
            }
        },
        {
            path: '/postArticle',
            component: resolve => require(["../components/Console/Article/PostArticle"], resolve),
            meta: {
                keepAlive: true,
                name: '文章列表',
            }
        },
        {
            path: '/postArticle',
            name: '发表文章',
            component: PostArticle,
            meta: {
                keepAlive: false
            }
        }, {
            path: '/blogDetail',
            name: '博客详情',
            component: resolve => require(["../components/Console/Article/BlogDetail"], resolve),
            hidden: true,
            meta: {
                keepAlive: false
            }
        }, {
            path: '/editBlog',
            name: '编辑博客',
            component: PostArticle,
            hidden: true,
            meta: {
                keepAlive: false
            }
        }
        ]
    },


        // , {
        //         path: '/postArticle',
        //         name: '发表文章',
        //         component: PostArticle,
        //         meta: {
        //             keepAlive: false
        //         }
        //     }, {
        //         path: '/blogDetail',
        //         name: '博客详情',
        //         component: BlogDetail,
        //         hidden: true,
        //         meta: {
        //             keepAlive: false
        //         }
        //     }, {
        //         path: '/editBlog',
        //         name: '编辑博客',
        //         component: PostArticle,
        //         hidden: true,
        //         meta: {
        //             keepAlive: false
        //         }
        //     }
        // ]
        // },
        // {
        //     path: '/home',
        //     component: Home,
        //     name: '附件管理',
        //     children: [{
        //         path: '/attach',
        //         iconCls: 'el-icon-picture',
        //         name: '附件管理',
        //         // component: attachmanage
        //         component: Attach
        //     }]
        // },

        // {
        //     path: '/home',
        //     component: Home,
        //     name: '用户管理',
        //     children: [{
        //         path: '/user',
        //         iconCls: 'fa fa-user-o',
        //         name: '用户管理',
        //         component: UserMana
        //     }]
        // },
        // {
        //     path: '/home',
        //     component: Home,
        //     name: '栏目管理',
        //     children: [{
        //         path: '/cateMana',
        //         iconCls: 'fa fa-reorder',
        //         name: '栏目管理',
        //         component: CateMana
        //     }]
        // },
        // {
        //     path: '/home',
        //     component: Home,
        //     name: '博客设置',
        //     iconCls: 'fa fa-bar-chart',
        //     children: [{
        //         path: '/blogsetting',
        //         iconCls: 'el-icon-setting',
        //         name: '博客设置',
        //         component: BlogSetting
        //     }]
        // },
        // {
        //     path: '/home',
        //     component: Home,
        //     hidden: true,
        //     children: [{
        //         path: '/userinfo',
        //         component: UserInfo

        //     }]
        // },
        // {
        //     path: '/home',
        //     component: Home,
        //     hidden: true,
        //     children: [{
        //         path: '/editpwd',
        //         component: editpwd

        //     }]
        // },
        // {
        //     path: '/home',
        //     component: Home,
        //     hidden: true,
        //     children: [{
        //         path: '/stylesetting',
        //         component: stylesetting

        //     }]
        // },
        // // -------------------下面的是游客的路由---------------------------
        // {
        //     path: '/main',
        //     component: main,
        //     children: [{
        //         path: '/about',
        //         component: (resolve) => {
        //             require(['../components/Visitor/page/about.vue'], resolve);
        //         }
        //     }],
        //     hidden: true,
        // },
        // {
        //     path: '/main',
        //     component: main,
        //     children: [{
        //         path: '/index',
        //         component: (resolve) => {
        //             require(['../components/Visitor/page/index.vue'], resolve);
        //         }
        //     }],
        //     hidden: true,
        // },
        // {
        //     path: '/main',
        //     component: main,
        //     children: [{
        //         path: '/blog',
        //         component: blog,
        //     }],
        //     hidden: true,
        // },
        // {
        //     path: '/main',
        //     component: main,
        //     children: [{
        //         path: '/detail/:id',
        //         name: 'detail',
        //         component: (resolve) => {
        //             require(["../components/Visitor/page/detail.vue"], resolve);
        //         }
        //     }],
        //     hidden: true,

        // },
        // {
        //     path: '/shot',
        //     name: 'shot',
        //     component: (resolve) => {
        //         require(['../components/Visitor/page/shot.vue'], resolve);
        //     }
        // },
        // {
        //     path: '/tools',
        //     name: 'tools',
        //     component: (resolve) => {
        //         require(['../components/Visitor/page/tools.vue'], resolve);
        //     }
        // },

        //这个播放器暂时用不到的话就先不写了，避免浪费时间解决很多小问题
        // {    
        //     path: '/freevideo',
        //     name: 'freevideo',
        //     component: (resolve) => {
        //         require(['../components/Visitor/page/freevideo.vue'], resolve);
        //     }
        // },
    ]
})