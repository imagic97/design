<template>
  <div class="registerContainer">
    <div class="loading" v-show="isLoading">
      <loading />
    </div>
    <div class="register">
      <h2>注册/REGISTER</h2>
      <div class="register_form">
        <input type="text" class="email" placeholder="邮箱,可省略" v-model="email" />
        <div class="tips">
          <span>{{tips}}</span>
        </div>
        <input type="text" class="user" placeholder="用户名" v-model="userName" />
        <div class="tips">
          <span>{{tips_a}}</span>
        </div>
        <input type="password" class="password" placeholder="密码" v-model="password" />
        <div class="tips">
          <span>{{tips_b}}</span>
        </div>
        <button class="register_btn" @click="register()">注册</button>
      </div>
    </div>
  </div>
</template>
<script>
import { register } from "@/api/api";
import loading from "@/components/common/loading";

export default {
  components: { loading },
  data() {
    return {
      userName: "",
      password: "",
      email: "",
      tips: "",
      tips_a: "",
      tips_b: "",
      isLoading: false
    };
  },
  watch: {
    userName: function() {
      this.tips_a = "";
    },
    password: function() {
      this.tips_b = "";
    }
  },
  methods: {
    register() {
      if (!this.userName) {
        this.tips_a = "请输入用户名";
        return;
      }
      if (!this.password) {
        this.tips_b = "请输入密码";
        return;
      }
      this.isLoading = true;
      register(this.userName, this.password, this.email).then(Response => {
        if (Response.data.code == 200) {
          this.$router.push("/login");
        } else {
          this.tips_b = "注册失败";
        }
        this.isLoading = false;
      });
    }
  }
};
</script>
<style scoped>
.registerContainer {
  position: relative;
}
.loading {
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 199;
  padding: 167px 0 0 0;
  background-color: rgba(0, 25, 104, 0.1);
}
h2 {
  text-align: center;
}

.outer_label {
  position: relative;
  margin: 0 auto;
  width: 120px;
  height: 120px;
  border-radius: 60px;
  box-shadow: 0 2px 10px rgba(0, 25, 104, 0.3);
}
.register_form {
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

.register_btn {
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
