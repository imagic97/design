import axios from "axios";
axios.defaults.timeout = 5000; // 请求超时
axios.defaults.baseURL = "/api";

// 获取书籍资源，封面，图片，章节
export function getResource(file, href) {
  return axios({
    method: "GET",
    url: "/read/view",
    params: {
      file: file,
      href: href
    }
  });
}
// 目录
export function getContent(file) {
  return axios({
    method: "GET",
    url: "/read/content",
    params: {
      file: file
    }
  });
}
// 章节样式
export function getChapterCSS(file) {
  return axios({
    method: "GET",
    url: "/read/css",
    params: {
      file: file
    }
  });
}

// 登录
export function login(userName, password) {
  return axios({
    method: "POST",
    url: "/user/login",
    params: {
      userName: userName,
      password: password
    }
  });
}
// 登出
export function logout() {
  return axios({
    method: "GET",
    url: "/user/logout"
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
      email: email
    }
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
      email: email
    }
  });
}
// 书架列表
export function getUserSelf() {
  return axios({
    method: "GET",
    url: "/user/getUserSelf"
  });
}

//移出书架
export function deleteBookFromSelf(selfId) {
  return axios({
    method: "GET",
    url: "/user/deleteBookFromSelf",
    params: {
      selfId: selfId
    }
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
    data: param
  });
}

// 删除电子书
export function deleteBook(bookId, fileName) {
  return axios({
    method: "GET",
    url: "/book/delete",
    params: {
      bookId: bookId,
      fileName: fileName
    }
  });
}
