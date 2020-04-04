<template>
  <div id="app">
    <div class="loading" v-show="isLoading">
      <loading />
    </div>
    <router-view />
  </div>
</template>

<script>
import { ebookMixin } from "@/util/mixin";
import loading from "@/components/common/loading";
import VE from "@/util/vueEvent";
import lS from "@/util/localStorage";
export default {
  mixins: [ebookMixin],
  components: {
    loading
  },
  name: "app",
  data() {
    return {
      isLoading: false
    };
  },
  mounted() {
    VE.$on("isLoading", value => {
      this.isLoading = value;
    });
    this.CreateStyle(this.THEME_LIGHT, "themeStyle");
    this.setIsLogin(lS.get("token"));
  }
};
</script>

<style>
@import "./assets/reset.css";
body {
  background: #7d8188;
}
.loading {
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 199;
  background-color: rgba(0, 0, 0, 0);
}
#app {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 0;
  width: 870px;
  min-width: 320px;
  height: 100%;
  background-color: #f1f1f1;
  margin: 0 auto;
  user-select: none;
}
@media screen and (max-width: 768px) {
  #app {
    width: 100%;
  }
}
</style>
