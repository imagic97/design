import { mapGetters, mapActions } from "vuex";
import { API_TO_GET_VIEW, API_TO_GET_COVER } from "./settings";
import { THEME_DARK, THEME_LIGHT } from "./settings";
export const ebookMixin = {
  computed: {
    ...mapGetters([
      "title",
      "fileName",
      "bookID",
      "content",
      "position",
      "nextPosition",
      "contentShow",
      "contentList",
      "menuShow",
      "keyInContent",
      "fontSize",
      "menuVisible",
      "isLogin",
      "userName",
      "bookSelfList",
      "offsetY",
    ]),
  },
  data() {
    return {
      API_TO_GET_VIEW: API_TO_GET_VIEW,
      API_TO_GET_COVER: API_TO_GET_COVER,
      THEME_LIGHT: THEME_LIGHT,
      THEME_DARK: THEME_DARK,
    };
  },

  methods: {
    ...mapActions([
      "setTitle",
      "setFileName",
      "setBookID",
      "setContent",
      "setPosition",
      "setNextPosition",
      "setContentShow",
      "setContentList",
      "setMenuShow",
      "setKeyInContent",
      "setFontSize",
      "setMenuVisible",
      "setIsLogin",
      "setUserName",
      "setBookSelfList",
      "setOffsetY",
    ]),
    setMenuShowOrHide() {
      this.setMenuShow(-1), this.setMenuVisible(!this.menuVisible);
    },
    //获取以"#"为章节分割符后的定位字符串，无则返回""
    getPosition(url) {
      var str = "";
      if (url.search("#") > 0) {
        str = url.slice(url.indexOf("#") + 1, url.length);
      }
      return str;
    },
    //挂载电子书CSS样式
    CreateStyle(styleText, elementID) {
      const style = document.createElement("style");
      style.setAttribute("type", "text/css");
      style.setAttribute("id", elementID);
      style.innerText = styleText;
      document.getElementsByTagName("head")[0].appendChild(style);
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
    },
  },
};
