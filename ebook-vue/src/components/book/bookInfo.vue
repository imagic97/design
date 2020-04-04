<template>
  <div class="book_bookBlock" v-show="menuShow === 0" @click="hideMenu()">
    <div class="border">
      <div class="bookCover book_bookCover">
        <img v-lazy="API_TO_GET_COVER + bookID" class="bookCover_img" />
      </div>
      <div class="bookInfo book_bookInfo">
        <p class="book_bookTitle">书名：{{ book.title }}</p>
        <p class="book_bookAuthor">作者：{{ book.creator }}</p>
        <p class="book_language">语言：{{ book.language }}</p>
        <p class="book_publisher">出版社：{{ book.publisher }}</p>
        <p class="book_publishDate">出版日期：{{ book.publishDate }}</p>
        <p class="book_creator">ISBN：{{ book.isbn }}</p>
        <p class="book_bookContent">
          <span class="book_detail">
            简介：
            {{
            book.introduction == "" ? "-" : book.introduction
            }}
          </span>
        </p>
        <p class="book_publishDate">出版日期：{{ book.publishDate }}</p>
        <p class="book_creator">ISBN：{{ book.isbn }}</p>
        <button @click="setMenuShowOrHide()">阅读</button>
      </div>
    </div>
  </div>
</template>
<script>
import { ebookMixin } from "@/util/mixin";
import { getBookInfo } from "@/api/api";
// import VE from "@/util/vueEvent";
// import lS from "@/util/localStorage";
export default {
  mixins: [ebookMixin],
  data() {
    return {
      bookcover: "",
      book: {}
    };
  },

  mounted() {
    // this.book.fileName = this.bookID;
    this.init(this.title);
  },
  methods: {
    init(value) {
      if (value == null) return;
      getBookInfo(value).then(Response => {
        if (Response.data.code == 200) {
          this.book = Response.data.result;
        }
      });
    },
    hideMenu() {
      this.setMenuVisible(!this.menuVisible);
    }
  }
};
</script>
<style scoped>
.book_bookTitle {
  padding-top: 3px;
  font-size: 22px;
  overflow: auto;
  white-space: nowrap;
  word-break: break-all;
  word-wrap: normal;
  overflow: auto;
}

.bookCover_img {
  height: 203px;
  width: 130px;
}
.book_bookBlock {
  position: absolute;
  bottom: 0;
  top: 0;
  height: 100%;
  width: 100%;
  overflow: scroll;
}
.border {
  margin: 16px 0 108px 0;
}
.bookCover {
  height: 203px;
  width: 130px;
  box-shadow: 3px 3px 3px rgba(0, 25, 104, 0.3);
  margin: 0 auto;
  display: block;
}
.bookInfo {
  line-height: 1.5;
  list-style: none;
  color: #4a7ab0;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0.03);
  user-select: none;
}
.bookInfo p {
  margin: 12px 0;
}
button {
  display: block;
  width: 280px;
  height: 48px;
  margin: 0 auto;
  border: 0;
  border-radius: 10px;
  background-color: #409eff;
  outline: none;
}

@media (max-width: 460px) {
  .bookInfo {
    padding: 0 0 0 12px;
  }
}
</style>
