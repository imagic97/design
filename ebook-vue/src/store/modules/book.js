const book = {
  state: {
    fileName: "",
    bookID: "7.epub",
    //单章节
    content: "",
    //当前章节定位
    position: "",
    //下一章节定位
    nextPosition: "",
    //目录列表显示
    contentShow: false,
    //目录列表
    contentList: [],
    //菜单列表显示
    menuShow: false,
    currentComponent: "contentItem",
    fontSize: 16
  },
  mutations: {
    SET_FILENAME: (state, fileName) => {
      state.fileName = fileName;
    },
    SET_BOOKID: (state, bookID) => {
      state.bookID = bookID;
    },
    SET_CONTENT: (state, content) => {
      state.content = content;
    },
    SET_POSITION: (state, position) => {
      state.position = position;
    },
    SET_NEXTPOSITION: (state, nextPosition) => {
      state.nextPosition = nextPosition;
    },
    SET_CONTENTSHOW: (state, contentShow) => {
      state.contentShow = contentShow;
    },
    SET_CONTENTLIST: (state, contentList) => {
      state.contentList = contentList;
    },
    SET_MENUSHOW: (state, menuShow) => {
      state.menuShow = menuShow;
    },
    SET_CURRENTCOMPONENT: (state, currentComponent) => {
      state.currentComponent = currentComponent;
    },
    SET_FONTSIZE: (state, fontSize) => {
      state.fontSize = fontSize;
    }
  }
};

export default book;
