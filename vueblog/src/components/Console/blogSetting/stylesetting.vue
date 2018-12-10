<template>
  <el-container class="dashboard">
    <el-main class="main">
        博客设置
        <el-menu :default-active="$route.path" class="el-menu-demo" mode="horizontal" @select="handleSelect">
  <el-menu-item index="/blogsetting">常规设置</el-menu-item>
 <!-- <el-menu-item index="/" >评论设置</el-menu-item> -->
  <el-menu-item index="/stylesetting" >博客样式</el-menu-item>
</el-menu>

<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

<!-- <el-form-item label="博客标题:" prop="title">
    <el-input  v-model="ruleForm.title"></el-input>
  </el-form-item>

  <el-form-item label="博客地址:" prop="blogaddress">
    <el-input  v-model="ruleForm.blogaddress"></el-input>
  </el-form-item>
   这暂时还不知道是干嘛的 -->

   <el-form-item label="博客LOGO:" prop="blogo">
    <el-input  v-model="ruleForm.blogo"></el-input>
  </el-form-item>
  <el-form-item label="favicon:" prop="favicon">
    <el-input  v-model="ruleForm.favicon"></el-input>
  </el-form-item> -->

  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">确认</el-button>
    <el-button @click="goback()">取消</el-button>
  </el-form-item>
</el-form>


    </el-main>
  </el-container>
</template>
<script>
import BlogTable from "@/components/Console/BlogTable";
import BlogCfg from "@/components/Console/BlogCfg";
import { postRequest } from "../../../utils/api";
import { putRequest } from "../../../utils/api";
import { deleteRequest } from "../../../utils/api";
import { getRequest } from "../../../utils/api";
export default {
  mounted: function() {
    var _this = this;
    getRequest("/isAdmin").then(resp => {
      if (resp.status == 200) {
        _this.isAdmin = resp.data;
      }
    });
  },
  data() {
    return {
      activeName: "post",
      isAdmin: false,
      ruleForm: {
        title: "",
        blogaddress: "",
        blogo: "",
        title: ""
      }
    };
  },
  methods: {
    submitForm() {},
    handleSelect(key, keyPath) {
                this.$router.push({
                    path: key,
                });
            },
    handleClick(tab, event) {
      //        console.log(tab, event);
    }
  },
  components: {
    blog_table: BlogTable,
    blog_cfg: BlogCfg
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
