<template>
  <div class="renderTargetContainer" @click="setCurrentComponentTxt()" v-bind:key="content">
    <div class="chapterContiner">
      <div
        v-html="responseText"
        v-bind:style="{fontSize:fontSize + 'px'}"
        v-lazy-container="{ selector: 'img'}"
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
import { getResource } from "@/api/api";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  data() {
    return {
      responseText: "",
      css: ""
    };
  },
  updated() {
    this.init();
  },

  mounted() {
    this.init();
  },
  methods: {
    setCurrentComponentTxt() {
      if (this.currentComponent == null)
        this.setCurrentComponent("readerControl");
      else this.setCurrentComponent(null);
    },
    init() {
      if ((this.bookID != "") & (this.content != ""))
        getResource(this.bookID, this.content).then(Response => {
          this.resolveCss(Response.data);
          for (let j = 0, len = this.css.length; j < len; j++) {
            this.addCss(
              this.API_TO_GET_VIEW + this.bookID + "&href=" + this.css[j]
            );
          }
          this.responseText = this.letslazyload(this.handleText(Response.data));
          this.loading = false;
        });
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
    // <link[^>]*href="[^"]*/(.+\.css)
    addCss(href) {
      const link = document.createElement("link");
      link.setAttribute("rel", "stylesheet");
      link.setAttribute("type", "text/css");
      link.setAttribute("href", href);
      document.getElementsByTagName("head")[0].appendChild(link);
    },

    resolveCss(text) {
      //let rule = /(?:<link[^>]*href="[^"]*\/)(.+\.css)/;
      let rule = /(?<=link.+href\s*="\W*)\w+.css/g;
      this.css = text.match(rule);
      //}
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
    find(text, cha, num) {
      var x = text.indexOf(cha);
      for (var i = 0; i < num; i++) {
        x = text.indexOf(cha, x + 1);
      }
      return x;
    },
    toNextChapter() {
      this.$message("da");
    }
  }
};
</script>

<style scoped>
/* .renderTargetContainer h1 {
  background-color: rgb(119, 119, 119);
  color: rgb(229, 100, 107);
}
.renderTargetContainer h2 {
  background-color: rgb(161, 161, 161);
  border-bottom: 1px solid #000;
}
.renderTargetContainer h3 {
  background-color: rgb(211, 211, 211);
} */
.renderTargetContainer img {
  max-width: 600px;
}
.renderTargetContainer {
  font-size: 16px;
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
.nextChapter button {
  display: block;
  width: 280px;
  height: 48px;
  margin: 0 auto;
  border: 0;
}
</style>
