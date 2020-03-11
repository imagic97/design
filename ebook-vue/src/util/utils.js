//添加CSS样式
export function addCss(href) {
  //在对象中创建一个对象，要与appendChild() 或 insertBefore()方法联合使用
  const link = document.createElement("link");
  //link标签设置rel、type、href属性
  link.setAttribute("rel", "stylesheet");
  link.setAttribute("type", "text/css");
  link.setAttribute("href", href);
  //getElementsByTagName返回指定标签名的集合
  //appendChild在指定节点后添加最后一个节点，将link标签添加至head标签下
  document.getElementsByTagName("head")[0].appendChild(link);
}

//移除CSS样式
export function removeCss(href) {
  //根据getElementsByTagName返回link标签的集合
  const link = document.getElementsByTagName("link");
  //遍历标签集合
  for (var i = link.length; i >= 0; i--) {
    //link存在
    if (
      link[i] &&
      link[i].getAttribute("href") != null &&
      link[i].getAttribute("href").indexOf(href) !== -1
    ) {
      link[i].parentNode.removeChild(link[i]);
    }
  }
}
