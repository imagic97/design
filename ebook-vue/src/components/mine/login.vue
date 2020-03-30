<template>
  <div class="loginContainer">
    <div class="loading" v-show="isLoading">
      <loading />
    </div>
    <div class="login">
      <h2>登录/LOGIN</h2>
      <div class="login_form">
        <input type="text" class="user" placeholder="用户名" v-model="user_name" />
        <div class="tips">
          <span>{{tips_a}}</span>
        </div>
        <input type="password" class="password" placeholder="密码" v-model="user_password" />
        <div class="tips">
          <span>{{tips_b}}</span>
        </div>
        <button class="login_btn" @click="login()">登录</button>
      </div>
    </div>
  </div>
</template>
 
<script>
import { login } from "@/api/api";
import loading from "@/components/common/loading";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: { loading },
  data() {
    return {
      user_name: "",
      user_password: "",
      tips_a: "",
      tips_b: "",
      isLoading: false
    };
  },
  watch: {
    user_name: function() {
      this.tips_a = "";
    },
    user_password: function() {
      this.tips_b = "";
    }
  },
  //   created() {
  //     if (
  //       JSON.parse(localStorage.getItem("user")) &&
  //       JSON.parse(localStorage.getItem("user")).user_name
  //     ) {
  //       this.user_name = JSON.parse(localStorage.getItem("user")).user_name;
  //       this.user_password = JSON.parse(localStorage.getItem("user")).user_password;
  //     }
  //   },
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
      this.isLoading = true;
      login(this.user_name, this.user_password).then(Response => {
        if (Response.data.code == 200) {
          this.tips_b = "登录成功";
          this.setUserName(this.user_name);
          this.setPassword(this.user_password);
          this.setIsLogin(true);
          this.$router.push("/mine");
        } else {
          this.tips_b = "登录失败，检查用户名/密码";
        }
        this.isLoading = false;
      });
    }
  }
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
  background-color: rgba(0, 25, 104, 0.1);
}
h2 {
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
