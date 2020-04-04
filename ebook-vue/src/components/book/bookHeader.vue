<template>
  <div class="title-wrapper" v-show="menuVisible">
    <div class="left" @click="back">
      <span class="iconfont icon-iconback"></span>
    </div>
    <div class="center" @click="back">
      <span class="book-title">{{ fileName == "" ? "无书名" : fileName }}</span>
    </div>
    <div class="right">
      <div class="icon-wrapper">
        <span class="iconfont icon-add-shelf" @click="addToSlef()" v-show="isInSelf()"></span>
      </div>
    </div>
  </div>
</template>

<script>
import { ebookMixin } from "@/util/mixin";
import { addBookToSelf } from "@/api/api";
// import lS from "@/util/localStorage";

export default {
  mixins: [ebookMixin],
  methods: {
    back() {
      this.$router.go(-1);
    },
    gotoBookStore() {
      this.$router.push("/self");
    },
    isInSelf() {
      for (let item of this.bookSelfList) {
        if (item.title == this.fileName) return false;
      }
      return true;
    },
    addToSlef() {
      // {selfId,bookId,fileName,title
      if (this.isLogin == false) {
        return;
      }
      addBookToSelf(this.title).then(Response => {
        if (Response.data.code == 200) {
          let book = {};
          book.bookId = this.title;
          book.fileName = this.bookId;
          book.title = this.fileName;
          this.bookSelfList.push(book);
        }
        return;
      });
      //
    }
  }
};
</script>

<style scoped>
.title-wrapper {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 200;
  display: flex;
  width: 100%;
  height: 64px;
  box-shadow: 0 1px 3px rgba(0, 25, 104, 0.3);
}
.book-title {
  margin: 0 auto;
}
.left {
  flex: 0 0 37px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.center {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.right {
  flex: 0 0 37px;
  display: flex;
  justify-content: flex-end;
}

.icon-wrapper {
  flex: 0 0 37px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.iconfont {
  height: 24px;
  width: 24px;
  font-size: 24px;
}
</style>
