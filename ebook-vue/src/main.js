import Vue from "vue";
import App from "./App.vue";
import store from "./store";
import VueLazyload from "vue-lazyload";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
Vue.config.productionTip = false;

Vue.use(ElementUI);
Vue.use(VueLazyload, {
  error: require("@/assets/book.svg"),
  loading: require("@/assets/loading.gif")
});
new Vue({
  store,
  render: h => h(App)
}).$mount("#app");
