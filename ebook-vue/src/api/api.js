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
