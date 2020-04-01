<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  name: "app",
  mounted() {
    this.CreateStyle(this.THEME_LIGHT, "themeStyle");
  }
};
</script>

<style>
@import "./assets/reset.css";
body {
  background: #7d8188;
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
  el-message {
    width: 45px;
  }
}
</style>
