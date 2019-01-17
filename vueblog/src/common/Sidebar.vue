<template>
  <div class="sidebar">
    <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157" text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
      <template v-for="item in roleItems">
        <template v-if="item.subs">
          <el-submenu :index="item.index" :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <template v-if="subItem.subs">
                <el-submenu :index="subItem.index" :key="subItem.index">
                  <template slot="title">
                    <i :class="subItem.icon"></i>
                    <span slot="title">{{ subItem.title }}</span>
                  </template>
                  <el-menu-item v-for="(ssi,i) in subItem.subs" :key="i" :index="ssi.index">
                    <i :class="ssi.icon"></i>
                    <span slot="title">{{ ssi.title }}</span>
                  </el-menu-item>
                </el-submenu>
              </template>
              <template v-else>
                <el-menu-item :index="subItem.index" :key="subItem.index">
                  <i :class="item.icon"></i>
                  <span slot="title">{{ subItem.title }}</span>
                </el-menu-item>
              </template>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from "../common/bus";
export default {
  data() {
    return {
      collapse: false,
      items: [
        {
          icon: "el-icon-setting",
          index: "dashboard",
          title: "系统首页"
        },
        {
          icon: "el-icon-setting",
          index: "9",
          title: "系统管理",
          subs: [
            {
              icon: "el-icon-setting",
              index: "company",
              title: "系统设置"
            },
            {
              icon: "el-icon-view",
              index: "userList",
              title: "账号管理"
            }
          ]
        }
      ]
    };
  },
methods:{
  handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
},
  computed: {
    onRoutes() {
      return this.$route.path.replace("/", "");
    },
    roleItems() {
      let role = localStorage.getItem("ms_role");
      if (role == undefined || !role) {
        this.$router.push("/login");
      }
      let newItems = new Array();
      newItems.push(this.items[0]);
      for (let i = 1; i <= 10; i++) {
        let value = 1 << (i - 1);
        let hasRole = role & value;
        if (hasRole > 0) {
          newItems.push(this.items[i]);
        }
      }
      return newItems;
    }
  },
  created() {
    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on("collapse", msg => {
      this.collapse = msg;
    });
  }
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}
.sidebar > ul {
  height: 100%;
}
</style>
