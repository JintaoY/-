<template>
	<view>
		<view class="cu-form-group margin-top">
			<view class="title">订单号</view>
			<input  disabled="disabled"  name="orderid" :value="the_order.igorderid"/>
		</view>
		<view class="cu-form-group">
			<view class="title">商品名称</view>
			<input disabled="disabled" name="productname" :value="the_order.productname"/>
		</view>
		<view class="cu-form-group">
			<view class="title">数量</view>
			<input disabled="disabled" name="pnum" :value="the_order.pnum" />
		</view>
		<view class="cu-form-group">
			<view class="title">收货人</view>
			<input  name="receivername" disabled="disabled" :value="the_order.receivername"/>
		</view>
		
		<view class="cu-form-group">
			<view class="title">手机</view>
			<input  name="receiverphone"  disabled="disabled":value="the_order.receiverphone"/>
		</view>
		
		<view class="cu-form-group">
			<view class="title">收货地址</view>
			<input  name="receiveraddress" disabled="disabled" :value="the_order.receiveraddress"/>
		</view>
		<!-- <view class="cu-form-group">
			<view class="title">下单时间</view>
			<input disabled="disabled" name="orederdate" :value="formatDate(the_order.orederdate)"/>
		</view> -->
		
		<view v-if="the_order.delivery==1">
			
			<view class="padding flex flex-direction">
			<button class="cu-btn bg-red margin-tb-sm lg" @tap="toGet()">确认收货</button>
			</view>

		</view>
		<view class="padding flex flex-direction">
			<button class="cu-btn bg-grey margin-tb-sm lg"  @tap="retu()">返回</button>
		</view>
	</view>
</template>

<script>
	Date.prototype.Format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, //月份
	        "d+": this.getDate(), //日
	        "h+": this.getHours(), //小时
	        "m+": this.getMinutes(), //分
	        "s+": this.getSeconds(), //秒
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
	        "S": this.getMilliseconds() //毫秒
	    };
	    if (/(y+)/.test(fmt))
	        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	        if (new RegExp("(" + k + ")").test(fmt))
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	};
	var _self, logRes , did
	export default {
		data() {
			return {
				the_order: [],
				packcode: null
			};
		},
		onLoad(options) {
			_self = this;
					logRes = _self.checkLogin('../order-detail/order-detail',1);
					
					// console.log(logRes[0]);
					_self.did = logRes[0];
					_self.the_order=JSON.parse(options.order);
					_self.packcode = _self.the_order.packcode;
		            // console.log(_self.the_order)
		        },
		methods: {
			formatDate(timestramp) {
				     return new Date(timestramp).Format('yyyy-MM-dd');
				 },
			retu(){
				uni.showToast({
					title:'操作成功',
					duration: 1000
				});
				
				setTimeout(function(){
					uni.reLaunch({
								    url: '../order'
								});
					},1000)
			},
			toGet(){
				// console.log(_self.packcode)
				// console.log(_self.the_order.orderid)
				// console.log(_self.did)

				uni.request({
					url: _self.apiServer+'mall/order/toGet',
					method: 'GET',
					data: {
						igorderid:_self.the_order.igorderid,
						id : _self.did,
					},
					success: res => {
						uni.showToast({
							title:'操作成功',
							duration: 1000
						});
						
						setTimeout(function(){
							uni.reLaunch({
										    url: '../order'
										});
							},1000)
					},
					fail: () => {},
					complete: () => {}
				});
			}
		}
	}
</script>

<style>
	@import url("../../../colorui/icon.wxss");
	@import url("../../../colorui/main.wxss");
	@import url("../../../colorui/animation.wxss");
</style>
