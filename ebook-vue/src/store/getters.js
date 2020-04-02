const getters = {
  fileName: state => state.book.fileName,
  bookID: state => state.book.bookID,
  content: state => state.book.content,
  position: state => state.book.position,
  nextPosition: state => state.book.nextPosition,
  contentShow: state => state.book.contentShow,
  contentList: state => state.book.contentList,
  menuShow: state => state.book.menuShow,
  currentComponent: state => state.book.currentComponent,
  fontSize: state => state.book.fontSize,
  menuVisible: state => state.book.menuVisible,
  keyInContent: state => state.book.keyInContent,
  isLogin: state => state.book.isLogin,
  userName: state => state.book.userName,
  bookSelfList: state => state.book.bookSelfList
};

export default getters;
