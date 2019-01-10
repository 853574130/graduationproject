<template>
  <el-container class="dashboard">
    <el-main class="main">
       
      <!-- <h1>首页模板</h1> -->
      <!-- <a href="http://localhost:8082/#/home"><h5>进入后台</h5></a>
      <a href="http://localhost:8082/#/login"><h5>登录</h5></a> -->

          <vheader></vheader>
          <loading v-if="loading"></loading>
          <div v-else>
            <keep-alive>
              <router-view v-if="$route.meta.keepAlive"/>
            </keep-alive>
            <router-view v-if="!$route.meta.keepAlive"/>
          </div>

          <vfooter></vfooter>

    </el-main>
  </el-container>
</template>
<script>
  import loading from './components/loading';
  // import {addScript} from './common/api/script';
  import vfooter from './components/vfooter';
  import vheader from './components/vheader';

export default {
  // mounted: function() {
  //   var _this = this;
  //   // getRequest("/isAdmin").then(resp => {
  //   //   if (resp.status == 200) {
  //   //     _this.isAdmin = resp.data;
  //   //   }
  //   // });
  // },
  mounted() {
    
      this.loading = false;
      
    },
  data() {
    return {
      loading:true,
      
    };
  },
  methods: {
    // timeout(){
    //   setTimeout(console.log("为什么"),5000);
    // setTimeout(alert("666"),50000);
    // },
  },
  components: {
      loading,
      vfooter,
      vheader
  },
  created(){
    // this.timeout();
  }
};
</script>
<style>
</style>
