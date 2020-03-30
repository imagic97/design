<template>
  <div class="container">
    <div class="user" v-show="isLogin">
      <router-link to="/history" tag="div">
        <div class="user_item">阅读历史记录</div>
      </router-link>
      <router-link to="/modify" tag="div">
        <div class="user_item">修改用户信息</div>
      </router-link>
      <div class="user_item" @click="logout()">登出</div>
    </div>
    <div class="tologin" v-show="!isLogin">
      <router-link to="/login" tag="div">
        <div class="user_item">登录</div>
      </router-link>
      <router-link to="/register" tag="div">
        <div class="user_item">注册</div>
      </router-link>
    </div>
    <router-link to="/me" tag="div">
      <div class="user_item">关于WEBOOK</div>
    </router-link>
  </div>
</template>
<script>
import { logout } from "@/api/api";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  methods: {
    logout() {
      logout();
      this.setIsLogin(false);
    }
  }
};
</script>
<style scoped>
.user_item {
  margin: 10px 0 0 0;
  height: 45px;
  text-align: center;
  line-height: 45px;
  border: solid #ebedef;
  overflow: auto;
}
.user_item:hover {
  background: linear-gradient(90deg, rgba(0, 0, 0, 0.05), transparent);
}
</style>
