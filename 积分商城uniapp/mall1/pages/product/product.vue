<template>
	<view>
		<view class="goods-list">
			
			<view 
				v-for="(item, index) in goodsList" :key="index"
				class="goods-item"
				
			>
				<view class="image-wrapper">
					<image :src="'http://49.235.2.40:8080/hx/'+item.productimage" mode="aspectFill"></image>
				</view>
				<text class="title clamp">{{item.productname}}</text>
				<view class="price-box">
					<text class="price" >兑换所需积分: {{item.productchangescore}}</text>
					
					
					
				</view>
				<view class="input-item">
					<text class="tit">请输入兑换数量</text>
					<input 
						type="text" 
						placeholder="在此输入"
						v-model="pnum"
						
					/>
				</view>
				
					<button class="getCode-btn"  @tap="order(item)">加入订单</button>
				
				
			</view>
		</view>
	</view>
</template>

<script>
	var _self,options,logRes,dlevel,hid,pnum
	export default {
		data() {
			return {
				goodsList:[]
			}
		},
		onLoad:function(options){
			_self = this;
			
			logRes = _self.checkLogin('../product/product',2);
			console.log(logRes[0]);
			
			
		    _self.findAll();
			
					
		},
		onShow:function(){
			
			_self = this;
			logRes = _self.checkLogin('../product/product',2);
		},
		methods: {
			findAll:function(){
				
				
				
				uni.request({
					url: this.apiServer+'mall/product/findall',
					method: 'GET',
					success: res => {
						console.log(res);
						_self.goodsList=res.data;
						console.log(_self.goodsList);
						
					},
					fail: (e) => {
						console.log(e);
						
					},
					complete: () => {}
				});
				
			},
			order:function(item){
				// uni.setStorageSync('OPid',pid),
				
				// uni.setStorageSync('OPnum',_self.pnum),
			   var prod= JSON.stringify(item);
			   uni.redirectTo({
				url: '../order/Buy/Buy?product='+prod+'&&pnum='+_self.pnum
			   });
					
				
			}
			
			
		},
		
	}
</script>

<style lang="scss">
	.getCode-btn{
		width: 300upx;
		height: 76upx;
		line-height: 76upx;
		border-radius: 50px;
		margin-top: 5upx;
		background: $uni-color-success;
		color: #fff;
		font-size: $font-lg;
		&:after{
			border-radius: 100px;
		}
	}
	.input-content{
		padding: 0 60upx;
	}
	.input-item{
		display:flex;
		flex-direction: column;
		align-items:flex-start;
		justify-content: center;
		padding: 0 30upx;
		background:$page-color-light;
		height: 100upx;
		border-radius: 4px;
		margin-bottom: 20upx;
		&:last-child{
			margin-bottom: 0;
		}
		.tit{
			height: 50upx;
			line-height: 56upx;
			font-size: $font-sm+2upx;
			color: $font-color-base;
		}
		input{
			height: 60upx;
			font-size: $font-base + 2upx;
			color: $font-color-dark;
			width: 100%;
		}	
	}
/* 商品列表 */
	.goods-list{
		display:flex;
		flex-wrap:wrap;
		padding: 0 30upx;
		background: #fff;
		.goods-item{
			display:flex;
			flex-direction: column;
			width: 48%;
			padding-bottom: 40upx;
			&:nth-child(2n+1){
				margin-right: 4%;
			}
		}
		.image-wrapper{
			width: 100%;
			height: 330upx;
			border-radius: 3px;
			overflow: hidden;
			image{
				width: 100%;
				height: 100%;
				opacity: 1;
			}
		}
		.title{
			font-size: $font-lg;
			color: $font-color-dark;
			line-height: 80upx;
		}
		.price-box{
			display: flex;
			align-items: center;
			justify-content: space-between;
			padding-right: 10upx;
			font-size: 24upx;
			color: $font-color-light;
		}
		.price{
			font-size: $font-lg;
			color: $uni-color-primary;
			line-height: 1;
			&:before{
				// content: '￥';
				font-size: 26upx;
			}
		}
	}
</style>
