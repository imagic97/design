import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

//VUE应用路由导航模块
export default new Router({
  routes: [
    {
      path: "/",
      //根路径路由重定向
      redirect: "/self",
    },
    {
      name: "home",
      path: "/home",
      component: (resolve) => require(["@/views/home"], resolve),
      children: [
        {
          name: "self",
          path: "/self",
          component: (resolve) => require(["@/views/self"], resolve),
        },
        {
          path: "/mine",
          component: (resolve) => require(["@/views/mine"], resolve),
          children: [
            {
              path: "/",
              component: (resolve) => require(["@/components/mine"], resolve),
            },
            {
              path: "/modify",
              component: (resolve) =>
                require(["@/components/mine/modifyUser"], resolve),
            },
            {
              path: "/history",
              component: (resolve) =>
                require(["@/components/mine/readHistory"], resolve),
            },
            {
              path: "/me",
              component: (resolve) =>
                require(["@/components/common/me"], resolve),
            },
          ],
        },
        {
          name: "search",
          path: "/search",
          component: (resolve) => require(["@/views/search"], resolve),
        },
        {
          name: "category",
          path: "/category",
          component: (resolve) => require(["@/views/category"], resolve),
        },
      ],
    },
    {
      name: "book-reader",
      path: "/book-reader",
      component: (resolve) => require(["@/views/book"], resolve),
    },
    {
      name: "login",
      path: "/login",
      component: (resolve) => require(["@/components/mine/login"], resolve),
    },
    {
      name: "register",
      path: "/register",
      component: (resolve) => require(["@/components/mine/register"], resolve),
    },
    {
      path: "/**",
      component: (resolve) => require(["@/components/common/error"], resolve),
    },
  ],
});
