<template>
  <div class="loginContainer">
    <div class="login">
      <avatar />
      <h2>登录/LOGIN</h2>
      <form class="login_form">
        <input
          type="text"
          class="user"
          autoComplete="username"
          placeholder="用户名"
          v-model="user_name"
        />
        <div class="tips">
          <span>{{ tips_a }}</span>
        </div>
        <input
          type="password"
          class="password"
          autoComplete="current-password"
          placeholder="密码"
          v-model="user_password"
        />
        <div class="tips">
          <span>{{ tips_b }}</span>
        </div>
        <button class="login_btn" @click.prevent="login()">登录</button>
      </form>
    </div>
  </div>
</template>

<script>
import { login } from "@/api/api";
import { ebookMixin } from "@/util/mixin";
import lS from "@/util/localStorage";
import VE from "@/util/vueEvent";
import avatar from "@/components/common/avatar";
export default {
  components: {
    avatar,
  },
  mixins: [ebookMixin],
  data() {
    return {
      user_name: "",
      user_password: "",
      tips_a: "",
      tips_b: "",
    };
  },
  watch: {
    user_name: function() {
      this.tips_a = "";
    },
    user_password: function() {
      this.tips_b = "";
    },
  },
  methods: {
    login() {
      if (!this.user_name) {
        this.tips_a = "请输入用户名";
        return;
      }
      if (!this.user_password) {
        this.tips_b = "请输入密码";
        return;
      }
      lS.set("token", "");
      VE.$emit("isLoading", true);
      login(this.user_name, this.user_password)
        .then((Response) => {
          if (Response.data.code == 200) {
            this.tips_b = "登录成功";
            this.setUserName(this.user_name);
            this.setIsLogin(Response.data.result);
            lS.set("token", Response.data.result);
            lS.set("userName", this.user_name);
            this.$router.push("/mine");
          } else {
            this.tips_b = Response.data.message;
          }
          VE.$emit("isLoading", false);
        })
        .catch(() => {
          this.tips_b = "请检查网络";
          VE.$emit("isLoading", false);
        });
    },
  },
};
</script>
<style scoped>
.loginContainer {
  position: relative;
}
.loading {
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 99;
  background-color: rgba(102, 177, 255, 0.1);
}
.login {
  padding: 48px 0 0 0;
}
h2 {
  margin: 24px 0 12px 0;
  text-align: center;
}
.login_form {
  max-width: 450px;
  padding: 0 10px 64px 10px;
  margin: 0 auto;
}

input {
  width: 100%;
  height: 50px;
  border: 1px solid #dbdbdb;
  outline: none;
  text-indent: 10px;
}

.login_btn {
  width: 100%;
  height: 50px;
  background-color: rgb(0, 167, 222);
  border: 0;
  outline: none;
}
.tips {
  width: 100%;
  height: 26px;
  color: #f66495;
  text-align: center;
}

.tips span {
  margin: 0 auto;
}
</style>
