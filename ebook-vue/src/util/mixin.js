import { mapGetters, mapActions } from "vuex";
import { API_TO_GET_VIEW, API_TO_GET_COVER, API_TO_GET_CSS } from "./settings";
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
      "menuVisible"
    ])
  },
  data() {
    return {
      API_TO_GET_VIEW: API_TO_GET_VIEW,
      API_TO_GET_COVER: API_TO_GET_COVER,
      API_TO_GET_CSS: API_TO_GET_CSS
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
      "setMenuVisible"
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
    }
  }
};
