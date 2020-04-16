import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const home = (r) => require.ensure([], () => r(require("@/page/home")), "home");
const login = (r) =>
  require.ensure([], () => r(require("@/page/login")), "login");
const manage = (r) =>
  require.ensure([], () => r(require("@/page/manage")), "manage");
const upload = (r) =>
  require.ensure([], () => r(require("@/page/book/upload")), "upload");
const bookList = (r) =>
  require.ensure([], () => r(require("@/page/book/bookList")), "bookList");
const addUser = (r) =>
  require.ensure([], () => r(require("@/page/user/addUser")), "addUser");
const userList = (r) =>
  require.ensure([], () => r(require("@/page/user/userList")), "userList");
const adminList = (r) =>
  require.ensure([], () => r(require("@/page/user/adminList")), "adminList");
const adminSet = (r) =>
  require.ensure([], () => r(require("@/page/setting/adminSet")), "adminSet");

const routes = [
  {
    path: "/",
    component: login,
  },
  {
    path: "/login",
    component: login,
  },
  {
    path: "/manage",
    component: manage,
    name: "",
    children: [
      {
        path: "",
        component: home,
        meta: [],
      },
      {
        path: "/upload",
        component: upload,
        meta: ["电子书管理", "上传电子书"],
      },
      {
        path: "/bookList",
        component: bookList,
        meta: ["电子书管理", "电子书列表"],
      },
      {
        path: "/addUser",
        component: addUser,
        meta: ["用户管理", "添加用户"],
      },
      {
        path: "/userList",
        component: userList,
        meta: ["用户管理", "用户列表"],
      },
      {
        path: "/adminList",
        component: adminList,
        meta: ["用户管理", "管理员列表"],
      },
      {
        path: "/adminSet",
        component: adminSet,
        meta: ["设置", "管理员信息"],
      },
    ],
  },
];

export default new Router({
  routes,
  strict: process.env.NODE_ENV !== "production",
});
