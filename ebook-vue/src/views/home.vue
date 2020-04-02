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
import lS from "@/util/localStorage";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    Tabbar
  },
  mounted() {
    this.setIsLogin(lS.get("token"));
  }
};
</script>
<style scoped>
.home {
  height: 100%;
  overflow: auto;
}
</style>
