<template>
  <el-container class="dashboard">
    <el-main class="main">

      <el-menu :default-active="$route.path" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="/userinfo">基本资料</el-menu-item>

        <el-menu-item index="/editpwd">密码修改</el-menu-item>
      </el-menu>

      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名:" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>

        <el-form-item label="显示名称" prop="nickname">
          <template>
            <el-popover placement="top-start" title="标题" width="200" trigger="hover" content="这是博客显示的名字。">
              <i class="el-icon-question" style="display:inline"></i>
            </el-popover>
          </template>
          <el-input v-model="ruleForm.nickname"></el-input>
        </el-form-item>

        <el-form-item label="邮箱:" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>

        <el-form-item label="头像:" prop="portrait">
          <el-input v-model="ruleForm.portrait"></el-input>
        </el-form-item>

        <el-form-item label="个人说明:" prop="describe">
          <el-input type="textarea" v-model="ruleForm.describe"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
          <!-- <el-button @click="resetForm('ruleForm')">恢复当前</el-button> -->
        </el-form-item>
      </el-form>

    </el-main>
  </el-container>
</template>
<script>
import BlogTable from "@/components/Console/Article/BlogTable";
import BlogCfg from "@/components/Console/BlogCfg";
 import {postRequest} from '../../utils/api'
  import {putRequest} from '../../utils/api'
  import {deleteRequest} from '../../utils/api'
  import {getRequest} from '../../utils/api'
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
      isAdmin: false,
      ruleForm: {
        name: "",
        nickname: "",
        email: "",
        portrait: "",
        describe: ""
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 2, max: 10, message: "长度在 2 到 10 个字符", trigger: "blur" }
        ],
        describe: [{ required: false, message: "请填写活动形式", trigger: "blur" }]
      }
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      this.$router.push({
        path: key
        // query: { repertoryId: this.search.repertoryId }
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
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
