const getters = {
  fileName: state => state.book.fileName,
  bookID: state => state.book.bookID,
  content: state => state.book.content,
  position: state => state.book.position,
  nextPosition: state => state.book.nextPosition,
  contentShow: state => state.book.contentShow
};

export default getters;
