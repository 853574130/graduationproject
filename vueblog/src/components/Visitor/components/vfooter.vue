<template>
  <div id="vfooter">
    <theme></theme>
    <div class="copyright">
      <div>Copyright © 2018 @此心安处是吾乡 BY <a href="#" target="_blank">iauhsoaix</a></div>
      <span id="busuanzi_container_site_uv">本站访客数 <span id="busuanzi_value_site_uv"></span> 人次</span>
      <span id="busuanzi_container_site_pv">本站总访问量 <span id="busuanzi_value_site_pv"></span> 次</span>
    </div>
  </div>

</template>

<script>
  import theme from './theme';

  export default {
    name: "vfooter",
    components: {
      theme,
    }
  }
</script>

<style lang="less" scoped>
  .copyright {
    position: absolute;
    right: 10px;
    bottom: 10px;
    color: #dad9eb;
    font-size: 12px;
    text-align: right;
    line-height: 1.5;
    a {
      color: #38bafc;
    }
  }
</style>
