<template>
  <el-container class="dashboard">
    <el-main class="main">
       
      <h1>空白模板</h1>
    </el-main>
  </el-container>
</template>
<script>
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
    };
  },
  methods: {
  },
  components: {
  }
};
</script>
<style>
</style>
