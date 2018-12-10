<template>
  <el-container class="dashboard">
    <el-main class="main">
     仪表盘
      <el-button type="primary" icon="el-icon-refresh
" size="small" style="margin-left: 3px;align:right;" @click="switchview">切换图表视图
      </el-button>

    <el-card class="box-card">
    <div class="text item">
      文章
    </div>
  </el-card>
  <el-card class="box-card">
    <div class="text item">
      附件
    </div>
  </el-card>
  
  <!--<el-card class="box-card">
    <div class="text item">
      评论
    </div>
  </el-card>
   <el-card class="box-card">
    <div class="text item">
      文章
    </div>
  </el-card> -->

    </el-main>
  </el-container>
</template>
<script>
  import BlogTable from '@/components/Console/BlogTable'
  import BlogCfg from '@/components/Console/BlogCfg'
import { postRequest } from "../../../utils/api";
import { putRequest } from "../../../utils/api";
import { deleteRequest } from "../../../utils/api";
import { getRequest } from "../../../utils/api";
  export default {
    mounted: function () {
      var _this = this;
      getRequest("/isAdmin").then(resp=> {
        if (resp.status == 200) {
          _this.isAdmin = resp.data;
        }
      })
    },
    data() {
      return {
        activeName: 'post',
        isAdmin: false
      };
      
    },
    methods: {
      handleClick(tab, event) {
//        console.log(tab, event);
      },
      switchview(){
        
      }
    },
    components: {
      'blog_table': BlogTable,
      'blog_cfg': BlogCfg
    }
  };
</script>
<style>
  .article_list > .header {
    background-color: #ececec;
    margin-top: 10px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
  }

  .article_list > .main {
    /*justify-content: flex-start;*/
    display: flex;
    flex-direction: column;
    padding-left: 0px;
    background-color: #fff;
    padding-top: 0px;
    margin-top: 8px;
  }

  .box-card {
    width: 480px;
  }
</style>
