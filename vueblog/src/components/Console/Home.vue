<template>
  <el-container class="home_container">
    <el-header>
      <div class="home_title">management</div>
      <!-- 小帅的博客管理平台 -->
      <div class="header-right">
      <div class="header-user-con">
         <!-- 全屏显示 -->
                <!-- <div class="btn-fullscreen" @click="handleFullScreen">
                    <el-tooltip effect="dark" :content="fullscreen?`取消全屏`:`全屏`" placement="bottom">
                        <i class="el-icon-rank"></i>
                    </el-tooltip>
                </div> -->
                 <!-- 消息中心 -->
         <div class="btn-bell">
           
                    <el-tooltip effect="dark" :content="message?`有${message}条未读消息`:`消息中心`" placement="bottom">
                        <router-link to="/tabs">
                            <i class="el-icon-bell"></i>
                        </router-link>
                    </el-tooltip>

                    <span class="btn-bell-badge" v-if="message"></span>
                </div>
        <!-- 用户头像 -->
                <div class="user-avator"><img src="../../../static/img/img2.jpg"></div>
        <!-- 用户名下拉菜单 -->
        <el-dropdown @command="handleCommand">
  <span class="el-dropdown-link home_userinfo">
    {{currentUserName}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
  </span>
    <!-- <el-dropdown-menu slot="dropdown">
      <el-dropdown-item command="login">登录</el-dropdown-item>
      </el-dropdown> -->
          <el-dropdown-menu slot="dropdown">
            <!-- <el-dropdown-item command="sysMsg">系统消息</el-dropdown-item>
            <el-dropdown-item command="MyArticle">我的文章</el-dropdown-item>
            <el-dropdown-item command="MyHome">个人主页</el-dropdown-item> -->
            <!-- 这个应该另外放个地方 -->

            <!-- 这里要判断一下 如果是未登录状态，这里显示的就应该是登录按钮 -->
            <el-dropdown-item command="login">登录</el-dropdown-item>
            <el-dropdown-item command="editpersonalinfo">个人信息</el-dropdown-item>
            <el-dropdown-item command="editpassword">修改密码</el-dropdown-item>
            <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      </div>

    </el-header>
    <!--                                 header                              -->
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="0"
          class="el-menu-vertical-demo" style="background-color: #E0D0EC" router>
          <template v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden">

            <el-submenu :index="index+''" v-if="item.children.length>1" :key="index">
              <template slot="title">
                <i :class="item.iconCls"></i>
                <span>{{item.name}}</span>
              </template>
              
              <el-menu-item v-for="child in item.children" v-if="!child.hidden" :index="child.path" :key="child.path">
                {{child.name}}
              </el-menu-item>
            </el-submenu>

            
            <template v-else>
              <el-menu-item :index="item.children[0].path">
                <i :class="item.children[0].iconCls"></i>
                <span slot="title">{{item.children[0].name}}</span>
              </el-menu-item>
            </template>

          </template>

          
        </el-menu>
      </el-aside>
         <!--                                 aside                              -->
      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <keep-alive>
            <router-view v-if="this.$route.meta.keepAlive"></router-view>
          </keep-alive>
          <router-view v-if="!this.$route.meta.keepAlive"></router-view>
         
        </el-main>
 <!--                                 main                              -->


      </el-container>
    </el-container>
  </el-container>
</template>
<script>
  import {getRequest} from '../../utils/api'
  export default{
    methods: {
      handleCommand(command){
        var _this = this;
        if (command == 'logout') {
          this.$confirm('注销登录吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function () {
            getRequest("/logout")
            _this.currentUserName = '游客';
            _this.$router.replace({path: '/index'});
          }, function () {
            //取消
          })
        }
        else if(command == 'editpersonalinfo'){
          this.$router.push('/userinfo');

        }
        else if(command == 'editpassword'){
          this.$router.push('/editpwd');
        }
         else if(command == 'login'){
          this.$router.push('/login');
        }
        
      },
       // 全屏事件
            handleFullScreen(){
                let element = document.documentElement;
                if (this.fullscreen) {
                    if (document.exitFullscreen) {
                        document.exitFullscreen();
                    } else if (document.webkitCancelFullScreen) {
                        document.webkitCancelFullScreen();
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                    } else if (document.msExitFullscreen) {
                        document.msExitFullscreen();
                    }
                } else {
                    if (element.requestFullscreen) {
                        element.requestFullscreen();
                    } else if (element.webkitRequestFullScreen) {
                        element.webkitRequestFullScreen();
                    } else if (element.mozRequestFullScreen) {
                        element.mozRequestFullScreen();
                    } else if (element.msRequestFullscreen) {
                        // IE11
                        element.msRequestFullscreen();
                    }
                }
                this.fullscreen = !this.fullscreen;
            }
    },
    created(){
      // console.log("this.$router.options.routes",this.$router.options.routes);
    },
    mounted: function () {
      // this.$alert('为了确保所有的小伙伴都能看到完整的数据演示，数据库只开放了查询权限和部分字段的更新权限，其他权限都不具备，完整权限的演示需要大家在自己本地部署后，换一个正常的数据库用户后即可查看，这点请大家悉知!', '友情提示', {
      //   confirmButtonText: '确定',
      //   callback: action => {
      //   }
      // });
      var _this = this;
      getRequest("/currentUserName").then(function (msg) {
        _this.currentUserName = msg.data;
      }, function (msg) {
        _this.currentUserName = '游客';
      });
    },
    data(){
      return {
        fullscreen: false,
        message: 2,
        currentUserName: ''
      }
    }
  }
</script>
<style>
  .home_container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    
  }

  .el-header {
    background-color: #070c0f;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
    
  }
   .header-right{
        float: right;
        padding-right: 50px;
        
    }

  .el-aside {
    background-color: #ECECEC;
  }

  .el-main {
    background-color: #fff;
    color: #000;
    text-align: center;
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }
  .user-avator{
        margin-left: 20px;
    }
    .user-avator img{
        display: block;
        width:40px;
        height:40px;
        border-radius: 50%;
    }
    .btn-bell, .btn-fullscreen{
      font-size:22px;
        position: relative;
        width: 30px;
        height: 30px;
        text-align: center;
        border-radius: 15px;
        cursor: pointer;
    }

  .btn-fullscreen{
        transform: rotate(45deg);
        margin-right: 5px;
        font-size: 24px;
    }
   .header-user-con{
     
        display: flex;
        height: 70px;
        align-items: center;
    }
     .btn-bell .el-icon-bell{
        color: #fff;
    }
    .btn-bell-badge{
        position: absolute;
        right: 0;
        top: -2px;
        width: 8px;
        height: 8px;
        border-radius: 4px;
        background: #f56c6c;
        color: #fff;
    }
</style>
