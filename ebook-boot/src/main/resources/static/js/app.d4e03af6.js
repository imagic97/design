(function(e){function n(n){for(var o,r,c=n[0],a=n[1],f=n[2],s=0,l=[];s<c.length;s++)r=c[s],Object.prototype.hasOwnProperty.call(u,r)&&u[r]&&l.push(u[r][0]),u[r]=0;for(o in a)Object.prototype.hasOwnProperty.call(a,o)&&(e[o]=a[o]);p&&p(n);while(l.length)l.shift()();return i.push.apply(i,f||[]),t()}function t(){for(var e,n=0;n<i.length;n++){for(var t=i[n],o=!0,r=1;r<t.length;r++){var c=t[r];0!==u[c]&&(o=!1)}o&&(i.splice(n--,1),e=a(a.s=t[0]))}return e}var o={},r={app:0},u={app:0},i=[];function c(e){return a.p+"js/"+({}[e]||e)+"."+{"chunk-61346126":"6a087044","chunk-5feb1bfb":"d5c2b462","chunk-6fbe4f17":"4d18333e"}[e]+".js"}function a(n){if(o[n])return o[n].exports;var t=o[n]={i:n,l:!1,exports:{}};return e[n].call(t.exports,t,t.exports,a),t.l=!0,t.exports}a.e=function(e){var n=[],t={"chunk-5feb1bfb":1,"chunk-6fbe4f17":1};r[e]?n.push(r[e]):0!==r[e]&&t[e]&&n.push(r[e]=new Promise((function(n,t){for(var o="css/"+({}[e]||e)+"."+{"chunk-61346126":"31d6cfe0","chunk-5feb1bfb":"4ea41f46","chunk-6fbe4f17":"2248735a"}[e]+".css",u=a.p+o,i=document.getElementsByTagName("link"),c=0;c<i.length;c++){var f=i[c],s=f.getAttribute("data-href")||f.getAttribute("href");if("stylesheet"===f.rel&&(s===o||s===u))return n()}var l=document.getElementsByTagName("style");for(c=0;c<l.length;c++){f=l[c],s=f.getAttribute("data-href");if(s===o||s===u)return n()}var p=document.createElement("link");p.rel="stylesheet",p.type="text/css",p.onload=n,p.onerror=function(n){var o=n&&n.target&&n.target.src||u,i=new Error("Loading CSS chunk "+e+" failed.\n("+o+")");i.code="CSS_CHUNK_LOAD_FAILED",i.request=o,delete r[e],p.parentNode.removeChild(p),t(i)},p.href=u;var m=document.getElementsByTagName("head")[0];m.appendChild(p)})).then((function(){r[e]=0})));var o=u[e];if(0!==o)if(o)n.push(o[2]);else{var i=new Promise((function(n,t){o=u[e]=[n,t]}));n.push(o[2]=i);var f,s=document.createElement("script");s.charset="utf-8",s.timeout=120,a.nc&&s.setAttribute("nonce",a.nc),s.src=c(e);var l=new Error;f=function(n){s.onerror=s.onload=null,clearTimeout(p);var t=u[e];if(0!==t){if(t){var o=n&&("load"===n.type?"missing":n.type),r=n&&n.target&&n.target.src;l.message="Loading chunk "+e+" failed.\n("+o+": "+r+")",l.name="ChunkLoadError",l.type=o,l.request=r,t[1](l)}u[e]=void 0}};var p=setTimeout((function(){f({type:"timeout",target:s})}),12e4);s.onerror=s.onload=f,document.head.appendChild(s)}return Promise.all(n)},a.m=e,a.c=o,a.d=function(e,n,t){a.o(e,n)||Object.defineProperty(e,n,{enumerable:!0,get:t})},a.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},a.t=function(e,n){if(1&n&&(e=a(e)),8&n)return e;if(4&n&&"object"===typeof e&&e&&e.__esModule)return e;var t=Object.create(null);if(a.r(t),Object.defineProperty(t,"default",{enumerable:!0,value:e}),2&n&&"string"!=typeof e)for(var o in e)a.d(t,o,function(n){return e[n]}.bind(null,o));return t},a.n=function(e){var n=e&&e.__esModule?function(){return e["default"]}:function(){return e};return a.d(n,"a",n),n},a.o=function(e,n){return Object.prototype.hasOwnProperty.call(e,n)},a.p="",a.oe=function(e){throw console.error(e),e};var f=window["webpackJsonp"]=window["webpackJsonp"]||[],s=f.push.bind(f);f.push=n,f=f.slice();for(var l=0;l<f.length;l++)n(f[l]);var p=s;i.push([0,"chunk-vendors"]),t()})({0:function(e,n,t){e.exports=t("56d7")},"034f":function(e,n,t){"use strict";var o=t("85ec"),r=t.n(o);r.a},"2ee5":function(e,n,t){e.exports=t.p+"img/error.25180fbd.svg"},"56d7":function(e,n,t){"use strict";t.r(n);t("e260"),t("e6cf"),t("cca6"),t("a79d");var o=t("a026"),r=function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},u=[],i={name:"app"},c=i,a=(t("034f"),t("2877")),f=Object(a["a"])(c,r,u,!1,null,null,null),s=f.exports,l=t("2f62"),p={state:{fileName:"",bookID:"",content:"",position:"",nextPosition:"",contentShow:!1,contentList:[],menuShow:-1,keyInContent:0,menuVisible:!1,fontSize:16},mutations:{SET_FILENAME:function(e,n){e.fileName=n},SET_BOOKID:function(e,n){e.bookID=n},SET_CONTENT:function(e,n){e.content=n},SET_POSITION:function(e,n){e.position=n},SET_NEXTPOSITION:function(e,n){e.nextPosition=n},SET_CONTENTSHOW:function(e,n){e.contentShow=n},SET_CONTENTLIST:function(e,n){e.contentList=n},SET_MENUSHOW:function(e,n){e.menuShow=n},SET_KEYINCONTENT:function(e,n){e.keyInContent=n},SET_FONTSIZE:function(e,n){e.fontSize=n},SET_MENUVISIBLE:function(e,n){e.menuVisible=n}}},m=p,d={fileName:function(e){return e.book.fileName},bookID:function(e){return e.book.bookID},content:function(e){return e.book.content},position:function(e){return e.book.position},nextPosition:function(e){return e.book.nextPosition},contentShow:function(e){return e.book.contentShow},contentList:function(e){return e.book.contentList},menuShow:function(e){return e.book.menuShow},currentComponent:function(e){return e.book.currentComponent},fontSize:function(e){return e.book.fontSize},menuVisible:function(e){return e.book.menuVisible},keyInContent:function(e){return e.book.keyInContent}},h=d,b={setFileName:function(e,n){var t=e.commit;return t("SET_FILENAME",n)},setBookID:function(e,n){var t=e.commit;return t("SET_BOOKID",n)},setContent:function(e,n){var t=e.commit;return t("SET_CONTENT",n)},setPosition:function(e,n){var t=e.commit;return t("SET_POSITION",n)},setNextPosition:function(e,n){var t=e.commit;return t("SET_NEXTPOSITION",n)},setContentShow:function(e,n){var t=e.commit;return t("SET_CONTENTSHOW",n)},setContentList:function(e,n){var t=e.commit;return t("SET_CONTENTLIST",n)},setMenuShow:function(e,n){var t=e.commit;return t("SET_MENUSHOW",n)},setKeyInContent:function(e,n){var t=e.commit;return t("SET_KEYINCONTENT",n)},setFontSize:function(e,n){var t=e.commit;return t("SET_FONTSIZE",n)},setMenuVisible:function(e,n){var t=e.commit;return t("SET_MENUVISIBLE",n)}},S=b;o["default"].use(l["a"]);var E=new l["a"].Store({modules:{book:m},getters:h,actions:S}),T=t("8c4f");o["default"].use(T["a"]);var v=new T["a"]({routes:[{path:"/",redirect:"/book-self"},{path:"/book-self",component:function(e){return Promise.all([t.e("chunk-61346126"),t.e("chunk-6fbe4f17")]).then(function(){var n=[t("f02b")];e.apply(null,n)}.bind(this)).catch(t.oe)}},{path:"/book-reader",component:function(e){return Promise.all([t.e("chunk-61346126"),t.e("chunk-5feb1bfb")]).then(function(){var n=[t("61bf")];e.apply(null,n)}.bind(this)).catch(t.oe)}}]}),N=t("caf9"),g=t("5c96"),k=t.n(g);t("0fae");o["default"].config.productionTip=!1,o["default"].use(k.a),o["default"].use(N["a"],{error:t("2ee5"),loading:t("c74f")}),new o["default"]({router:v,store:E,render:function(e){return e(s)}}).$mount("#app")},"85ec":function(e,n,t){},c74f:function(e,n){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABH0lEQVQ4T6WTMUvEQBCF3/h3/AeClaBgr2AjimCnlc1N7jZxk5sUdlpZKApWXq+NjZXYa+Gf8clGNsTciRvcbmHeNztv3krmlPjHkQAgcAoggGQIS4C8BZi3YohYnRZJgCzXSyHeKm8X3QZJgMyN7gHZCkIS+1baTYQkAcZOjwicNwBg07w9DgKEYp3oHsGPuqxfBo+wyNTvsZZmBJd/NVGdjoRcF8Fd5eurCIqefJIrIrLRBRTmLWQhPHlXBLcAZ8FAAaaVt3EUkziw0q7VaS5A8SMH6nRNgKcgnvp6O8uzM5AnERYCF/MytwUArwI8RHHr9kQPBdyhyHM3bHMAgbwTXDVvxymJ7ANaD1LEjVd9D/4Q9j9ac2+3kNp1Ud0XrOjKMXpg7sUAAAAASUVORK5CYII="}});
//# sourceMappingURL=app.d4e03af6.js.map