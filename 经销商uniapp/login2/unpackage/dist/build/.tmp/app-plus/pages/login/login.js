(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/login/login"],{4263:function(t,o,e){"use strict";(function(t,e){var n;Object.defineProperty(o,"__esModule",{value:!0}),o.default=void 0;var a={data:function(){return{did:"",mobile:"",validateCode:""}},methods:{toLogin:function(){6==n.validateCode.length?n.did.length<1?t.showToast({title:"请输入正确的经销商编号",icon:"none"}):11==n.mobile.length?t.request({url:n.apiServer+"app/distributor/login",method:"POST",header:{"content-type":"application/x-www-form-urlencoded"},data:{did:n.did,mobile:n.mobile,validateCode:n.validateCode},success:function(o){console.log(e(o," at pages\\login\\login.vue:91")),console.log(e(o.data.status," at pages\\login\\login.vue:92")),200==o.data.status?(t.showToast({title:"登录成功"}),t.setStorageSync("DID",o.data.data.did),""==n.options.backtype&&t.switchTab({url:"../product/product"}),1==n.options.backtype?t.redirectTo({url:n.options.backpage}):t.switchTab({url:n.options.backpage})):1301==o.data.status?t.showToast({title:"验证码错误，请输入正确的验证码",icon:"none"}):t.showToast({title:o.data.msg})},fail:function(o){console.log(e(o," at pages\\login\\login.vue:124")),t.showToast({title:"验证码获得失败，请重新尝试!",icon:"none"})},complete:function(){}}):t.showToast({title:"请输入正确的手机号码",icon:"none"}):t.showToast({title:"请输入正确的验证码",icon:"none"})},getValidate:function(){11==n.mobile.length?t.request({url:n.apiServer+"app/distributor/getValidateCode",method:"GET",data:{mobile:n.mobile},success:function(o){200==o.data.status?(t.showToast({title:"验证码有效期为5分钟",icon:"success"}),n.validateCode=o.data.data):t.showToast({title:o.data.msg,icon:"none"})},fail:function(t){},complete:function(){}}):t.showToast({title:"请输入正确的手机号码",icon:"none"})}},onLoad:function(t){n=this,this.options=t}};o.default=a}).call(this,e("6e42")["default"],e("0de9")["default"])},"4c05":function(t,o,e){"use strict";e.r(o);var n=e("4263"),a=e.n(n);for(var i in n)"default"!==i&&function(t){e.d(o,t,function(){return n[t]})}(i);o["default"]=a.a},"58eb":function(t,o,e){"use strict";e.r(o);var n=e("8993"),a=e("4c05");for(var i in a)"default"!==i&&function(t){e.d(o,t,function(){return a[t]})}(i);e("713b");var l=e("2877"),s=Object(l["a"])(a["default"],n["a"],n["b"],!1,null,null,null);o["default"]=s.exports},"713b":function(t,o,e){"use strict";var n=e("9232"),a=e.n(n);a.a},8993:function(t,o,e){"use strict";var n=function(){var t=this,o=t.$createElement;t._self._c},a=[];e.d(o,"a",function(){return n}),e.d(o,"b",function(){return a})},9232:function(t,o,e){},bf76:function(t,o,e){"use strict";(function(t){e("9cb2"),e("921b");n(e("66fd"));var o=n(e("58eb"));function n(t){return t&&t.__esModule?t:{default:t}}t(o.default)}).call(this,e("6e42")["createPage"])}},[["bf76","common/runtime","common/vendor"]]]);