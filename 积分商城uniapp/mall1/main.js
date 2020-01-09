import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})


/* 判断是否登录 */
Vue.prototype.checkLogin = function(backpage, backtype){
	//uni 数据缓存
	//判断是否存在USERID
	var USERID  = uni.getStorageSync('USERID');
	
	if(USERID == ''){
		//backpage : 登录后返回的页面
		//backtype : 打开页面的类型[1 : redirectTo 2 : switchTab]
		uni.redirectTo({url:'../login/login?backpage='+backpage+'&backtype='+backtype});
		return false;
	}
	return [USERID];
}

Vue.prototype.apiServer = 'http://49.235.2.40:8082/';

app.$mount()
