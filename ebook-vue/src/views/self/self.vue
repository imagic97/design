<template>
  <div class="bookSelfView">
    <div class="userBookSelf">
      <a
        class="bookSelfList"
        v-for="(item, key) in userSelfList"
        :key="key"
        href=""
      >
        <keep-alive>
          <book-cover
            :imgUrl="API_TO_GET_COVER + item.bookID"
            :bookTitle="item.bookTitle"
          />
        </keep-alive>
      </a>
    </div>
  </div>
</template>
<script>
import bookCover from "@/components/self/bookCover.vue";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    bookCover
  },
  data() {
    return {
      userSelfList: [
        { bookID: "1.epub", bookTitle: "dad" },
        { bookID: "2.epub", bookTitle: "dad" },
        { bookID: "3.epub", bookTitle: "dad" },
        { bookID: "4.epub", bookTitle: "dad" },
        { bookID: "5.epub", bookTitle: "dad" },
        { bookID: "6.epub", bookTitle: "dad" },
        { bookID: "7.epub", bookTitle: "dad" },
        { bookID: "8.epub", bookTitle: "dad" }
      ]
    };
  }
};
</script>
<style scoped>
.bookSelfList {
  display: block;
  width: 108px;
  /* height: auto; */
  margin-left: 32px;
  margin-bottom: 40px;
}

.userBookSelf {
  display: flex;
  margin-top: 40px;
  flex-direction: row;
  flex-wrap: wrap;
  -webkit-box-pack: justify;
  margin-top: 40px;
}
a {
  margin: 0;
  text-decoration: none;
}
.bookSelfView {
  width: 100%;
  overflow: auto;
  height: 100%;
}
</style>
