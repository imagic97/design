<template>
  <div class="reading">
    <book-header />
    <book-reader />
    <book-info />
    <book-menu />
    <book-content />
    <book-theme />
    <book-mark />
  </div>
</template>

<script>
import bookReader from "@/components/book/bookReader";
import bookMenu from "@/components/book/bookMenu";
import bookContent from "@/components/book/bookContent";
import bookTheme from "@/components/book/bookTheme";
import bookHeader from "@/components/book/bookHeader";
import bookInfo from "@/components/book/bookInfo";
import bookMark from "@/components/book/bookMark";
import { ebookMixin } from "@/util/mixin";

import { addReadHistory } from "@/api/api";

export default {
  mixins: [ebookMixin],
  components: {
    bookReader,
    bookMenu,
    bookContent,
    bookTheme,
    bookHeader,
    bookInfo,
    bookMark,
  },

  data() {
    return {
      timeIntervel: null,
    };
  },
  mounted() {
    this.timeIntervel = setInterval(this.saveRead, 60000);
  },
  methods: {
    saveRead() {
      let chapter = JSON.stringify(this.createBook());
      addReadHistory(this.bookID, chapter).then((Response) => {
        if (Response.data.code == 200) {
          console.log("ada");
        } else console.log(Response.data.message);
      });
    },
  },
  destroyed() {
    this.setBookID("");
    this.setFileName("");
    this.setTitle("");
    this.setContent("");
    this.setPosition("");
    this.setNextPosition("");
    this.setContentList([]);
    this.setKeyInContent(0);
    this.setMenuVisible(false);
    this.setMenuShow(-1);

    clearInterval(this.timeIntervel);
  },
};
</script>

<style scoped>
.reading {
  position: relative;
  width: 100%;
  height: 100%;
}
</style>
