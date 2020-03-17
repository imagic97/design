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
    // {
    //   path: '/book-store',
    //   component: resolve => require(['@/views/store/index.vue'], resolve),
    //   redirect: '/book-store/shelf',
    //   children: [
    //     {
    //       path: '/book-store/shelf',
    //       component: resolve => require(['@/views/store/bookShelf.vue'], resolve),
    //       //meta:路由元数据
    //       //一个路由匹配到的所有路由记录会暴露为 $route 对象 (还有在导航守卫中的路由对象) 的 $route.matched 数组
    //       //需要遍历 $route.matched 来检查路由记录中的 meta 字段。
    //       meta: { key: 1 }
    //     },
    //     {
    //       path: '/book-store/category',
    //       component: resolve => require(['@/views/store/bookCategory.vue'], resolve),
    //       meta: { key: 2 }
    //     },
    //     {
    //       path: '/book-store/home',
    //       component: resolve => require(['@/views/store/bookHome.vue'], resolve),
    //       meta: { key: 3 }
    //     },
    //     {
    //       path: '/book-store/list',
    //       component: resolve => require(['@/views/store/bookList.vue'], resolve),
    //       meta: { key: 4 }
    //     },
    //     {
    //       path: '/book-store/detail',
    //       component: resolve => require(['@/views/store/bookDetail.vue'], resolve),
    //       meta: { key: 5 }
    //     },
    //     {
    //       path: '/book-store/book-speaking',
    //       component: resolve => require(['@/views/store/bookSpeaking.vue'], resolve),
    //       meta: { key: 6 }
    //     }
    //   ]
    // }
  ]
});
