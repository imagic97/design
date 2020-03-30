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
export default {
  components: {
    Tabbar
  }
};
</script>
<style scoped>
.home {
  height: 100%;
  overflow: auto;
}
</style>