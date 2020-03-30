<template>
  <div class="modifyContainer">
    <div class="loading" v-show="isLoading">
      <loading />
    </div>
    <div class="modify">
      <h2>修改信息</h2>
      <div class="modify_form">
        <input type="email" class="email" placeholder="邮箱,可省略" v-model="email" />
        <div class="tips">
          <span>{{tips}}</span>
        </div>
        <input type="text" class="user" placeholder="用户名" v-model="userName" />
        <div class="tips">
          <span>{{tips_a}}</span>
        </div>
        <input type="password" class="password" placeholder="旧密码" v-model="old_password" />
        <div class="tips">
          <span>{{tips_b}}</span>
        </div>
        <input type="password" class="password" placeholder="新密码" v-model="new_password" />
        <div class="tips">
          <span>{{tips_c}}</span>
        </div>
        <button class="modify_btn" @click="modify()">提交修改</button>
      </div>
    </div>
  </div>
</template>
<script>
import { modifyUser } from "@/api/api";
import loading from "@/components/common/loading";

export default {
  components: { loading },
  data() {
    return {
      userName: "",
      old_password: "",
      new_password: "",
      email: "",
      tips: "",
      tips_a: "",
      tips_b: "",
      tips_c: "",
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
    modify() {
      if (!this.userName) {
        this.tips_a = "请输入用户名";
        return;
      }
      if (!this.password) {
        this.tips_b = "请输入密码";
        return;
      }
      this.isLoading = true;
      modifyUser(this.userName, this.new_password, this.email).then(
        Response => {
          if (Response.data.code == 200) {
            this.tips_c = "修改成功";
          } else {
            this.tips_c = "修改失败";
          }
          this.isLoading = false;
        }
      );
    }
  }
};
</script>
<style scoped>
.loading {
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 199;
  background-color: rgba(0, 25, 104, 0.1);
}
.modify_form {
  max-width: 450px;
  padding: 12px 25px 64px 25px;
  margin: 0 auto;
}
h2 {
  text-align: center;
}

input {
  width: 100%;
  height: 50px;
  border: 1px solid #dbdbdb;
  outline: none;
  text-indent: 10px;
}
.modify_btn {
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
