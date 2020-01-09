<template>
	<view>
		<!-- <form action="#" methods="post"></form> -->
		<form @submit="formSubmit">
		<view class="cu-form-group margin-top">
			<view class="title">商品名称</view>
			<input disabled="disabled" name="productname" :value="product.productname"/>
		</view>
		<view class="cu-form-group">
			<view class="title">数量</view>
			<input disabled="disabled" name="pnum" :value="pnumb"/>
		</view>
		<view class="cu-form-group">
			<view class="title">收货人</view>
			<input  name="receivername" :value="myInfo.dname"/>
		</view>
		<view class="cu-form-group">
			<view class="title">手机</view>
			<input  name="receiverphone" :value="myInfo.dphonenum"/>
		</view>
		<view class="cu-form-group">
			<view class="title">收货地址</view>
			<input id="receiveraddress" name="receiveraddress" v-model="myInfo.daddress" />
		</view>
		<view>
			<input id="pid" name="pid"  v-show="false"  :value="product.productid"/>
			<input id="receiverid" v-show="false" name="receiverid" :value="myInfo.did" />
			<input id="sendid" v-show="false" name="sendid"  :value="myInfo.hid" />
		</view>
		<view>
			<view class="padding flex flex-direction">	
				<button class="cu-btn bg-red margin-tb-sm lg" form-type="submit">购买</button>
			</view>
		</view>
		</form>
		<view class="padding flex flex-direction">
			<button class="cu-btn bg-red margin-tb-sm lg" @tap="retu()">返回</button>
		</view>
	</view>
</template>

<script>
	var _self, logRes , did,myInfo,product,address,pnumb,formdata
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
					// _self.myInfo=uni.getStorageSync('myInfo');
					_self.product = JSON.parse(options.product);
					_self.pnumb = options.pnum/* uni.getStorageSync('OPnum') */;
					console.log(_self.pnumb);
					console.log(_self.product);
					_self.did = logRes[0];
					
					uni.request({
						url: _self.apiServer+'app/order/myinfo',
						method: 'GET',
						data: {
							did : _self.did
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

							uni.request({
								url: _self.apiServer+'app/order/buy',
								method: 'POST',
								header: {
									'content-type': 'application/x-www-form-urlencoded', 
								},
								data: {
									pid				:  formdata.pid,
									pnum			:  formdata.pnum,
									receiverid		:  formdata.receiverid,
									receiveraddress :  formdata.receiveraddress,
									receivername	:  formdata.receivername ,
									receiverphone   :  formdata.receiverphone,
									sendid			:  formdata.sendid

								},
								success: res => {
									uni.showToast({
										title:'操作成功',
										duration: 1000
									});
									
									setTimeout(function(){
										uni.reLaunch({
													    url: '../../product/product'
													});
										})
								},
								fail: () => {},
								complete: () => {}
							});
			            },
			retu(){
				uni.showToast({
					title:'操作成功',
					duration: 1000
				});
				
				setTimeout(function(){
					uni.reLaunch({
								    url: '../../product/product'
								});
					},1000)
			}
		}
	}
</script>

<style>
	@import url("../../../colorui/icon.wxss");
	@import url("../../../colorui/main.wxss");
	@import url("../../../colorui/animation.wxss");
</style>
