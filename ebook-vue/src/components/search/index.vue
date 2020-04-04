<template>
  <ul class="search_result_list">
    <li class="search_result_item" v-for="(item, resultKey) in searchResult" :key="resultKey">
      <div class="search_result_bookBlock" @click="toBookInfo(item)">
        <div class="bookCover search_result_bookCover">
          <img v-lazy="API_TO_GET_COVER + item.fileName" class="bookCover_img" />
        </div>
        <div class="bookInfo search_result_bookInfo">
          <p class="search_result_bookTitle">{{ item.title }}</p>
          <p class="search_result_bookAuthor">{{ item.creator }}</p>
          <p class="search_result_bookContent">
            <span class="search_result_detail">
              {{
              item.introduction == "" ? "-" : item.introduction
              }}
            </span>
          </p>
        </div>
      </div>
    </li>
  </ul>
</template>
<script>
import { ebookMixin } from "@/util/mixin";
// import { getUserSelf, deleteBook, deleteBookFromSelf } from "@/api/api";
// import VE from "@/util/vueEvent";
// import lS from "@/util/localStorage";
export default {
  mixins: [ebookMixin],
  props: {
    searchResult: Array
  },
  methods: {
    toBookInfo(book) {
      this.setBookID(book.fileName);
      this.setFileName(book.title);
      this.setTitle(book.bookId);
      this.setMenuVisible(false);
      this.setMenuShow(0);
      this.$router.push({ path: "/book-reader" });
    }
  }
};
</script>
<style scoped>
.search_result_bookTitle {
  padding-top: 3px;
  font-size: 22px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  word-break: break-all;
  word-wrap: normal;
}

.search_result_list {
  width: auto;
  height: 100%;
  margin: 24px 36px 64px 36px;
  border-radius: 16px;
  padding: 0 16px;
  background-color: white;
}
.search_result_bookBlock:hover {
  background: rgba(0, 0, 0, 0.05);
}
.search_result_item {
  border-radius: 0;
  border: solid #ebedf1;
  border-width: 0 0 1px;
  padding: 24px;
  cursor: pointer;
}
.search_result_item:first-child {
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
}
.bookCover_img {
  height: 103px;
  width: 72px;
}
.search_result_bookBlock {
  width: 100%;
  display: block;
}
.bookCover {
  width: 72px !important;
  height: 103px !important;
  float: left;
  display: block;
  width: 120px;
  height: 174px;
  box-shadow: 3px 3px 3px rgba(0, 25, 104, 0.3);
  position: relative;
}
.bookInfo {
  height: 108px;
  line-height: 1.5;
  list-style: none;
  color: #4a7ab0;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0.03);
  user-select: none;
  padding: 0 0 0 34px;
  overflow: hidden;
}
@media (max-width: 460px) {
  .search_result_list {
    margin: 24px 0 0 0;
  }
  .search_result_item {
    padding: 24px 0;
  }
  .bookInfo {
    padding: 0 0 0 12px;
  }
}
</style>
