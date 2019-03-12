<template>
  <el-container class="article_list">
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="全部文章" name="all" @tab-click="handleClick">
          <template>          
          <blog_table state="-1" :showEdit="false" :showDelete="false" :activeName="activeName" v-show="activeName=='all'"></blog_table>
          </template>
          
          <!-- <h1 v-show="activeName=='all'">all</h1> -->
        
        </el-tab-pane>
        <el-tab-pane label="已发表" name="post" @tab-click="handleClick">
          <template>
          <blog_table state="1" :showEdit="true" :showDelete="true" :activeName="activeName" v-show="activeName=='post'"></blog_table>
          </template>
          
          <!-- <h1 v-show="activeName=='post'">post</h1> -->
        
        </el-tab-pane>
        <el-tab-pane label="草稿箱" name="draft" @tab-click="handleClick">
          <template>
          <blog_table state="0" :showEdit="true" :showDelete="true" :activeName="activeName" v-show="activeName=='draft'"></blog_table>
          </template>
          <!-- <h1 v-show="activeName=='draft'">all</h1> -->
        
        </el-tab-pane>
        <el-tab-pane label="回收站" name="dustbin" @tab-click="handleClick">
          <!-- <blog_table state="2" :showEdit="false" :showDelete="true" :activeName="activeName" v-show="activeName=='dustbin'"></blog_table> -->
        </el-tab-pane>
        <!-- <el-tab-pane label="博客管理" name="blogmana" v-if="isAdmin">
          <blog_table state="-2" :showEdit="false" :showDelete="true" :activeName="activeName"></blog_table>
        </el-tab-pane>
        <el-tab-pane label="博客配置" name="blogcfg">
          <blog_cfg></blog_cfg>
        </el-tab-pane> -->
      </el-tabs>
    </el-main>
  </el-container>
</template>
<script>
  import BlogTable from '@/components/Console/Article/BlogTable'
  import BlogCfg from '@/components/Console/BlogCfg'
  import {postRequest} from '../../../utils/api'
  import {putRequest} from '../../../utils/api'
  import {deleteRequest} from '../../../utils/api'
  import {getRequest} from '../../../utils/api'
  export default {
    mounted: function () {
      // getRequest("/isAdmin").then(resp=> {
      //   if (resp.status == 200) {
      //     this.isAdmin = resp.data;
      //   }
      // })
    },
    data() {
      return {
        activeName: 'all',
        //当前激活的列表
        isAdmin: false
      };
    },
    methods: {
      handleClick(tab, event) {
        // "第"+event+"被点击了"
      this.$message.success(this.activeName)
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
</style>
