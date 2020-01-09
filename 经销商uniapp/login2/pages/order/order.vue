<template>
	<view>
		<scroll-view scroll-x class="bg-white nav">
			<view class="flex text-center">
				<view class="cu-item flex-sub" :class="index==TabCur?'text-orange cur':''" v-for="(item,index) in ['全部','待出货','待收货','已收货','退货']" :key="index" @tap="tabSelect" :data-id="index">
					{{item}}
				</view>
			</view>
		</scroll-view>
		<view class="orderborder" style="" v-for="(item, index) in TheLists" :key="index" @tap="details(item)">
			<view class="wrapper">
				<view class="theorderid">
					<text>订单号 : {{item.orderid}}</text>
				</view>
				<view class="theContent">
					<text class="theContentN"> 商品名称 : {{item.productname}} </text> 
					<text class="theContentN"> 数量 : {{item.pnum}} </text> 
				</view>
				<view  class="btn_">
					<text class="name"> 下单时间 : {{formatDate(item.orederdate)}} </text>
				</view>
				<view class="btn_">
					<!-- <text class="btn">{{item.delivery}}</text> -->
					<text v-if="item.delivery===0">
						<text class="btn">待出货</text>
					 </text>
					<text class="btn" v-else-if="item.delivery===1">
					待收货 </text>
					<text class="btn" v-else-if="item.delivery===2">
					已收货 </text>
					<text v-else-if="item.delivery===3">
						<text class="btn">退货</text>
					 </text>

					<text class="btn">查看详细</text>
				</view>
			</view>
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
	var _self , logRes , did
	export default {
		data() {
			return {
				OrderList: [],
				TheLists:[],
				TabCur: 0,
				scrollLeft: 0
			};
		},
		onLoad(option){
			_self = this;
			logRes = _self.checkLogin('../order/order',2);
			console.log(logRes[0]);
			_self.did = logRes[0];
			
			uni.request({
				url: _self.apiServer+'app/order/allOrders',
				method: 'GET',
				data: {
					id : _self.did
				},
				success: res => {
					_self.OrderList = res.data;
	
					_self.TheLists=_self.OrderList;
					
				},
				fail: () => {},
				complete: () => {}
			});
		},
		onShow() {
			
			uni.request({
				url: _self.apiServer+'app/order/allOrders',
				method: 'GET',
				data: {
					id : _self.did
				},
				success: res => {
					_self.OrderList = res.data;
				
					_self.TheLists=_self.OrderList;
					
				},
				fail: () => {},
				complete: () => {}
			});
			
		},
		methods: {
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60;
				var num =this.TabCur-1;
				_self.TheLists=[];
				if(num>=0){
					_self.OrderList.forEach(function(e){
						if(e.delivery==num){
							_self.TheLists.push(e);						
						}
					});
					}else{
						_self.TheLists=_self.OrderList;
					}
					console.log(_self.TheLists)
			},
			details(item){
				//console.log(item);
				var order = JSON.stringify(item);
				
				uni.redirectTo({
					url: 'order-detail/order-detail?order='+order
				});
			},
			formatDate(timestramp) {
				     return new Date(timestramp).Format('yyyy-MM-dd');
				 }
		}
	}
	
</script>

<style>
	@import url("../../colorui/icon.wxss");
	@import url("../../colorui/main.wxss");
	@import url("../../colorui/animation.wxss");
	.orderborder{
		/* float: left; */
		border-radius: 8rpx; 
		margin-left: 50rpx;
		margin-top: 10rpx;
		margin-right: 50rpx;
		padding-left: 15rpx; 
		padding-top: 15rpx;
		border: #F0AD4E solid 2rpx;
		border-top: #DD524D solid 5rpx;
		background-color: #FFFFFF;
		background-image: -webkit-gradient(linear,
		0 0, 100% 100%,
		color-stop(.25, rgba(252,251,251, .2)),
		color-stop(.25, transparent),
		color-stop(.5, transparent),
		color-stop(.5, rgba(252,251,251, .2)),
		color-stop(.75, rgba(252,251,251, .2)),
		color-stop(.75, transparent),
		to(transparent));
		background-size: 8px 8px;

	}
	.theorderid{
		padding-bottom: 5rpx;
		border-bottom: #DCDFE6 dotted 1rpx;
		font-size: 32rpx;
		font-weight: 500;
	}
	.theContent{
		margin: 20rpx;
	}
	.theContentN{
		margin-right: 40rpx;
		font-size: 35rpx;
	}
	.btn_{
		text-align: right;
		padding-right: 18rpx;
		padding-bottom: 20rpx;

	}
	.btn{
		/* float: right; */
		margin-right: 20rpx;

		padding: 10rpx;
		
		
		border: #c06020 solid 4rpx;
		border-radius: 30rpx;
		font-size: 35rpx;
		color: #F0AD4E;
	}
</style>
