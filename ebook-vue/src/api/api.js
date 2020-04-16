import axios from "axios";

axios.defaults.timeout = 60000; // 请求超时
axios.defaults.baseURL = "/api";

axios.interceptors.request.use(
  function(config) {
    let token = window.localStorage.getItem("token");
    if (token) {
      config.headers.common["token"] = token;
    }
    return config;
  },
  function(error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

// 获取书籍资源，封面，图片，章节
export function getResource(file, href) {
  return axios({
    method: "GET",
    url: "/read/view",
    params: {
      file: file,
      href: href,
    },
  });
}
// 目录
export function getContent(file) {
  return axios({
    method: "GET",
    url: "/read/content",
    params: {
      file: file,
    },
  });
}
// 章节样式
export function getChapterCSS(file) {
  return axios({
    method: "GET",
    url: "/read/css",
    params: {
      file: file,
    },
  });
}

// 登录
export function login(userName, password) {
  return axios({
    method: "POST",
    url: "/user/login",
    params: {
      userName: userName,
      password: password,
    },
  });
}
// 登出
export function logout() {
  return axios({
    method: "GET",
    url: "/user/logout",
  });
}
// 注册
export function register(userName, password, email) {
  return axios({
    method: "POST",
    url: "/user/register",
    params: {
      userName: userName,
      password: password,
      email: email,
    },
  });
}
// 用户修改
export function modifyUser(userName, password, email) {
  return axios({
    method: "POST",
    url: "/user/modifyUser",
    params: {
      userName: userName,
      password: password,
      email: email,
    },
  });
}
// 书架列表
export function getUserSelf() {
  return axios({
    method: "GET",
    url: "/user/getUserSelf",
  });
}
///user/addBookToSelf?bookId=15

export function addBookToSelf(bookId) {
  return axios({
    method: "GET",
    url: "/user/addBookToSelf",
    params: {
      bookId: bookId,
    },
  });
}

//移出书架
export function deleteBookFromSelf(selfId) {
  return axios({
    method: "GET",
    url: "/user/deleteBookFromSelf",
    params: {
      selfId: selfId,
    },
  });
}
// 上传电子书
export function uploadBook(file) {
  let param = new FormData();
  param.append("multipartFile", file);
  return axios({
    headers: { "Content-Type": "multipart/form-data" },
    method: "POST",
    url: "/book/upload",
    data: param,
  });
}

// 删除电子书
export function deleteBook(bookId, fileName) {
  return axios({
    method: "GET",
    url: "/book/delete",
    params: {
      bookId: bookId,
      fileName: fileName,
    },
  });
}

// 搜索
export function searchBook(keyWords, offset) {
  return axios({
    method: "GET",
    url: "/search",
    params: {
      keyWords: keyWords,
      offset: offset,
    },
  });
}
// 电子书详细信息
export function getBookInfo(bookId) {
  return axios({
    method: "GET",
    url: "/book/getBookInfo",
    params: {
      bookId: bookId,
    },
  });
}
//获取全部分类
export function getCategory() {
  return axios({
    method: "GET",
    url: "/get_category",
  });
}
// 获取分类下的电子书
export function getCategoryBook(category_Id) {
  return axios({
    method: "GET",
    url: "/get_category_book",
    params: {
      category_Id: category_Id,
    },
  });
}

// 添加书签
export function addBookMark(bookId, chapter) {
  return axios({
    method: "POST",
    url: "/user/addBookMark",
    params: {
      bookId: bookId,
      chapter: chapter,
    },
  });
}
// 获取电子书的书签
export function getBookMark(bookId) {
  return axios({
    method: "GET",
    url: "/user/getBookMark",
    params: {
      bookId: bookId,
    },
  });
}

// 删除电子书的书签
export function deleteBookMark(bookMarkId) {
  return axios({
    method: "GET",
    url: "/user/deleteBookMark",
    params: {
      bookMarkId: bookMarkId,
    },
  });
}
// 添加阅读记录
export function addReadHistory(bookId, chapter) {
  return axios({
    method: "POST",
    url: "/user/addReadHistory",
    params: {
      bookId: bookId,
      chapter: chapter,
    },
  });
}

// 获取阅读记录
export function getReadHistory() {
  return axios({
    method: "GET",
    url: "/user/getReadHistory",
  });
}
