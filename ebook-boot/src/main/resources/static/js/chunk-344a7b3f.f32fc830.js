(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-344a7b3f"],{"0141":function(e,t,i){"use strict";var o=i("2829"),s=i.n(o);s.a},"1f86":function(e,t,i){"use strict";i.r(t);var o=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"self_view"},[i("div",{directives:[{name:"show",rawName:"v-show",value:!e.uploadShow,expression:"!uploadShow"}],staticClass:"book_self"},[i("self-nav"),i("book-self",{on:{showUpload:e.getIsUploadShow}})],1),i("div",{directives:[{name:"show",rawName:"v-show",value:e.uploadShow,expression:"uploadShow"}],staticClass:"upload"},[i("upload-file",{on:{hideUpload:e.getIsUploadShow}})],1)])},s=[],n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"userBookSelfContainer"},[i("div",{staticClass:"bookSelf"},[e._l(e.bookSelfList,(function(t,o){return i("div",{key:o,staticClass:"bookSelfList",on:{click:function(i){e.selectedMode?e.selectBook(o):e.toReaderBook(t)}}},[i("div",{staticClass:"bookCover"},[i("img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.API_TO_GET_COVER+t.fileName,expression:"API_TO_GET_COVER + item.fileName"}],staticClass:"bookCover_img"}),i("div",{directives:[{name:"show",rawName:"v-show",value:e.selectedMode,expression:"selectedMode"}],staticClass:"iconfont icon-selected",class:{selected:t.isSelected}}),i("div",{staticClass:"book_cover_boder"})]),i("div",{staticClass:"title"},[e._v(e._s(t.title?t.title:"无书名"))])])})),i("div",{staticClass:"addBook placeholder",on:{click:function(t){return e.showUpload()}}},[i("div",{staticClass:"iconfont icon-add"})]),e._l(e.placeholder,(function(e){return i("div",{key:"num-"+e,staticClass:"placeholder"})}))],2)])},a=[],l=(i("a4d3"),i("e01a"),i("d28b"),i("a434"),i("d3b7"),i("3ca3"),i("ddb0"),i("4e3c")),c=i("4ec3"),r=i("022d"),d={mixins:[l["a"]],data:function(){return{selectedMode:!1,placeholder:4}},watch:{isLogin:function(){""==this.isLogin&&this.setBookSelfList([])}},mounted:function(){var e=this;r["a"].$on("SELECTMODE",(function(t){e.selectedMode=t,!1===t&&e.selectCancel(e.bookSelfList)})),r["a"].$on("DELETEBOOK",(function(t){!0===t&&e.deleteBook(e.bookSelfList)}));var t=window.innerWidth>870?850:window.innerWidth;this.placeholder=parseInt(t/150)-1,this.init()},methods:{init:function(){var e=this;null!=this.isLogin&&0==this.bookSelfList.length&&Object(c["o"])().then((function(t){if(200==t.data.code){var i=!0,o=!1,s=void 0;try{for(var n,a=t.data.result[Symbol.iterator]();!(i=(n=a.next()).done);i=!0){var l=n.value;l.isSelected=!1,e.bookSelfList.push(l)}}catch(c){o=!0,s=c}finally{try{i||null==a.return||a.return()}finally{if(o)throw s}}}}))},toReaderBook:function(e){this.setBookID(e.bookId),this.setFileName(e.fileName),this.setTitle(e.title),this.$router.push({path:"/book-reader"})},showUpload:function(){this.$emit("showUpload",!0)},selectBook:function(e){this.bookSelfList[e].isSelected=!this.bookSelfList[e].isSelected},deleteBook:function(e){for(var t=e.length-1;t>=0;t-=1)!0===e[t].isSelected&&(0===e[t].selfId?Object(c["d"])(e[t].bookId,e[t].fileName):Object(c["e"])(e[t].selfId),e.splice(t,1));return e},selectCancel:function(e){var t=!0,i=!1,o=void 0;try{for(var s,n=e[Symbol.iterator]();!(t=(s=n.next()).done);t=!0){var a=s.value;a.isSelected=!1}}catch(l){i=!0,o=l}finally{try{t||null==n.return||n.return()}finally{if(i)throw o}}return e}}},f=d,u=(i("a9bc"),i("2877")),h=Object(u["a"])(f,n,a,!1,null,"41dd95fc",null),v=h.exports,p=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"slef_nav"},[i("div",{directives:[{name:"show",rawName:"v-show",value:!e.isSelectMode,expression:"!isSelectMode"}],staticClass:"type no_select"},[i("div",{staticClass:"nav nav_left"}),i("div",{staticClass:"nav nav_center"},[e._v("书架")]),i("div",{staticClass:"nav nav_right",on:{click:e.selectMode}},[i("span",{staticClass:"iconfont icon-edit"})])]),i("div",{directives:[{name:"show",rawName:"v-show",value:e.isSelectMode,expression:"isSelectMode"}],staticClass:"type select"},[i("div",{staticClass:"nav nav_left",on:{click:e.deleteBook}},[i("span",{staticClass:"iconfont icon-delete"})]),i("div",{staticClass:"nav nav_center"},[e._v("书架")]),i("div",{staticClass:"nav nav_right",on:{click:e.selectMode}},[e._v("取消")])])])},m=[],g={data:function(){return{isSelectMode:!1}},methods:{selectMode:function(){this.isSelectMode=!this.isSelectMode,r["a"].$emit("SELECTMODE",this.isSelectMode)},deleteBook:function(){r["a"].$emit("DELETEBOOK",!0)}}},b=g,S=(i("fd82"),Object(u["a"])(b,p,m,!1,null,"17f1221b",null)),w=S.exports,k=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("div",{staticClass:"upload upload_file"},[i("h3",[e._v("上传电子书")]),i("div",{ref:"message",attrs:{id:"message"},on:{click:function(t){return e.cancel()}}},[e._v(e._s(e.message))]),i("input",{ref:"file",attrs:{type:"file",id:"file"},on:{change:function(t){return e.getFile(t)}}}),i("button",{staticClass:"btn",on:{click:function(t){return e.selectFile()}}},[e._v("选择电子书")]),i("button",{staticClass:"btn btn-upload",on:{click:function(t){return e.uploadFile()}}},[e._v("上传")]),i("button",{staticClass:"btn btn-cancel",on:{click:function(t){return e.hide()}}},[e._v("取消")])])])},C=[],_=(i("b0c0"),{data:function(){return{message:"限制大小为20M",file:null}},methods:{selectFile:function(){this.$refs.file.click()},getFile:function(){var e=event.target.files;if(0!=e.length){if("application/epub+zip"!=e[0].type)return this.message="只支持epub格式哦",void(this.$refs.message.style.color="red");if(e[0].size>20971520)return this.message="文件大小超过限制",void(this.$refs.message.style.color="red");this.file=e[0],this.$refs.message.style.color="",this.message=e[0].name}},uploadFile:function(){var e=this;return null==this.file?(this.message="请选择文件上传",void(this.$refs.message.style.color="red")):0==this.isLogin?(this.message="请登录后继续操作",void(this.$refs.message.style.color="red")):(r["a"].$emit("isLoading",!0),void Object(c["u"])(this.file).then((function(t){200==t.data.code?(e.message="上传成功",e.file=null):e.message=t.data.message,r["a"].$emit("isLoading",!1)})).catch((function(){e.message="网络错误",e.$refs.message.style.color="red",r["a"].$emit("isLoading",!1)})))},cancel:function(){this.file=null,this.message=""},hide:function(){this.cancel(),this.$emit("hideUpload",!1)}}}),y=_,$=(i("0141"),Object(u["a"])(y,k,C,!1,null,"1f398717",null)),E=$.exports,M={mixins:[l["a"]],components:{bookSelf:v,selfNav:w,uploadFile:E},data:function(){return{uploadShow:!1}},methods:{getIsUploadShow:function(e){this.uploadShow=e},hideUpload:function(){this.uploadShow=!1}}},x=M,L=(i("7164"),Object(u["a"])(x,o,s,!1,null,"47f5291d",null));t["default"]=L.exports},2829:function(e,t,i){},"3ca3":function(e,t,i){"use strict";var o=i("6547").charAt,s=i("69f3"),n=i("7dd0"),a="String Iterator",l=s.set,c=s.getterFor(a);n(String,"String",(function(e){l(this,{type:a,string:String(e),index:0})}),(function(){var e,t=c(this),i=t.string,s=t.index;return s>=i.length?{value:void 0,done:!0}:(e=o(i,s),t.index+=e.length,{value:e,done:!1})}))},6547:function(e,t,i){var o=i("a691"),s=i("1d80"),n=function(e){return function(t,i){var n,a,l=String(s(t)),c=o(i),r=l.length;return c<0||c>=r?e?"":void 0:(n=l.charCodeAt(c),n<55296||n>56319||c+1===r||(a=l.charCodeAt(c+1))<56320||a>57343?e?l.charAt(c):n:e?l.slice(c,c+2):a-56320+(n-55296<<10)+65536)}};e.exports={codeAt:n(!1),charAt:n(!0)}},"6fca":function(e,t,i){},7164:function(e,t,i){"use strict";var o=i("cef2"),s=i.n(o);s.a},a434:function(e,t,i){"use strict";var o=i("23e7"),s=i("23cb"),n=i("a691"),a=i("50c4"),l=i("7b0b"),c=i("65f0"),r=i("8418"),d=i("1dde"),f=i("ae40"),u=d("splice"),h=f("splice",{ACCESSORS:!0,0:0,1:2}),v=Math.max,p=Math.min,m=9007199254740991,g="Maximum allowed length exceeded";o({target:"Array",proto:!0,forced:!u||!h},{splice:function(e,t){var i,o,d,f,u,h,b=l(this),S=a(b.length),w=s(e,S),k=arguments.length;if(0===k?i=o=0:1===k?(i=0,o=S-w):(i=k-2,o=p(v(n(t),0),S-w)),S+i-o>m)throw TypeError(g);for(d=c(b,o),f=0;f<o;f++)u=w+f,u in b&&r(d,f,b[u]);if(d.length=o,i<o){for(f=w;f<S-o;f++)u=f+o,h=f+i,u in b?b[h]=b[u]:delete b[h];for(f=S;f>S-o+i;f--)delete b[f-1]}else if(i>o)for(f=S-o;f>w;f--)u=f+o-1,h=f+i-1,u in b?b[h]=b[u]:delete b[h];for(f=0;f<i;f++)b[f+w]=arguments[f+2];return b.length=S-o+i,d}})},a9bc:function(e,t,i){"use strict";var o=i("6fca"),s=i.n(o);s.a},b0c0:function(e,t,i){var o=i("83ab"),s=i("9bf2").f,n=Function.prototype,a=n.toString,l=/^\s*function ([^ (]*)/,c="name";!o||c in n||s(n,c,{configurable:!0,get:function(){try{return a.call(this).match(l)[1]}catch(e){return""}}})},c36e:function(e,t,i){},cef2:function(e,t,i){},d28b:function(e,t,i){var o=i("746f");o("iterator")},ddb0:function(e,t,i){var o=i("da84"),s=i("fdbc"),n=i("e260"),a=i("9112"),l=i("b622"),c=l("iterator"),r=l("toStringTag"),d=n.values;for(var f in s){var u=o[f],h=u&&u.prototype;if(h){if(h[c]!==d)try{a(h,c,d)}catch(p){h[c]=d}if(h[r]||a(h,r,f),s[f])for(var v in n)if(h[v]!==n[v])try{a(h,v,n[v])}catch(p){h[v]=n[v]}}}},e01a:function(e,t,i){"use strict";var o=i("23e7"),s=i("83ab"),n=i("da84"),a=i("5135"),l=i("861d"),c=i("9bf2").f,r=i("e893"),d=n.Symbol;if(s&&"function"==typeof d&&(!("description"in d.prototype)||void 0!==d().description)){var f={},u=function(){var e=arguments.length<1||void 0===arguments[0]?void 0:String(arguments[0]),t=this instanceof u?new d(e):void 0===e?d():d(e);return""===e&&(f[t]=!0),t};r(u,d);var h=u.prototype=d.prototype;h.constructor=u;var v=h.toString,p="Symbol(test)"==String(d("test")),m=/^Symbol\((.*)\)[^)]+$/;c(h,"description",{configurable:!0,get:function(){var e=l(this)?this.valueOf():this,t=v.call(e);if(a(f,e))return"";var i=p?t.slice(7,-1):t.replace(m,"$1");return""===i?void 0:i}}),o({global:!0,forced:!0},{Symbol:u})}},fd82:function(e,t,i){"use strict";var o=i("c36e"),s=i.n(o);s.a}}]);
//# sourceMappingURL=chunk-344a7b3f.f32fc830.js.map