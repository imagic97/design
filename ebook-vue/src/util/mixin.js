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
      "currentComponent",
      "fontSize"
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
      "setCurrentComponent",
      "setFontSize"
    ])
  }
};
