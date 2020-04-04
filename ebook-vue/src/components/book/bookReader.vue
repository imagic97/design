<template>
  <div class="renderTargetContainer" ref="scroll" @click="setMenuShowOrHide()">
    <div class="chapterContainer">
      <reader-context :responseHtml="responseHtml" v-bind:fontSize="fontSize" />
      <div class="nextChapterContainner">
        <div class="nextChapter">
          <button
            type="primary"
            v-if="
              responseHtml != '' &&
                this.keyInContent + 1 < this.contentList.length
            "
            @click.stop="toNextChapter()"
          >下一章</button>
        </div>
      </div>
    </div>
    <div></div>
  </div>
</template>
<script>
import { getResource, getChapterCSS } from "@/api/api";
import readerContext from "../book/bookReaderContext";
import { ebookMixin } from "@/util/mixin";
import lS from "@/util/localStorage";
import VE from "@/util/vueEvent";
export default {
  mixins: [ebookMixin],
  components: {
    readerContext
  },
  data() {
    return {
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
    this.$refs.scroll.addEventListener("scroll", () => {
      this.setOffsetY(this.$refs.scroll.scrollTop);
      // 滚动事件
      let book = this.createBook();
      lS.set(this.fileName, JSON.stringify(book));
      lS.set("currentRead", JSON.stringify(book));
    });
  },

  created() {
    let book;
    if (this.fileName === "") {
      book = JSON.parse(lS.get("currentRead"));
    } else {
      book = JSON.parse(lS.get(this.fileName));
    }
    if (book == null) return;
    this.parsingBook(book);
  },

  methods: {
    init() {
      if (this.content == "" && this.contentList.length > 0) {
        this.setContent = this.contentLiset[0].url;
      }
      if (this.fileName != "" && this.content != "") {
        VE.$emit("isLoading", true);
        getResource(this.fileName, this.content)
          .then(Response => {
            this.responseHtml = this.handleHtml(Response.data);
            this.$nextTick(() => {
              this.isLoading = false;
              this.$refs.scroll.scrollTo(0, this.offsetY);
              let book = this.createBook();
              lS.set(this.fileName, JSON.stringify(book));
              lS.set("currentRead", JSON.stringify(book));
              //隐藏加载图
              VE.$emit("isLoading", false);
            });
          })
          .catch(() => {
            this.tips_b = "请检查网络";
            VE.$emit("isLoading", false);
          });
      }
      //组件第一次加载加载电子书样式
      if (this.chapterCSS == "")
        getChapterCSS(this.fileName).then(Response => {
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

            this.chapterCSS += ` a {pointer-events:none;} .chapterContainer img {max-width: 100%;max-height: 100%;display: block;}`;
            //去除换行
            this.chapterCSS = this.chapterCSS.replace(/\r|\n/gi, "");
            //移除已加载style节点并重新挂载
            let removeDom = document.getElementById("chapterCSS");
            if (removeDom != null) {
              removeDom.parentNode.removeChild(removeDom);
            }
            this.CreateStyle(this.chapterCSS, "chapterCSS");
          }
        });
    },

    //获取并生成图片请求地址
    getImgPath(sourcePath, htmlPath) {
      let imgIndex = -1;
      let htmlIndex = -1;
      if (sourcePath.indexOf("../") < 0 && htmlPath.indexOf("/") > 0) {
        htmlPath = htmlPath.slice(0, htmlPath.lastIndexOf("/"));
        return htmlPath + "/" + sourcePath;
      }

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
        let tamp = html.slice(0, html.indexOf(this.position));
        tamp = tamp.slice(tamp.lastIndexOf("<"));
        html = tamp + html.slice(html.indexOf(this.position));
      }
      if (
        html.search(this.nextPosition) >= 0 &&
        this.nextPosition !== "" &&
        this.nextPosition != this.position
      ) {
        html = html.slice(0, html.indexOf(this.nextPosition));
        html = html.slice(0, html.lastIndexOf(">") + 1);
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
          this.fileName +
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
      this.setOffsetY(0);
      this.setContent(this.contentList[key].url);
      this.setKeyInContent(key);
      this.setPosition(this.nextPosition);
      this.setNextPosition(nextPosition);
    },

    //将store状态信息生成book对象
    createBook() {
      let book = {};
      book.bookID = this.bookID;
      book.content = this.content;
      book.fileName = this.fileName;
      book.keyInContent = this.keyInContent;
      book.nextPosition = this.nextPosition;
      book.position = this.position;
      book.title = this.title;
      book.offsetY = this.offsetY;
      return book;
    },
    //将book对象解析store状态
    parsingBook(book) {
      this.setBookID(book.bookID);
      this.setContent(book.content);
      this.setFileName(book.fileName);
      this.setKeyInContent(book.keyInContent);
      this.setNextPosition(book.nextPosition);
      this.setPosition(book.position);
      this.setTitle(book.title);
      this.setOffsetY(book.offsetY ? book.offsetY : 0);
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
  border-radius: 10px;
  background-color: #409eff;
  outline: none;
}
</style>
