<template>
  <div class="home">
    <keep-alive>
      <router-view />
    </keep-alive>
    <Tabbar></Tabbar>
  </div>
</template>
<script>
import Tabbar from "@/components/common/tabbar";
import sessionStorage from "@/util/sessionStorage";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    Tabbar
  },
  mounted() {
    this.setUserName(sessionStorage.get("userName"));
    this.setPassword(sessionStorage.get("password"));
    this.setIsLogin(sessionStorage.get("isLogin") ? true : false);
  }
};
</script>
<style scoped>
.home {
  height: 100%;
  overflow: auto;
}
</style>
