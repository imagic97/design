(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7041351a"],{"46ee":function(s,t,e){},"5f3b":function(s,t,e){"use strict";e.r(t);var a=function(){var s=this,t=s.$createElement,e=s._self._c||t;return e("div",{staticClass:"registerContainer"},[e("div",{directives:[{name:"show",rawName:"v-show",value:s.isLoading,expression:"isLoading"}],staticClass:"loading"},[e("loading")],1),e("div",{staticClass:"register"},[e("h2",[s._v("注册/REGISTER")]),e("div",{staticClass:"register_form"},[e("input",{directives:[{name:"model",rawName:"v-model",value:s.email,expression:"email"}],staticClass:"email",attrs:{type:"text",placeholder:"邮箱,可省略"},domProps:{value:s.email},on:{input:function(t){t.target.composing||(s.email=t.target.value)}}}),e("div",{staticClass:"tips"},[e("span",[s._v(s._s(s.tips))])]),e("input",{directives:[{name:"model",rawName:"v-model",value:s.userName,expression:"userName"}],staticClass:"user",attrs:{type:"text",placeholder:"用户名"},domProps:{value:s.userName},on:{input:function(t){t.target.composing||(s.userName=t.target.value)}}}),e("div",{staticClass:"tips"},[e("span",[s._v(s._s(s.tips_a))])]),e("input",{directives:[{name:"model",rawName:"v-model",value:s.password,expression:"password"}],staticClass:"password",attrs:{type:"password",placeholder:"密码"},domProps:{value:s.password},on:{input:function(t){t.target.composing||(s.password=t.target.value)}}}),e("div",{staticClass:"tips"},[e("span",[s._v(s._s(s.tips_b))])]),e("button",{staticClass:"register_btn",on:{click:function(t){return s.register()}}},[s._v("注册")])])])])},i=[],n=e("4ec3"),r=e("bf93"),o={components:{loading:r["a"]},data:function(){return{userName:"",password:"",email:"",tips:"",tips_a:"",tips_b:"",isLoading:!1}},watch:{userName:function(){this.tips_a=""},password:function(){this.tips_b=""}},methods:{register:function(){var s=this;this.userName?this.password?(this.isLoading=!0,Object(n["j"])(this.userName,this.password,this.email).then((function(t){200==t.data.code?s.$router.push("/login"):s.tips_b=t.data.message,s.isLoading=!1}))):this.tips_b="请输入密码":this.tips_a="请输入用户名"}}},c=o,l=(e("8678"),e("2877")),u=Object(l["a"])(c,a,i,!1,null,"a4f38bec",null);t["default"]=u.exports},"5f65":function(s,t,e){"use strict";var a=e("46ee"),i=e.n(a);i.a},7750:function(s,t,e){},8678:function(s,t,e){"use strict";var a=e("7750"),i=e.n(a);i.a},bf93:function(s,t,e){"use strict";var a=function(){var s=this,t=s.$createElement;s._self._c;return s._m(0)},i=[function(){var s=this,t=s.$createElement,e=s._self._c||t;return e("div",{staticClass:"spinner"},[e("div",{staticClass:"rect1"}),e("div",{staticClass:"rect2"}),e("div",{staticClass:"rect3"}),e("div",{staticClass:"rect4"}),e("div",{staticClass:"rect5"})])}],n=(e("5f65"),e("2877")),r={},o=Object(n["a"])(r,a,i,!1,null,"230c0e8e",null);t["a"]=o.exports}}]);
//# sourceMappingURL=chunk-7041351a.922af4d9.js.map