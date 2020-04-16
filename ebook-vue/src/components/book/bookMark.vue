<template>
  <div class="bookMark" v-if="menuVisible && menuShow === 3">
    <div class="bookMarkCatalog">
      <header class="component_title" @click="showOperater">
        书签
        <span class="iconfont icon-edit"></span>
      </header>

      <ul class="bookMarkCatalog_list" ref="bookMarkScroll">
        <li
          class="bookMarkItem"
          v-for="(item, key) in bookMarkList"
          :key="key"
          @click="toRead(item.chapters)"
        >
          <div class="content">
            <div class="book_mark title">
              {{ contentList[item.chapters.keyInContent].title }}
            </div>
            <div class="book_mark date">{{ item.createDate }}</div>
          </div>
          <div class="operater" v-show="isOperater">
            <span
              class="iconfont icon-delete"
              @click.stop="deleteBookMark(key, item.bookMarkId)"
            ></span>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import { getBookMark, deleteBookMark } from "@/api/api";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  data() {
    return {
      isOperater: false,
      bookMarkList: [],
    };
  },
  watch: {
    menuShow: function() {
      if (this.menuShow == 3) {
        this.init();
      }
    },
  },
  methods: {
    init() {
      if (this.bookID == "") return;
      getBookMark(this.bookID).then((Response) => {
        if (Response.data.code == 200) {
          let array = Response.data.result;
          for (let index = 0; index < array.length; index++) {
            array[index].chapters = JSON.parse(array[index].chapters);
          }
          this.bookMarkList = array;
        }
      });
    },
    toRead(chapter) {
      this.parsingBook(chapter);
      this.setMenuShowOrHide();
      this.isOperater = false;
    },
    showOperater() {
      this.isOperater = !this.isOperater;
    },
    deleteBookMark(key, bookMarkId) {
      deleteBookMark(bookMarkId).then((Response) => {
        if (Response.data.code == 200) {
          this.bookMarkList.splice(key, 1);
        }
      });
    },
  },
};
</script>

<style scoped>
.bookMarkCatalog {
  position: absolute;
  width: 100%;
  bottom: 64px;
  height: 57%;
  border-radius: 16px 16px 0 0;
  overflow: hidden;
  box-shadow: 0 -1px 3px rgba(0, 25, 104, 0.3);
}
.content {
  flex: 1;
}
.operater {
  margin: 0 20px;
}
.iconfont {
  font-size: 20px;
}
.icon-delete {
  color: red;
}

.component_title {
  font-size: 22px;
  margin: 12px 0;
  text-align: center;
}
.book_mark {
  font-size: 16px;
}
.date {
  font-size: 12px;
  line-height: 16px;
}

.bookMarkItem:hover {
  background: linear-gradient(90deg, rgba(0, 0, 0, 0.05), transparent);
}
.listSelected {
  color: #4a7ab0;
}

ul {
  overflow: auto;
  width: 100%;
  height: 100%;
}

li {
  -webkit-box-direction: normal;
  list-style: none;
  line-height: 52px;
  border-radius: 0;
  border: solid #ebedf1;
  overflow: hidden;
  border-width: 0 0 1px;
  padding: 0 0px 0 28px;
  display: flex;
}
#aa {
  display: block;
  width: 80%;
  height: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
