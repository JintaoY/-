<template>
	<view class="container">
		
		
		
			<button class="confirm-btn0" @tap="toPerson" :disabled="logining">个人中心</button>
			<button class="confirm-btn" @tap="toxjgl" :disabled="logining">下级管理</button>
			<button class="confirm-btn" @tap="toDeliver" :disabled="logining">发货管理</button>
			<button class="confirm-btn" @tap="toStore" :disabled="logining">入库管理</button>
			<button class="confirm-btn" @tap="toOrder" :disabled="logining">订单管理</button>
		
		
	</view>
</template>

<script>
	var _self,options;
	/* import {
	    mapMutations  
	} from 'vuex'; */
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
			toOrder:function(){
				uni.navigateTo({
					url: '../order/order'
				});
			},
			
			//入库管理
			toStore:function(){
				uni.navigateTo({
					url: '../order/Buy/Buy'
				});
			},
			
			//发货管理
			toDeliver:function(){
				uni.navigateTo({
					url: '../order/Buy/Buy'
				});
			},
			
			//跳转到下级管理
			toxjgl:function(){
				uni.navigateTo({
					url: '../xjgl/xjgl'
				});
			},
			
			//跳转个人中心
			toPerson:function(){
				
				uni.navigateTo({
					url: '../person/person'
				});
			}
			
		},
		onLoad:function(options){
			_self = this;
			//console.log(options);
			this.options = options;
					
		}
	}
</script>

<style lang='scss'>
	page{
			background: #fff;
		}
	
	.confirm-btn0{
		width: 630upx;
		height: 85upx;
		line-height: 76upx;
		border-radius: 50px;
		margin-top: 70upx;
		background: $uni-color-error;
		color: #fff;
		font-size: $font-lg;
		&:after{
			border-radius: 100px;
		}
	}
	

	.confirm-btn{
		width: 630upx;
		height: 85upx;
		line-height: 76upx;
		border-radius: 50px;
		margin-top: 20upx;
		background: $uni-color-error;
		color: #fff;
		font-size: $font-lg;
		&:after{
			border-radius: 100px;
		}
	}
</style>
