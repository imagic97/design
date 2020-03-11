<template>
  <div id="reading">
    <reader />
    <keep-alive>
      <component v-bind:is="currentComponent"></component>
    </keep-alive>
  </div>
</template>

<script>
import reader from "../../components/reader.vue";
import readerControl from "../../components/readerControl.vue";
import contentItem from "../../components/content.vue";
import theme from "../../components/themeControl.vue";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    readerControl,
    reader,
    contentItem,
    theme
  },
  data() {
    return {};
  }
};
</script>

<style scoped>
#reading {
  position: relative;
  width: 100%;
  height: 100%;
}
</style>
