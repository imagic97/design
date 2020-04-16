<template>
  <div class="header_container">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/manage' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item v-for="(item, index) in $route.meta" :key="index">
        {{ item }}
      </el-breadcrumb-item>
    </el-breadcrumb>
    <el-dropdown @command="handleCommand">
      <div class="user">
        <span>{{ adminName }} </span> <img :src="avator" class="avator" />
      </div>

      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="home">首页</el-dropdown-item>
        <el-dropdown-item command="signout">退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import { logout } from "@/api";
import avator from "@/assets/logo.png";
import { mapActions, mapGetters } from "vuex";

export default {
  data() {
    return {
      avator: avator,
      baseImgPath: "",
    };
  },
  computed: {
    ...mapGetters(["adminName", "token"]),
  },
  methods: {
    ...mapActions(["setAdminName", "setToken"]),
    async handleCommand(command) {
      if (command == "home") {
        this.$router.push("/manage");
      } else if (command == "signout") {
        const res = await logout();
        if (res.data.code == 200) {
          this.$message({
            type: "success",
            message: "退出成功",
          });
          window.sessionStorage.removeItem("token");
          this.$router.push("/");
        } else {
          this.$message({
            type: "error",
            message: "退出操作出现错误",
          });
        }
      }
    },
  },
};
</script>

<style lang="less">
@import "../assets/style/mixin";
.header_container {
  background-color: #eff2f7;
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 20px;
}

.user {
  text-align: center;
  .avator {
    .wh(36px, 36px);
    border-radius: 50%;
    margin-right: 37px;
  }
}
</style>
