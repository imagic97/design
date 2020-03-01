<template>
  <div class="renderTargetContainer" v-bind:key="content">
    <div v-html="responseText" v-lazy-container="{ selector: 'img'}"></div>
  </div>
</template>
<script>
import { getResource } from "@/api/api";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  data() {
    return {
      responseText: "别着急，喝口茶慢慢等...",
      css: []
    };
  },
  updated() {
    for (let j = 0, len = this.css.length; j < len; j++) {
      this.addCss(this.API_TO_GET_VIEW + this.bookID + "&href=" + this.css[j]);
    }
    this.init();
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      if ((this.bookID != "") & (this.content != ""))
        getResource(this.bookID, this.content).then(Response => {
          this.resolveCss(Response.data);
          this.responseText = this.letslazyload(this.handleText(Response.data));
        });
      // getResource(this.bookID, "stylesheet.css").then(Response => {
      //   this.css += Response.data;
      // });
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
      //在对象中创建一个对象，要与appendChild() 或 insertBefore()方法联合使用
      const link = document.createElement("link");
      //link标签设置rel、type、href属性
      link.setAttribute("rel", "stylesheet");
      link.setAttribute("type", "text/css");
      link.setAttribute("href", href);
      //getElementsByTagName返回指定标签名的集合
      //appendChild在指定节点后添加最后一个节点，将link标签添加至head标签下
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
        if (
          text.search(this.nextPosition) >= 0 &&
          this.nextPosition !== "" &&
          this.nextPosition != this.position
        ) {
          text = text.slice(0, text.indexOf(this.nextPosition) - 8);
        }
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

      // if (text.search("<h1>") > 0 && this.position == "") {
      //   if (find(text, "<h2", 3) > 0) {
      //     text = text.slice(text.indexOf("<h2"), text.length);
      //     text = text.slice(0, this.find(text, "<h2", 3));
      //   } else {
      //     text = text.slice(text.indexOf("<h1"), text.length);
      //     text = text.slice(0, this.find(text, "<h1", 2));
      //   }
      // }
      return text;
    },
    find(text, cha, num) {
      var x = text.indexOf(cha);
      for (var i = 0; i < num; i++) {
        x = text.indexOf(cha, x + 1);
      }
      return x;
    }
  }
};
</script>

<style>
.renderTargetContainer {
  background-color: white;
}

.renderTargetContainer h1 {
  background-color: rgb(192, 189, 189);
  color: rgb(229, 100, 107);
}
.renderTargetContainer h2 {
  background-color: rgb(185, 129, 13);
  border-bottom: 1px solid #000;
}
.renderTargetContainer h3 {
  background-color: rgb(192, 189, 189);
}
.renderTargetContainer img {
  max-width: 600px;
}
.renderTargetContainer {
  position: absolute;
  width: 1000px;
  height: 100%;
  position: absolute;
  left: 50%;
  margin-left: -563px;
  padding: 0px 55px;
  overflow: auto;
  border-left: 1px solid rgb(192, 189, 189);
  border-right: 1px solid rgb(192, 189, 189);
}
.renderTargetContainer pre {
  background-color: #e6e6e6;
}
</style>
