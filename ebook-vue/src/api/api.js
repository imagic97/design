import axios from "axios";
// export const API_TO_GET_VIEW = "/read/view?file=";
// export const API_TO_GET_COVER = "/read/cover?file=";
// export const API_TO_GET_CONTENT = "/read/content?file=";
////获取书籍资源，封面，图片，章节
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

export function getContent(file) {
  return axios({
    method: "GET",
    url: "/read/content",
    params: {
      file: file
    }
  });
}

export function getChapterCSS(file) {
  return axios({
    method: "GET",
    url: "/read/css",
    params: {
      file: file
    }
  });
}

// http://localhost:8080/user/getUserSelf
export function getUserSelf() {
  return axios({
    method: "GET",
    url: "/user/getUserSelf"
  });
}

///user/login?userName=test&password=123456
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
export function logout() {
  return axios({
    method: "GET",
    url: "/user/logout"
  });
}
///user/register?userName=user&password=123456&email=
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
