<template>
  <div class="userBookSelfContainer">
    <div class="bookSelf">
      <div
        class="bookSelfList"
        v-for="(item, key) in bookSelfList"
        :key="key"
        @click="selectedMode ? selectBook(key) : toReaderBook(item)"
      >
        <div class="bookCover">
          <!-- :key="item.bookID" -->
          <img v-lazy="API_TO_GET_COVER + item.fileName"  class="bookCover_img" />
          <div
            v-show="selectedMode"
            v-bind:class="{ selected: item.isSelected }"
            class="iconfont icon-selected"
          ></div>
          <div class="book_cover_boder"></div>
        </div>
        <div class="title">{{ item.title ? item.title : "无书名" }}</div>
      </div>
      <div class="addBook placeholder" @click="showUpload()">
        <div class="iconfont icon-add"></div>
      </div>
      <!-- 占位作用，防止组件拉伸 -->
      <div class="placeholder" v-for="n in placeholder" :key="`num-` + n"></div>
    </div>
  </div>
</template>
<script>
import { ebookMixin } from "@/util/mixin";
import { getUserSelf, deleteBook, deleteBookFromSelf } from "@/api/api";
import VE from "@/util/vueEvent";
import lS from "@/util/localStorage";

export default {
  mixins: [ebookMixin],

  data() {
    return {
      // bookSelfList: [],
      selectedMode: false,
      placeholder: 4
    };
  },

  watch: {
    isLogin: function() {
      if (this.isLogin == "") {
        this.bookSelfList = [];
      }
    }
  },
  mounted() {
    VE.$on("SELECTMODE", value => {
      this.selectedMode = value;
      if (value === false) {
        this.selectCancel(this.bookSelfList);
      }
    });
    VE.$on("DELETEBOOK", value => {
      if (value === true) this.deleteBook(this.bookSelfList);
    });
    let num = window.innerWidth > 870 ? 850 : window.innerWidth;
    this.placeholder = parseInt(num / 150) - 1;
    this.init();
  },

  methods: {
    //初始化书架
    init() {
      //用户已登陆，从网络获取书架
      if (this.isLogin != null && this.bookSelfList.length == 0) {
        getUserSelf().then(Response => {
          if (Response.data.code == 200)
            // {selfId,bookId,fileName,title
            for (let item of Response.data.result) {
              item.isSelected = false;
              this.bookSelfList.push(item);
            }
        });
      }
    },
    //去阅读页面
    toReaderBook(book) {
      this.setBookID(book.bookId);
      this.setFileName(book.fileName);
      this.setTitle(book.title);
      this.$router.push({ path: "/book-reader" });
    },
    //显示上传
    showUpload() {
      this.$emit("showUpload", true);
    },
    //从书架选择书
    selectBook(key) {
      this.bookSelfList[key].isSelected = !this.bookSelfList[key].isSelected;
    },
    //删除书架的书
    deleteBook(array) {
      for (let i = array.length - 1; i >= 0; i = i - 1) {
        if (array[i].isSelected === true) {
          if (array[i].selfId === 0) {
            //用户自己上传书
            deleteBook(array[i].bookId, array[i].fileName);
          } else {
            //书城的书
            deleteBookFromSelf(array[i].selfId);
          }
          lS.delete(array[i].fileName);
          array.splice(i, 1);
        }
      }
      return array;
    },

    //取消选择
    selectCancel(array) {
      for (let item of array) {
        item.isSelected = false;
      }
      return array;
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
  margin-top: 8px;
  line-height: 18px;
  overflow: hidden;
  height: 36px;
  text-overflow: ellipsis;
}

.icon-selected {
  position: absolute;
  bottom: 5px;
  right: 5px;
  font-size: 24px;
  border-radius: 15px;
  color: rgba(0, 0, 0, 0.4);
}

.icon-add {
  font-size: 36px;
  text-align: center;
  color: rgb(125, 129, 136);
}

.selected {
  color: blue;
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
  box-shadow: 3px 3px 3px rgba(0, 25, 104, 0.3);
}

@media screen and (min-width: 500px) {
  .bookCover:hover {
    z-index: 99;
    transform: scale(1.3);
  }
}
.addBook {
  background-color: #e0e0e0;
  display: flex;
  align-items: center;
  justify-content: center;
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
    margin-bottom: 60px;
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
