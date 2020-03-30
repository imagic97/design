<template>
  <div class="reading">
    <book-reader />
    <book-menu />
    <book-content />
    <book-theme />
  </div>
</template>

<script>
import bookReader from "@/components/book/bookReader";
import bookMenu from "../../components/book/bookMenu";
import bookContent from "../../components/book/bookContent";
import bookTheme from "../../components/book/bookTheme";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    bookReader,
    bookMenu,
    bookContent,
    bookTheme
  },
  destroyed() {
    this.setBookID("");
    this.setContent("");
    this.setPosition("");
    this.setNextPosition("");
    this.setContentList([]);
    this.setKeyInContent(0);
  }
};
</script>

<style scoped>
.reading {
  position: relative;
  width: 100%;
  height: 100%;
}
</style>
