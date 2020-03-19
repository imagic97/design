<template>
  <div class="userBookSelfContainer">
    <div class="bookSelf">
      <div
        class="bookSelfList"
        v-for="(item, key) in userSelfList"
        :key="key"
        @click="toReaderBook(item)"
      >
        <div class="bookCover">
          <img v-lazy="API_TO_GET_COVER + item.bookID" :key="item.bookID" class="bookCover_img" />
          <div class="book_cover_boder"></div>
        </div>
        <div class="title">{{item.bookTitle}}</div>
      </div>
      <span class="addBook placeholder"></span>
      <!-- 占位作用，防止组件弹性拉伸 -->
      <div class="placeholder"></div>
      <div class="placeholder"></div>
      <div class="placeholder"></div>
      <div class="placeholder"></div>
    </div>
  </div>
</template>
<script>
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],

  data() {
    return {
      userSelfList: [
        { bookID: "1.epub", bookTitle: "" },
        { bookID: "2.epub", bookTitle: "dadsdsdasdasdadadadad" },
        { bookID: "3.epub", bookTitle: "dad" },
        { bookID: "4.epub", bookTitle: "dad" },
        { bookID: "5.epub", bookTitle: "dad" },
        { bookID: "6.epub", bookTitle: "dad" },
        { bookID: "7.epub", bookTitle: "dad" }
      ]
    };
  },
  methods: {
    toReaderBook(book) {
      this.setBookID(book.bookID);
      this.$router.push({ path: "/book-reader" });
    }
  }
};
</script>

<style scoped>
.bookSelf {
  display: flex;
  padding: 0 30px;
  margin-left: -30px;
  margin-top: 40px;
  flex-direction: row;
  flex-wrap: wrap;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -webkit-box-pack: justify;
  justify-content: space-between;
}
.title {
  margin-top: 16px;
  line-height: 18px;
  overflow: hidden;
  height: 36px;
  text-overflow: ellipsis;
}

.placeholder,
.bookSelfList {
  display: block;
  width: 108px;
  height: 156px;
  margin-left: 30px;
  margin-bottom: 75px;
}

.bookCover_img {
  display: block;
  width: 100%;
  height: 100%;
  box-shadow: 6px 7px 6px rgba(0, 25, 104, 0.3);
}

@media screen and (min-width: 500px) {
  .bookCover:hover {
    z-index: 99;
    transform: scale(1.3);
  }
}
.addBook {
  background-color: #e0e0e0;
  background-size: 40px 40px;
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-image: url(../../assets/add.png);
}

.bookCover {
  position: relative;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

.book_cover_boder {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0px;
  left: 0;
  background-image: linear-gradient(
    90deg,
    hsla(0, 0%, 63.1%, 0.25),
    rgba(21, 21, 20, 0.1) 1%,
    hsla(0, 0%, 100%, 0.15) 4%,
    hsla(0, 0%, 58%, 0.1) 8%,
    hsla(0, 0%, 89%, 0) 57%,
    rgba(223, 218, 218, 0.03) 91%,
    rgba(223, 218, 218, 0.05) 98%,
    hsla(0, 0%, 100%, 0.1)
  );
  box-shadow: inset 0 0 0 0 rgba(0, 0, 0, 0.1);
}
@media screen and (max-width: 460px) {
  .placeholder,
  .bookSelfList {
    margin-left: 20px;
    margin-bottom: 50px;
  }
}
@media screen and (max-width: 450px) {
  .placeholder,
  .bookSelfList {
    width: 95.04px;
    height: 137.266px;
  }
}
@media screen and (max-width: 375px) {
  .placeholder,
  .bookSelfList {
    width: 86.4px;
    height: 124.797px;
  }
}

@media screen and (max-width: 350px) {
  .bookSelf {
    padding: 0 20px;
    margin-left: -20px;
    margin-top: 40px;
  }
  .placeholder,
  .bookSelfList {
    width: 73.438px;
    height: 106.078px;
  }
}
</style>