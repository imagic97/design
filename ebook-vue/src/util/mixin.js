import { mapGetters, mapActions } from "vuex";
import { API_TO_GET_VIEW, API_TO_GET_COVER } from "./settings";
export const ebookMixin = {
  computed: {
    ...mapGetters([
      "fileName",
      "bookID",
      "content",
      "position",
      "nextPosition",
      "contentShow"
    ])
  },
  data() {
    return {
      API_TO_GET_VIEW: API_TO_GET_VIEW,
      API_TO_GET_COVER: API_TO_GET_COVER
    };
  },

  methods: {
    ...mapActions([
      "setFileName",
      "setBookID",
      "setContent",
      "setPosition",
      "setNextPosition",
      "setContentShow"
    ])
  }
};
