<template>
	<view style="margin: 60rpx;">
		<!-- <form action="#" methods="post"></form> -->
		<form @submit="formSubmit">
		<view class="cu-form-group margin-top">
			<view class="" style="padding-left: 40%;">订单信息</view>
		</view>
		<view class="cu-form-group">
			<view class="title">商品名称</view>
			<input disabled="disabled" name="productname" :value="product.productname"/>
		</view>
		<view class="cu-form-group">
			<view class="title">所需积分</view>
			<input disabled="disabled" name="productchangescore" :value="product.productchangescore*pnumb"/>
		</view>
		<view class="cu-form-group">
			<view class="title">数量</view>
			<input disabled="disabled" name="pnum" :value="pnumb"/>
		</view>
		<view class="cu-form-group">
			<view class="title">收货人</view>
			<input  name="uusername" :value="myInfo.uusername"/>
		</view>
		<view class="cu-form-group">
			<view class="title">手机</view>
			<input  name="uphonenum" :value="myInfo.uphonenum"/>
		</view>
		<view class="cu-form-group">
			<view class="title">收货地址</view>
			<input id="uaddress" name="uaddress" v-model="myInfo.uaddress" />
		</view>
		<view>
			<input id="pid" name="pid"  v-show="false"  :value="product.productid"/>
			<input id="userid" v-show="false" name="userid" :value="myInfo.userid" />
			
		</view>
		<view>
			<view class="padding flex flex-direction">	
				<button class="cu-btn bg-red margin-tb-sm lg" form-type="submit">购买</button>
				<button class="cu-btn bg-gray margin-tb-sm lg" @tap="retu()">返回</button>
			</view>
		</view>
		</form>

	</view>
</template>

<script>
	var _self, logRes , USERID,myInfo,product,address,pnumb,formdata
	export default {
		data() {
			return {
				product: [],
				myInfo:[],
				pnumb:null
			};
		},
		onLoad(options) {
			_self = this;
					logRes = _self.checkLogin('../Buy/Buy',1);
					_self.product = JSON.parse(options.product);
					_self.pnumb = options.pnum/* uni.getStorageSync('OPnum') */;
					console.log(_self.pnumb);
					console.log(_self.product);
					_self.USERID = logRes[0];
					
					uni.request({
						url: _self.apiServer+'mall/order/myinfo',
						method: 'GET',
						data: {
							userid : _self.USERID
						},
						success: res => {
							_self.myInfo = res.data.data;
							console.log(_self.myInfo);
						},
						fail: () => {},
						complete: () => {}
					});
				
		        },
		methods: {
			formSubmit: function(e) {
			                // console.log('form发生了submit事件，携带数据为：' + JSON.stringify(e.detail.value))
			                formdata = e.detail.value 
							// console.log(formdata)
							if(formdata.pnum==undefined){
								uni.showToast({
									title:'购买数量不能为空',
									duration: 1000
								});
								setTimeout(function(){
									uni.reLaunch({
												    url: '../order'
												});
									},1000)
							}else{
								uni.request({
									url: _self.apiServer+'mall/order/buy',
									method: 'POST',
									header: {
										'content-type': 'application/x-www-form-urlencoded', 
									},
									data: {
										pid				:  formdata.pid,
										pnum			:  formdata.pnum,
										userid		:  formdata.userid,
										uaddress :  formdata.uaddress,
										uusername	:  formdata.uusername ,
										uphonenum   :  formdata.uphonenum
									},
									success: res => {
										var result = res.data.data;
										if(result==200){
											uni.showToast({
												title:'操作成功',
												duration: 1000,
												icon:'none'
											});
											setTimeout(function(){
												uni.reLaunch({
															    url: '../../product/product'
															});
												},1000)
										}else{
											uni.showToast({
												title:'积分不足，购买失败',
												duration: 1000,
												icon:'none'
											});
											setTimeout(function(){
												uni.reLaunch({
															    url: '../../product/product'
															});
												},1000)
										}
									},
									fail: () => {},
									complete: () => {}
								});
							}
							
			            },
			retu(){
				// uni.showToast({
				// 	title:'操作成功',
				// 	duration: 1000
				// });
				
				setTimeout(function(){
					uni.reLaunch({
								    url: '../../product/product'
								});
					})
			}
		}
	}
</script>

<style>
	@import url("../../../colorui/icon.wxss");
	@import url("../../../colorui/main.wxss");
	@import url("../../../colorui/animation.wxss");
</style>
