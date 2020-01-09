<template>
	<view class="content">
		<view class="row b-b">
			<text class="tit">经销商ID</text>
			<!-- <input class="input" type="text" v-model="distributor.did" disabled='true' /> -->
			<text class="text">{{distributor.did}}</text>
		</view>
		<view class="row b-b">
			<text class="tit">手机号</text>
			<text class="text">{{distributor.dphonenum}}</text>
		</view>
		<view class="row b-b">
			<text class="tit">姓名</text>
			<text class="text">{{distributor.dname}}</text>
		</view>
		<view class="row b-b">
			<text class="tit">性别</text>
			<text class="text">{{distributor.dsex}}</text>
		</view>
		<view class="row b-b">
			<text class="tit">地址</text>
			<text class="text">{{distributor.daddress}}</text>
		</view>
		
		<view class="row b-b">
			<text class="tit">积分</text>
			<text class="text">{{distributor.dintegral}}</text>
		</view>
		
		<view class="row b-b">
			<text class="tit">账号状态</text>
			<text class="text" v-if="distributor.dstatus == 1">
				已开启
			</text>
			<text class="text" v-else>
				已关闭
			</text>
			
		</view>
		
		
		
		<button class="add-btn" @click="toReturn">返回</button>
	</view>
</template>

<script>
	var _self, distributor
	export default {
		data() {
			return {
				distributor: {}
			}
		},
		methods: {
			toReturn:function(){
				uni.switchTab({
					url:"xjgl"
				})
			}
		},
		onLoad(item) {
			_self = this;
			_self.distributor = JSON.parse(item.distributor);
			console.log(_self.distributor);
			//var distributor = uni.getStorageSync("distributor");
			//uni.removeStorageSync("distributor");
			
		}
	}
</script>

<style lang="scss">
	page{
		background: $page-color-base;
		padding-top: 16upx;
	}

	.row{
		display: flex;
		align-items: center;
		position: relative;
		padding:0 30upx;
		height: 110upx;
		background: #fff;
		border-bottom: 1px solid $border-color-base;
		
		.tit{
			flex-shrink: 0;
			width: 200upx;
			font-size: 35upx;
			color: $font-color-dark;
		}
		.input{
			flex: 1;
			font-size: 30upx;
			color: $font-color-dark;
		}
		.text{
			font-size: 30upx;
			color: $font-color-dark;
			text-align: right;
		}
		.icon-shouhuodizhi{
			font-size: 36upx;
			color: $font-color-light;
		}
	}
	.default-row{
		margin-top: 16upx;
		.tit{
			flex: 1;
		}
		switch{
			transform: translateX(16upx) scale(.9);
		}
	}
	.add-btn{
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		margin: 60upx auto;
		font-size: $font-lg;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
</style>

