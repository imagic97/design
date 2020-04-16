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
import { ebookMixin } from "@/util/mixin";
import sS from "@/util/sessionStorage";
import lS from "@/util/localStorage";
import { getReadHistory } from "@/api/api";
export default {
  mixins: [ebookMixin],
  components: {
    Tabbar,
  },
  mounted() {
    this.setIsLogin(lS.get("token"));
    this.init();
  },
  methods: {
    init() {
      if (lS.get("token") == "") {
        return;
      }
      getReadHistory().then((Response) => {
        if (Response.data.code == 200) {
          let array = Response.data.result;
          for (let index = 0; index < array.length; index++) {
            let chapter = JSON.parse(array[index].chapter);
            sS.set(chapter.fileName, array[index].chapter);
          }
        }
      });
    },
  },
};
</script>
<style scoped>
.home {
  height: 100%;
  overflow: auto;
}
</style>
