const book = {
  state: {
    fileName: "",
    bookID: "3.epub",
    content: "",
    position: "",
    nextPosition: "",
    contentShow: false
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
    }
  }
};

export default book;
