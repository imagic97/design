<template>
  <div class="bookCoverContainer">
    <div class="bookCover">
      <img v-lazy="imgUrl" :key="imgUrl" alt />
      <span class="book_cover_boder"></span>
    </div>
    <div class="bookTitle">{{ bookTitle!="" ? bookTitle+"das" : "空" }}</div>
  </div>
</template>
<script>
export default {
  props: {
    imgUrl: String,
    bookTitle: String
  }
};
</script>
<style scoped>
img {
  display: block;
  width: 100%;
  height: 156px;
  box-shadow: 6px 7px 6px rgba(0, 25, 104, 0.3);
}

@media screen and (min-width: 500px) {
  .bookCover:hover {
    z-index: 99;
  }
}

.bookCover {
  margin: 0;
  padding: 0;
  position: relative;
}
.bookTitle {
  text-overflow: ellipsis;
  margin: 16px 0 0 0;
  max-height: 36px;
}

/* @media screen and (max-width: 500px) {
  .bookTitle {
    text-overflow: ellipsis;
    margin: 8px 0 22px 0;
    max-height: 36px;
  }
} */

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
</style>
