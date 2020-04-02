import { mapGetters, mapActions } from "vuex";
import { API_TO_GET_VIEW, API_TO_GET_COVER, API_TO_GET_CSS } from "./settings";
import { THEME_DARK, THEME_LIGHT } from "./settings";
export const ebookMixin = {
  computed: {
    ...mapGetters([
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
      "bookSelfList"
    ])
  },
  data() {
    return {
      API_TO_GET_VIEW: API_TO_GET_VIEW,
      API_TO_GET_COVER: API_TO_GET_COVER,
      API_TO_GET_CSS: API_TO_GET_CSS,
      THEME_LIGHT: THEME_LIGHT,
      THEME_DARK: THEME_DARK
    };
  },

  methods: {
    ...mapActions([
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
      "setBookSelfList"
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
    }
  }
};
