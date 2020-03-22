import Vue from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import VueLazyload from "vue-lazyload";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
Vue.config.productionTip = false;

Vue.use(ElementUI);
Vue.use(VueLazyload, {
  error: require("@/assets/error.svg"),
  loading: require("@/assets/pre-image.svg")
});
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
