<template>
	<view>
	 
	  
	    <cmd-transition name="fade-up">
	      <view>
	        <cmd-cel-item title="经销商编号" :addon="distributor.did" arrow></cmd-cel-item>
	        <cmd-cel-item title="用户名" :addon="distributor.dname" arrow></cmd-cel-item>
	        <cmd-cel-item title="手机号" :addon="distributor.dphonenum" arrow></cmd-cel-item>
	        <cmd-cel-item title="性别" :addon="distributor.dsex" @tap="showModal" arrow></cmd-cel-item>
			<view class="cu-modal" :class="modalName=='true'?'show':''" @tap="hideModal">
				<view class="cu-dialog" @tap.stop="">
					<radio-group class="block" @change="RadioChange">
						<view class="cu-list menu text-left">
							<view class="cu-item" >
								<label class="flex justify-between align-center flex-sub">
									<view class="flex-sub">男</view>
									<radio class="round"  :checked="'男'== distributor.dsex ?true:false"
									 value="男"></radio>
								</label>
							</view>
							<view class="cu-item" >
								<label class="flex justify-between align-center flex-sub">
									<view class="flex-sub">女</view>
									<radio class="round"  :checked="'女'== distributor.dsex ?true:false"
									 value="女"></radio>
								</label>
							</view>
						</view>
					</radio-group>
				</view>
			</view>
	        <cmd-cel-item title="我的地址" :addon="distributor.daddress" @tap="address" arrow></cmd-cel-item>
	        <cmd-cel-item title="经销商级别" :addon="distributor.dlevel" arrow></cmd-cel-item>
	        <cmd-cel-item title="修改密码" @click="fnClick('modify')" arrow></cmd-cel-item>
	        <button class="btn-logout" @tap="logout">退出登录</button>
	      </view>
	    </cmd-transition>
	  
	</view>
</template>

<script>
	import cmdNavBar from "@/components/cmd-nav-bar/cmd-nav-bar.vue"
	import cmdPageBody from "@/components/cmd-page-body/cmd-page-body.vue"
	import cmdTransition from "@/components/cmd-transition/cmd-transition.vue"
	import cmdCelItem from "@/components/cmd-cell-item/cmd-cell-item.vue"
	import cmdAvatar from "@/components/cmd-avatar/cmd-avatar.vue"
	
	var _self , logRes , did
	export default {
	  components: {
	    cmdNavBar,
	    cmdPageBody,
	    cmdTransition,
	    cmdCelItem,
	    cmdAvatar
	  },
	
	  data() {
	    return {
			distributor:{},
			//模态框属性
			modalName: null
		}
	  },
		onLoad(option){
			 _self = this;
			 logRes = _self.checkLogin('../person/person',2);
			console.log(logRes[0]);
			_self.did = logRes[0];
			
			uni.request({
				url: _self.apiServer+'app/distributor/findDistributorData',
				method: 'GET',
				data: {
					did : _self.did
				},
				success: res => {
					_self.distributor = res.data.data;
					console.log(_self.distributor);
				},
				fail: () => {},
				complete: () => {}
			});
		},
	  mounted() {},
	  
	  methods:{
	    /**
	     * 点击触发
	     * @param {Object} type 跳转页面名或者类型方式
	     */
	    fnClick(type){
	      if(type == 'modify'){
	        uni.navigateTo({
	          url:'/pages/person/modify'
	        })
	      }
	    },
		address(){
			var address = JSON.stringify(this.distributor.daddress);
			uni.navigateTo({
			  url:'/pages/person/addressManage?daddress='+address
			  
			})
		},
		//退出登录，消除缓存id
		logout(){
			uni.showModal({
				title: '提示',
				content: '确定退出登录吗?',
				success: function (res) {
				        if (res.confirm) {
							 console.log('用户点击确定');
							 uni.removeStorageSync('DID');
							 uni.switchTab({
							 	url:"../product/product"
							 })
							 
				        } else if (res.cancel) {
				             console.log('用户点击取消');
				        }
				    }
			})
		},
		//修改性别模态框
		showModal(e) {
				this.modalName = 'true';
		},
		hideModal(e) {
			this.modalName = null;
		},
		RadioChange(e){
			_self = this;
			var dsex = e.target.value;
			console.log(dsex);
			logRes = _self.checkLogin('../person/person',2);
			console.log(logRes[0]);
			_self.did = logRes[0];
			uni.request({
				url: _self.apiServer + 'app/distributor/updataDsex',
				method: 'POST',
				header: { 'content-type': 'application/x-www-form-urlencoded' },
				data: {
					did: _self.did,
					dsex: dsex
				},
				success: res => {
					console.log(res.data.status);
					uni.reLaunch({
						url:"/pages/person/person"
					})
				},
				fail: e => {
					console.log(e);
				},
				complete: () => {}
			});
		}
		
	  }
	}
</script>

<style>
	.btn-logout {
	  margin-top: 100upx;
	  width: 80%;
	  border-radius: 50upx;
	  font-size: 16px;
	  color: #fff;
	  background: linear-gradient(to right, #365fff, #36bbff);
	}
	
	.btn-logout-hover {
	  background: linear-gradient(to right, #365fdd, #36bbfa);
	}
</style>
