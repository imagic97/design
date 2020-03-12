<template>
  <div class="renderTargetContainer" @click="setCurrentComponentTxt()" v-bind:key="content">
    <div class="chapterContainer">
      <div
        id="text"
        v-html="responseText"
        :style="{'fontSize':fontSize+'px','lineHeight':1.8*fontSize+'px'}"
        v-lazy-container="{ selector: 'img' }"
      ></div>
      <div class="nextChapterContainner">
        <div class="nextChapter">
          <el-button type="primary" v-if="responseText" @click.stop="toNextChapter">下一章</el-button>
        </div>
      </div>
    </div>
    <div></div>
  </div>
</template>
<script>
import { getResource, getChapterCSS } from "@/api/api";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  data() {
    return {
      responseText: "",
      chapterCSS: ""
    };
  },
  updated() {
    this.init();
  },

  mounted() {
    // this.addCss(this.API_TO_GET_CSS + this.bookID);
    /* eslint-disable */

    this.init();
  },
  methods: {
    setCurrentComponentTxt() {
      if (this.currentComponent == null)
        this.setCurrentComponent("readerControl");
      else this.setCurrentComponent(null);
    },
    init() {
      if ((this.bookID != "") & (this.content != "")) {
        getResource(this.bookID, this.content).then(Response => {
          this.responseText = this.letslazyload(this.handleText(Response.data));
        });
        getChapterCSS(this.bookID).then(Response => {
          if (Response.data.code == 200) {
            this.chapterCSS = Response.data.result;
            //添加章节样式限制
            this.chapterCSS =
              ".chapterContiner " +
              this.chapterCSS.replace(/}/gi, "}.chapterContainer ");
            //去除换行
            this.chapterCSS = this.chapterCSS.replace(/\r\n/gi, "");
            //移除style节点
            let removeDom = document.getElementById("chapterCSS");
            if (removeDom != null) {
              removeDom.parentNode.removeChild(removeDom);
            }
            this.CreateStyle();
          }
        });
      }
    },

    letslazyload(contentp) {
      let regexp1 = new RegExp(/(<img )([^>]*)(src=")([^"]*")([^>]*)(>)/, "g");
      contentp = contentp.replace(regexp1, function(
        match,
        p1,
        p2,
        p3,
        p4,
        p5,
        p6
      ) {
        return p1 + p2 + 'data-src="' + p4 + p5 + p6;
      });
      return contentp;
    },
    CreateStyle() {
      const style = document.createElement("style");
      style.setAttribute("type", "text/css");
      style.setAttribute("id", "chapterCSS");
      style.innerText = this.chapterCSS;
      document.getElementsByTagName("head")[0].appendChild(style);
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
      if (text.search('src="../') > -1) {
        //不能加引号，否则替换不生效，i忽略大小写，g表示全局查找。
        text = text.replace(
          /src="..\/Images\//gi,
          `src="` + this.API_TO_GET_VIEW + this.bookID + `&href=`
        );
      } else if (text.search('src="') > -1) {
        text = text.replace(
          /src="/gi,
          `src="` + this.API_TO_GET_VIEW + this.bookID + `&href=`
        );
      }
      return text;
    },
    toNextChapter() {
      this.$message("da");
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
