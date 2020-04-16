<template>
  <div class="content" v-show="menuVisible && menuShow === 1">
    <div class="contentCatalog">
      <header class="component_title">目录</header>
      <ul class="contentCatalog_list" ref="contentScroll">
        <li
          class="chapterItem"
          v-for="(item, key) in contentList"
          :key="key"
          v-on:click="ContentToReader(item, key)"
        >
          <div
            id="aa"
            v-bind:class="{ listSelected: isCurrentContent(item.url) }"
          >
            {{ item.title }}
          </div>
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
  mounted() {
    this.init();
  },
  watch: {
    menuShow: function() {
      if (this.menuShow === 1) {
        setTimeout(() => {
          this.$refs.contentScroll.scrollTo(0, 52 * this.keyInContent);
        }, 10);
      }
    },
  },

  methods: {
    isCurrentContent(value) {
      return this.content === value;
    },
    setCurrentComponentHide() {
      this.setCurrentComponent(null);
    },
    init() {
      this.setContentList([]);
      getContent(this.fileName).then((Response) => {
        if (Response.data.result == null) {
          this.setContentList([]);
          return;
        }
        this.getJsonList(Response.data.result.children);
        if (this.content == "") {
          this.setContent(this.contentList[0].url);
          this.setNextPosition(this.getPosition(this.contentList[1].url));
        }
      });
    },
    //递归Json获取顺序目录列表
    getJsonList(contentJson) {
      for (var i = 0; i < contentJson.length; i++) {
        this.contentList.push({
          title: contentJson[i].title,
          url: contentJson[i].url,
        });
        if (contentJson[i].children.length > 0) {
          //获取子目录内容
          this.getJsonList(contentJson[i].children);
        }
      }
    },
    ContentToReader(content, key) {
      //判断点击章节url是否为空，是则返回
      if (content.url == "") return;
      this.key = key;
      //分割章节定位
      var position = this.getPosition(content.url);
      var nextPosition = "";
      if (key + 1 < this.contentList.length) {
        nextPosition = this.getPosition(this.contentList[key + 1].url);
      }
      //判断当前章节、定位是否和点击章节一致
      if (this.content != content.url) {
        this.setContent(content.url);
        this.setPosition(position);
        //获取下一个章节定位
        this.setNextPosition(nextPosition);
        this.setKeyInContent(key);
      }
      this.setOffsetY(0);
      this.setMenuShowOrHide();
    },
  },
};
</script>

<style scoped>
.contentCatalog {
  position: absolute;
  width: 100%;
  bottom: 64px;
  height: 57%;
  border-radius: 16px 16px 0 0;
  overflow: hidden;
  box-shadow: 0 -1px 3px rgba(0, 25, 104, 0.3);
}

.component_title {
  font-size: 22px;
  margin: 12px 0;

  text-align: center;
}

.chapterItem:hover {
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
  height: 52px;
  line-height: 52px;
  border-radius: 0;
  border: solid #ebedf1;
  overflow: hidden;
  border-width: 0 0 1px;
  padding: 0 0px 0 28px;
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
