<template>
	<view class="content b-t">
		<view class="list" v-for="(item, index) in distributorList" :key="index" @tap="details(item)">
			<view class="wrapper">
				<view class="address-box">
					<text class="address">经销商编号 : {{item.did}}</text>
				</view>
				<view class="u-box">
					<text class="name"> {{item.dname}} </text>
					<text class="mobile"> {{item.dphonenum}} </text>
				</view>
			</view>
			<!-- <text class="yticon icon-bianji" @click.stop="addAddress('edit', item)"></text> -->
		</view>
		
	</view>
</template>

<script>
	var _self , logRes , did
	export default {
		data() {
			return {
				distributorList: []
			}
		},
		onLoad(option){
			_self = this;
			logRes = _self.checkLogin('../xjgl/xjgl',2);
			console.log(logRes[0]);
			_self.did = logRes[0];
			
			uni.request({
				url: _self.apiServer+'app/distributor/getSubordinates',
				method: 'GET',
				data: {
					did : _self.did
				},
				success: res => {
					_self.distributorList = res.data.data;
					console.log(_self.distributorList[0]);
					
				},
				fail: () => {},
				complete: () => {}
			});
		},
		methods: {
			//选择地址
			details(item){
				//console.log(item);
				var distributor = JSON.stringify(item);
				
				uni.redirectTo({
					url: 'xjdetail?distributor='+distributor
				});
			}
		}
	}
</script>

<style lang='scss'>
	page{
		padding-bottom: 120upx;
	}
	.content{
		position: relative;
	}
	.list{
		display: flex;
		align-items: center;
		padding: 20upx 30upx;;
		background: #fff;
		position: relative;
		border-color:#DD524D;
		
	}
	.wrapper{
		display: flex;
		flex-direction: column;
		flex: 1;
		border-color:#DD524D;
		
	}
	.address-box{
		display: flex;
		align-items: center;
		.tag{
			font-size: 24upx;
			color: $base-color;
			margin-right: 10upx;
			background: #fffafb;
			border: 1px solid #ffb4c7;
			border-radius: 4upx;
			padding: 4upx 10upx;
			line-height: 1;
		}
		.address{
			font-size: 30upx;
			color: $font-color-dark;
		}
	}
	.u-box{
		font-size: 28upx;
		color: $font-color-light;
		margin-top: 16upx;
		.name{
			margin-right: 30upx;
		}
	}
	.icon-bianji{
		display: flex;
		align-items: center;
		height: 80upx;
		font-size: 40upx;
		color: $font-color-light;
		padding-left: 30upx;
	}
	
	.add-btn{
		position: fixed;
		left: 30upx;
		right: 30upx;
		bottom: 16upx;
		z-index: 95;
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		font-size: 32upx;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);		
	}
	
	.yticon {
		font-family: "yticon" !important;
		font-size: 16px;
		font-style: normal;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
	}
</style>

