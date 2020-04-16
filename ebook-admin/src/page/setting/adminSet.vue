<template>
  <div class="adduser">
    <h1>当前管理员信息</h1>
    <el-form :model="adminForm" :rules="rules" ref="loginForm">
      <el-form-item prop="username">
        <el-input v-model="adminForm.username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input placeholder="密码" v-model="adminForm.password"></el-input>
      </el-form-item>
      <el-form-item prop="email">
        <el-input placeholder="邮箱" v-model="adminForm.email"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="submitForm('loginForm')"
          class="submit_btn"
          >修改</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      adminForm: {
        username: "",
        password: "",
        email: "",
        type: 1,
      },
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        email: [
          {
            required: false,
            methods: "请输入用户邮箱",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm() {},
  },
};
</script>
<style lang="less" scoped>
.adduser {
  width: 350px;
  margin: 44px 0 0 16px;
  h1 {
    margin-bottom: 18px;
  }
}
</style>
