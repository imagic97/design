const actions = {
  setFileName: ({ commit }, fileName) => {
    return commit("SET_FILENAME", fileName);
  },
  setBookID: ({ commit }, bookID) => {
    return commit("SET_BOOKID", bookID);
  },
  setContent: ({ commit }, content) => {
    return commit("SET_CONTENT", content);
  },
  setPosition: ({ commit }, position) => {
    return commit("SET_POSITION", position);
  },
  setNextPosition: ({ commit }, nextPosition) => {
    return commit("SET_NEXTPOSITION", nextPosition);
  },
  setContentShow: ({ commit }, contentShow) => {
    return commit("SET_CONTENTSHOW", contentShow);
  },
  setContentList: ({ commit }, contentList) => {
    return commit("SET_CONTENTLIST", contentList);
  },
  setMenuShow: ({ commit }, menuShow) => {
    return commit("SET_MENUSHOW", menuShow);
  },
  setKeyInContent: ({ commit }, keyInContent) => {
    return commit("SET_KEYINCONTENT", keyInContent);
  },
  setFontSize: ({ commit }, fontSize) => {
    return commit("SET_FONTSIZE", fontSize);
  },
  setMenuVisible: ({ commit }, menuVisible) => {
    return commit("SET_MENUVISIBLE", menuVisible);
  }
};

export default actions;
