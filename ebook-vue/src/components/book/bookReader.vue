<template>
  <div class="renderTargetContainer" @click="setMenuShowOrHide()">
    <loading v-show="isLoading" />
    <div class="chapterContainer" v-show="!isLoading">
      <reader-context :responseHtml="responseHtml" v-bind:fontSize="fontSize" />
      <div class="nextChapterContainner">
        <div class="nextChapter">
          <el-button
            type="primary"
            v-if="responseHtml!=''&&this.keyInContent+1 < this.contentList.length"
            @click.stop="toNextChapter"
          >下一章</el-button>
        </div>
      </div>
    </div>
    <div></div>
  </div>
</template>
<script>
import { getResource, getChapterCSS } from "@/api/api";
import readerContext from "../book/bookReaderContext";
import loading from "@/components/common/loading";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    readerContext,
    loading
  },
  data() {
    return {
      isLoading: true,
      //响应文本
      responseHtml: "",
      //响应样式
      chapterCSS: ""
    };
  },

  watch: {
    content: function() {
      this.init();
    }
  },

  mounted() {
    this.init();
  },

  methods: {
    init() {
      this.isLoading = true;
      if (this.content == "" && this.contentList.length > 0) {
        this.setContent = this.contentLiset[0].url;
      }
      if (this.bookID != "" && this.content != "") {
        getResource(this.bookID, this.content).then(Response => {
          this.responseHtml = this.handleHtml(Response.data);
          this.$nextTick(() => {
            this.isLoading = false;
          });
        });
      }
      if (this.chapterCSS == "")
        getChapterCSS(this.bookID).then(Response => {
          if (Response.data.code == 200) {
            this.chapterCSS = Response.data.result;
            //添加章节样式容器限制
            this.chapterCSS =
              ".chapterContiner " +
              this.chapterCSS.replace(/}/gi, "}.chapterContainer ");
            //去除原本CSS字体大小
            this.chapterCSS = this.chapterCSS.replace(
              /font-size:.{3,8};/gi,
              ""
            );
            this.chapterCSS += " a {pointer-events:none;}";
            //去除换行
            this.chapterCSS = this.chapterCSS.replace(/\r|\n/gi, "");
            //移除已加载style节点并重新挂载
            let removeDom = document.getElementById("chapterCSS");
            if (removeDom != null) {
              removeDom.parentNode.removeChild(removeDom);
            }
            this.CreateStyle(this.chapterCSS);
          }
        });
    },

    //挂载电子书CSS样式
    CreateStyle(styleText) {
      const style = document.createElement("style");
      style.setAttribute("type", "text/css");
      style.setAttribute("id", "chapterCSS");
      style.innerText = styleText;
      document.getElementsByTagName("head")[0].appendChild(style);
    },

    //获取并生成图片请求地址
    getImgPath(sourcePath, htmlPath) {
      let imgIndex = -1;
      let htmlIndex = -1;
      htmlPath = htmlPath.slice(0, htmlPath.lastIndexOf("/"));
      while ((imgIndex = sourcePath.indexOf("../") > -1)) {
        sourcePath = sourcePath.slice(imgIndex + 2, sourcePath.length);
        htmlIndex = htmlPath.lastIndexOf("/");
        htmlPath = htmlPath.slice(0, htmlIndex);
      }
      if (htmlIndex < 0) return sourcePath;
      return htmlPath + "/" + sourcePath;
    },

    //对书籍文本进行分割，替换img路径
    handleHtml(html) {
      html = html.slice(html.indexOf("<body"));
      if (this.position != "" && html.search(this.position) > -1) {
        html = html.slice(html.indexOf(this.position) - 8);
      }
      if (
        html.search(this.nextPosition) >= 0 &&
        this.nextPosition !== "" &&
        this.nextPosition != this.position
      ) {
        html = html.slice(0, html.indexOf(this.nextPosition) - 8);
      }
      html = html.slice(0, html.indexOf("</body>"));

      let regexp = new RegExp(/(<img )([^>]*)(src=")([^"]*")([^>]*)(>)/, "g");
      html = html.replace(regexp, (match, p1, p2, p3, p4, p5, p6) => {
        p4 = this.getImgPath(p4, this.content);
        return (
          p1 +
          p2 +
          `data-src="` +
          this.API_TO_GET_VIEW +
          this.bookID +
          `&href=` +
          p4 +
          p5 +
          p6
        );
      });
      return html;
    },
    toNextChapter() {
      let key = this.keyInContent + 1;
      if (key >= this.contentList.length) return;
      let nextPosition = "";
      if (key + 1 < this.contentList.length) {
        nextPosition = this.getPosition(this.contentList[key + 1].url);
      }
      this.setContent(this.contentList[key].url);
      this.setKeyInContent(key);
      this.setPosition(this.nextPosition);
      this.setNextPosition(nextPosition);
    }
  }
};
</script>

<style scoped>
.renderTargetContainer img {
  max-width: 600px;
}
.renderTargetContainer {
  font-size: 16px;
  padding: 17px 0 0 0;
  position: absolute;
  width: 100%;
  height: 100%;
  min-height: 450px;
  overflow: auto;
}

.renderTargetContainer::-webkit-scrollbar {
  width: 0 !important;
}

.nextChapterContainner {
  width: 100%;
  margin: 0 auto;
}

#text {
  padding: 0 14px;
}

.nextChapter {
  padding: 40px 0 126px 0;
}

.nextChapter button {
  display: block;
  width: 280px;
  height: 48px;
  margin: 0 auto;
  border: 0;
}
</style>
