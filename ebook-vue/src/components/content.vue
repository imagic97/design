<template>
  <div id="readerCatalog">
    <!-- <a href class="readerCatalog_bookInfo">
      <div class="readerCatalog_bookInfo_cover">
        <img src alt="书籍封面" class="bookCover_img" />
        <div class="wr_bookCover_border"></div>
        <span class="wr_bookCover_decor wr_bookCover_gradientDecor wr_bookCover_borderDecor"></span>
      </div>
      <div class="readerCatalog_bookInfo_right">
        <h2 class="readerCatalog_bookInfo_title">
          <span class="readerCatalog_bookInfo_title_txt">Java编程的逻辑</span>
          <span class="readerCatalog_bookInfo_title_arrow"></span>
        </h2>
        <div class="readerCatalog_bookInfo_author">马俊昌</div>
    
      </div>
    </a>-->
    <ul class="readerCatalog_list">
      <li
        class="chapterItem"
        v-for="(item,key) in truthList"
        :key="key"
        v-on:click="ContentToReader(item,key)"
      >
        <span>{{item.title}}</span>
      </li>
    </ul>
  </div>
</template>
<script>
import { getContent } from "@/api/api";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  data() {
    return {
      key: "",
      contentList: "",
      truthList: []
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      getContent(this.bookID).then(Response => {
        this.contentList = Response.data;
        if (this.contentList == null) this.contentList = "目录为空哦";
        this.getJsonList(this.contentList.children);
      });
    },
    getJsonList(contentJson) {
      for (var i = 0; i < contentJson.length; i++) {
        this.truthList.push({
          title: contentJson[i].title,
          url: contentJson[i].url
        });
        if (contentJson[i].children.length > 0) {
          this.getJsonList(contentJson[i].children);
        }
      }
    },
    ContentToReader(content, key) {
      //判断点击章节url是否为空，是则返回
      if (content.url == "") return;
      this.key = key;
      //分割章节定位
      var position = "";
      var nextPosition = "";
      //     var nextPosition =
      // key + 1 < this.truthList.length
      //   ? this.truthList[key + 1].url.slice(
      //       content.url.indexOf("#") + 1,
      //       content.url.length
      //     )
      //   : "";

      if (content.url.search("#") > 0) {
        position = content.url.slice(
          content.url.indexOf("#") + 1,
          content.url.length
        );
        if (
          key + 1 < this.truthList.length &&
          this.truthList[key + 1].url.search("#") > 0
        ) {
          nextPosition = this.truthList[key + 1].url.slice(
            content.url.indexOf("#") + 1,
            content.url.length
          );
        }
      }

      //判断当前章节、定位是否和点击章节一致
      if ((this.content != content.url) | (this.position != position)) {
        this.setContent(content.url);
        this.setPosition(position);
        //获取下一个章节定位
        this.setNextPosition(nextPosition);
      }

      this.setContentShow(false);
    }
  }
};
</script>

<style scoped>
#readerCatalog {
  width: 520px;
  position: absolute;
  height: 100%;
  overflow: auto;
  right: 0;
}

.chapterItem:hover {
  background: linear-gradient(90deg, rgba(0, 0, 0, 0.05), transparent);
}

ul {
  -webkit-box-flex: 1;
  flex: auto;
  overflow: auto;
  padding-bottom: env(safe-area-inset-bottom);
  width: 520px;
  position: absolute;
  height: 90%;
}
li {
  -webkit-box-direction: normal;
  list-style: none;
  height: 52px;
  line-height: 52px;
  padding-left: 36px;
  padding-right: 36px;
  border-radius: 0;
  border: solid #ebedf1;
  border-width: 0 0 1px;
}
</style>