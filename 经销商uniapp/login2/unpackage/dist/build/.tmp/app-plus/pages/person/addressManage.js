(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/person/addressManage"],{"01fb":function(e,a,n){"use strict";(function(e){n("9cb2"),n("921b");t(n("66fd"));var a=t(n("1b50"));function t(e){return e&&e.__esModule?e:{default:e}}e(a.default)}).call(this,n("6e42")["createPage"])},"1b50":function(e,a,n){"use strict";n.r(a);var t=n("54aa"),s=n("5a2a");for(var d in s)"default"!==d&&function(e){n.d(a,e,function(){return s[e]})}(d);n("fdb7");var o=n("2877"),r=Object(o["a"])(s["default"],t["a"],t["b"],!1,null,null,null);a["default"]=r.exports},"3a47":function(e,a,n){},"54aa":function(e,a,n){"use strict";var t=function(){var e=this,a=e.$createElement;e._self._c},s=[];n.d(a,"a",function(){return t}),n.d(a,"b",function(){return s})},"5a2a":function(e,a,n){"use strict";n.r(a);var t=n("ce18"),s=n.n(t);for(var d in t)"default"!==d&&function(e){n.d(a,e,function(){return t[e]})}(d);a["default"]=s.a},ce18:function(e,a,n){"use strict";(function(e,n){var t,s;Object.defineProperty(a,"__esModule",{value:!0}),a.default=void 0;var d={data:function(){return{daddress:"",did:""}},onLoad:function(a){t=this,s=t.checkLogin("../person/addressManage",1),console.log(e(s[0]," at pages\\person\\addressManage.vue:25")),t.did=s[0],t.daddress=JSON.parse(a.daddress)},methods:{confirm:function(){console.log(e(t.did," at pages\\person\\addressManage.vue:32")),n.request({url:t.apiServer+"app/distributor/updataDaddress",method:"POST",header:{"content-type":"application/x-www-form-urlencoded"},data:{did:t.did,daddress:t.daddress},success:function(a){console.log(e(a," at pages\\person\\addressManage.vue:42")),console.log(e(a.data.status," at pages\\person\\addressManage.vue:43")),200==a.data.status?n.reLaunch({url:"/pages/person/person"}):n.showToast({title:a.data.msg})},fail:function(a){console.log(e(a," at pages\\person\\addressManage.vue:54"))},complete:function(){}})}}};a.default=d}).call(this,n("0de9")["default"],n("6e42")["default"])},fdb7:function(e,a,n){"use strict";var t=n("3a47"),s=n.n(t);s.a}},[["01fb","common/runtime","common/vendor"]]]);