import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

Vue.use(ElementUI);

Vue.config.productionTip = false;

router.beforeEach((to, from, next) => {
  if (to.path == "/login" || to.path == "/register") {
    next();
  } else {
    let token = window.sessionStorage.getItem("token");
    if (token != null) {
      console.log(token);
      next();
    } else {
      next("/login");
    }
  }
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
