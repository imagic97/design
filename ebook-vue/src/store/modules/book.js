const book = {
  state: {
    //电子书在数据库ID
    title: "",

    //电子书名
    fileName: "",

    //电子书对应文件
    bookID: "",

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
    menuShow: -1,
    keyInContent: 0,
    menuVisible: false,
    fontSize: "16",
    isLogin: "",
    userName: "",
    bookSelfList: [],
    offsetY: 0,
  },
  mutations: {
    SET_TITLE: (state, title) => {
      state.title = title;
    },
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
    SET_KEYINCONTENT: (state, keyInContent) => {
      state.keyInContent = keyInContent;
    },
    SET_FONTSIZE: (state, fontSize) => {
      state.fontSize = fontSize;
    },
    SET_MENUVISIBLE: (state, menuVisible) => {
      state.menuVisible = menuVisible;
    },
    SET_ISLOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_USERNAME: (state, userName) => {
      state.userName = userName;
    },
    SET_BOOKSELFLIST: (state, bookSelfList) => {
      state.bookSelfList = bookSelfList;
    },
    SET_OFFSETY: (state, offsetY) => {
      state.offsetY = offsetY;
    },
  },
};

export default book;
