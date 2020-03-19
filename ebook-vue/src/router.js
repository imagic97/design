import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

//VUE应用路由导航模块
export default new Router({
  routes: [
    {
      path: "/",
      //根路径路由重定向
      redirect: "/book-self"
    },
    {
      path: "/book-self",
      component: resolve => require(["@/views/self/self.vue"], resolve)
    },
    {
      path: "/book-reader",
      component: resolve => require(["@/views/book/reading.vue"], resolve)
    }
  ]
});
