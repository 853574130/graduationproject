<template>
  <div class="wrapper">
    <vHead></vHead>
    <vSidebar></vSidebar>
    <div class="content-box">
      <!-- :class="{'content-collapse':collapse}" -->
      <div class="content">
        <transition name="move" mode="out-in">
          <keep-alive>
            <router-view></router-view>
          </keep-alive>
        </transition>
      </div>
    </div>
  </div>
</template>
<script>
  import {getRequest} from '../../utils/api'
  import vSidebar from '../../common/Sidebar.vue';
  import vHead from '../../common/Header.vue';
    import bus from '../../common/bus';
  export default{
    methods: {
      // handleCommand(command){
      //   var _this = this;
      //   if (command == 'logout') {
      //     this.$confirm('注销登录吗?', '提示', {
      //       confirmButtonText: '确定',
      //       cancelButtonText: '取消',
      //       type: 'warning'
      //     }).then(function () {
      //       getRequest("/logout")
      //       _this.currentUserName = '游客';
      //       _this.$router.replace({path: '/index'});
      //     }, function () {
      //       //取消
      //     })
      //   }
      //   else if(command == 'editpersonalinfo'){
      //     this.$router.push('/userinfo');

      //   }
      //   else if(command == 'editpassword'){
      //     this.$router.push('/editpwd');
      //   }
      //    else if(command == 'login'){
      //     this.$router.push('/login');
      //   }
        
      // },
      //  // 全屏事件
      //       handleFullScreen(){
      //           let element = document.documentElement;
      //           if (this.fullscreen) {
      //               if (document.exitFullscreen) {
      //                   document.exitFullscreen();
      //               } else if (document.webkitCancelFullScreen) {
      //                   document.webkitCancelFullScreen();
      //               } else if (document.mozCancelFullScreen) {
      //                   document.mozCancelFullScreen();
      //               } else if (document.msExitFullscreen) {
      //                   document.msExitFullscreen();
      //               }
      //           } else {
      //               if (element.requestFullscreen) {
      //                   element.requestFullscreen();
      //               } else if (element.webkitRequestFullScreen) {
      //                   element.webkitRequestFullScreen();
      //               } else if (element.mozRequestFullScreen) {
      //                   element.mozRequestFullScreen();
      //               } else if (element.msRequestFullscreen) {
      //                   // IE11
      //                   element.msRequestFullscreen();
      //               }
      //           }
      //           this.fullscreen = !this.fullscreen;
      //       }
    },
    created(){
      // console.log("this",this);
      bus.$on('collapse', msg => {
                this.collapse = msg;
            })
    },
    components:{
      vHead, vSidebar
    },
    mounted: function () {
      // this.$alert('为了确保所有的小伙伴都能看到完整的数据演示，数据库只开放了查询权限和部分字段的更新权限，其他权限都不具备，完整权限的演示需要大家在自己本地部署后，换一个正常的数据库用户后即可查看，这点请大家悉知!', '友情提示', {
      //   confirmButtonText: '确定',
      //   callback: action => {
      //   }
      // });
      // var _this = this;
      // getRequest("/currentUserName").then(function (msg) {
      //   _this.currentUserName = msg.data;
      // }, function (msg) {
      //   _this.currentUserName = '游客';
      // });
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
/* .home_container {
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
.header-right {
  float: right;
  padding-right: 50px;
}

.el-aside {
  background-color: #ececec;
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
.user-avator {
  margin-left: 20px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.btn-bell,
.btn-fullscreen {
  font-size: 22px;
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}

.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}
.header-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}
.btn-bell .el-icon-bell {
  color: #fff;
}
.btn-bell-badge {
  position: absolute;
  right: 0;
  top: -2px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
} */
</style>
