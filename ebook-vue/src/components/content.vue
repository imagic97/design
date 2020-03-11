<template>
  <div class="content">
    <div class="contentCatalog">
      <ul class="contentCatalog_list">
        <li
          class="chapterItem"
          v-for="(item, key) in contentList"
          :key="key"
          v-on:click="ContentToReader(item, key)"
        >
          <span v-bind:class="{'selected':isCurrentContent(item.url)}">{{ item.title }}</span>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import { getContent } from "@/api/api";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  data() {
    return {
      key: ""
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    isCurrentContent(value) {
      return this.content === value;
    },
    setCurrentComponentHide() {
      this.setCurrentComponent(null);
    },
    init() {
      getContent(this.bookID).then(Response => {
        if (Response.data.result == null) {
          this.contentList = "目录为空哦";
          return;
        }
        this.getJsonList(Response.data.result.children);
        if (this.content == "") this.setContent(this.contentList[0].url);
      });
    },
    getJsonList(contentJson) {
      for (var i = 0; i < contentJson.length; i++) {
        this.contentList.push({
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
      if (content.url.search("#") > 0) {
        position = content.url.slice(
          content.url.indexOf("#") + 1,
          content.url.length
        );
      }

      if (
        key + 1 < this.contentList.length &&
        this.contentList[key + 1].url.search("#") > 0
      ) {
        nextPosition = this.contentList[key + 1].url.slice(
          this.contentList[key + 1].url.indexOf("#") + 1,
          this.contentList[key + 1].url.length
        );
      }
      //判断当前章节、定位是否和点击章节一致
      if ((this.content != content.url) | (this.position != position)) {
        this.setContent(content.url);
        this.setPosition(position);
        //获取下一个章节定位
        this.setNextPosition(nextPosition);
      }
      this.setCurrentComponentHide();
    }
  }
};
</script>

<style scoped>
.contentCatalog {
  position: absolute;
  width: 90%;
  bottom: 0;
  height: 55%;
  overflow: hidden;
  padding: 0 0 0 10%;
  /* background-color: #fff; */
}

.chapterItem:hover {
  background: linear-gradient(90deg, rgba(0, 0, 0, 0.05), transparent);
}
.selected {
  color: darkblue;
}

ul {
  overflow: auto;
  width: 100%;
  height: 100%;
}

ul::-webkit-scrollbar {
  width: 0 !important;
}
li {
  -webkit-box-direction: normal;
  list-style: none;
  height: 52px;
  line-height: 52px;
  border-radius: 0;
  border: solid #ebedf1;
  border-width: 0 0 1px;
}
</style>
