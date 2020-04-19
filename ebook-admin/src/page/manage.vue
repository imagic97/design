<template>
  <div class="manage_page fillcontain">
    <el-row style="height: 100%;">
      <el-col :span="4" style="min-height: 100%; background-color: #324057;">
        <p class="title title-1" style="color: rgb(32, 160, 255); margin: 24px auto;">WEBOOK</p>
        <p class="title title-2" style="color: rgb(32, 160, 255); margin: 24px auto;">后台管理</p>
        <el-menu
          :default-active="defaultActive"
          style="min-height: 100%;"
          background-color="#324057"
          text-color="rgb(32, 160, 255)"
          active-text-color="#ffd04b"
          router
        >
          <el-menu-item index="manage">
            <i class="el-icon-menu"></i>首页
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-document"></i>电子书管理
            </template>
            <el-menu-item index="upload">上传电子书</el-menu-item>
            <el-menu-item index="bookList">电子书列表</el-menu-item>
            <el-menu-item index="category">电子书分类</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-edit"></i>用户管理
            </template>
            <el-menu-item index="addUser">添加用户</el-menu-item>
            <el-menu-item index="userList">用户列表</el-menu-item>
            <el-menu-item index="adminList">管理员列表</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-setting"></i>设置
            </template>
            <el-menu-item index="adminSet">管理员信息</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>
      <el-col :span="20" style="height: 100%; overflow: auto;">
        <head-top></head-top>
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import headTop from "../components/headTop";
export default {
  components: {
    headTop
  },
  computed: {
    defaultActive: function() {
      return this.$route.path.replace("/", "");
    }
  }
};
</script>

<style lang="less" scoped>
@import "../assets/style/mixin";
.title {
  text-align: center;
  margin: 0 0 0 0;
  padding: 0;
}
.title-1 {
  font-size: 24px;
}
</style>
