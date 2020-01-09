import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

/* 判断是否登录 */
Vue.prototype.checkLogin = function(backpage, backtype){
	//uni 数据缓存
	//判断是否存在DID
	var DID  = uni.getStorageSync('DID');
	
	if(DID == ''){
		//backpage : 登录后返回的页面
		//backtype : 打开页面的类型[1 : redirectTo 2 : switchTab]
		uni.redirectTo({url:'../login/login?backpage='+backpage+'&backtype='+backtype});
		uni.setStorageSync('DLEVEL',res.data.data.dlevel);
		uni.setStorageSync('HID',res.data.data.hid);
		// uni.setStorageSync('ADDRESS',res.data.data.address);
		// uni.setStorageSync('DPHONENUM',res.data.data.dphoneNum);
		// uni.setStorageSync('DNAME',res.data.data.dname);
		return false;
	}
	return [DID];
}

Vue.prototype.apiServer = 'http://49.235.2.40:8081/';

Vue.prototype.imgServer = 'http://49.235.2.40:8080/hx/';

App.mpType = ''

const app = new Vue({
    ...App
})
app.$mount()
