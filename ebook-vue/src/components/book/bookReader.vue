<template>
  <div class="renderTargetContainer" @click="setMenuShowOrHide()" v-bind:key="content">
    <div class="chapterContainer">
      <reader-context :responseText="responseText" v-bind:fontSize="fontSize" />
      <div class="nextChapterContainner">
        <div class="nextChapter">
          <el-button
            type="primary"
            v-if="responseText!=''&&this.keyInContent+1 < this.contentList.length"
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
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    readerContext
  },
  data() {
    return {
      //视图更新导致mounted周期加载
      ifUpdate: "",
      //响应文本
      responseText: "",
      //响应样式
      chapterCSS: ""
    };
  },
  updated() {
    //判断是否为api返回数据更新视图，避免二次更新视图
    if (this.ifUpdate == true) {
      this.ifUpdate = false;
      return;
    }
    this.init();
  },

  mounted() {
    this.init();
  },

  methods: {
    init() {
      if (this.content == "" && this.contentList.length > 0) {
        this.setContent = this.contentLiset[0].url;
      }
      if (this.bookID != "" && this.content != "") {
        getResource(this.bookID, this.content).then(Response => {
          // this.responseText = this.letslazyload(this.handleText(Response.data));
          this.responseText = this.handleText(Response.data);
          this.ifUpdate = true;
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

    // letslazyload(contentp) {
    //   let regexp1 = new RegExp(/(<img )([^>]*)(src=")([^"]*")([^>]*)(>)/, "g");
    //   contentp = contentp.replace(regexp1, function(
    //     match,
    //     p1,
    //     p2,
    //     p3,
    //     p4,
    //     p5,
    //     p6
    //   ) {
    //     return p1 + p2 + 'data-src="' + p4 + p5 + p6;
    //   });
    //   return contentp;
    // },

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
      //"../"出现的次数
      /* eslint-disable */
      var imgIndex = -1;
      var htmlIndex = -1;
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
    handleText(text) {
      text = text.slice(text.indexOf("<body"));
      if (this.position != "" && text.search(this.position) > -1) {
        text = text.slice(text.indexOf(this.position) - 8);
      }
      if (
        text.search(this.nextPosition) >= 0 &&
        this.nextPosition !== "" &&
        this.nextPosition != this.position
      ) {
        text = text.slice(0, text.indexOf(this.nextPosition) - 8);
      }
      text = text.slice(0, text.indexOf("</body>"));
      // }
      let fun = this.getImgPath;
      let content = this.content;
      let API_TO_GET_VIEW = this.API_TO_GET_VIEW;
      let bookID = this.bookID;
      let regexp = new RegExp(/(<img )([^>]*)(src=")([^"]*")([^>]*)(>)/, "g");
      text = text.replace(regexp, function(match, p1, p2, p3, p4, p5, p6) {
        p4 = fun(p4, content);
        return (
          p1 +
          p2 +
          'data-src="' +
          API_TO_GET_VIEW +
          bookID +
          `&href=` +
          p4 +
          p5 +
          p6
        );
      });

      // if (text.search('src="../') > -1) {
      //   //不能加引号，否则替换不生效，i忽略大小写，g表示全局查找。
      //   text = text.replace(
      //     /src="/gi,
      //     // /src="..\/Images\//gi,
      //     `src="` + this.API_TO_GET_VIEW + this.bookID + `&href=`
      //   );
      // } else if (text.search('src="') > -1) {
      //   text = text.replace(
      //     /src="/gi,
      //     `src="` + this.API_TO_GET_VIEW + this.bookID + `&href=`
      //   );
      // }
      return text;
    },
    toNextChapter() {
      let key = this.keyInContent + 1;
      if (key >= this.contentList.length) return;
      var nextPosition = "";
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
/* .renderTargetContainer pre {
  background-color: #e6e6e6;
} */
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
