<template>
	<view>
		<view class="goods-list">
			
			<view 
				v-for="(item, index) in goodsList" :key="index"
				class="goods-item"
				
			>
				<view class="image-wrapper">
					<image :src="img+item.productimage" mode="aspectFill"></image>
				</view>
				<text class="title clamp" id="productname" name="productname" v-model="productname">{{item.productname}}</text>
				<view class="price-box">
					<text class="price" v-if="dlevel==1">{{item.productprice1}}</text>
					<text class="price" v-else="dlevel==2">{{item.productprice2}}</text>
					<text class="price" v-else="dlevel==3">{{item.productprice3}}</text>
					<!-- <text class="price" >{{dlevel==1?item.productprice1:dlevel==2?item.productprice2:item.productprice3}}</text> -->
					<text>已售 {{item.productsale}}</text>
				</view>
				<view class="input-item">
					<text class="tit">请输入购买数量</text>
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
	var _self, options, logRes, dlevel, hid, productname, pnum
	export default {
		data() {
			return {
				goodsList:[],
				img : ''
			}
		},
		onLoad:function(options){
			_self = this;
			_self.img = _self.imgServer
			
			logRes = this.checkLogin('../product/product',2);
			console.log(logRes[0]);
			dlevel=uni.getStorageSync('DLEVEL');
			console.log(dlevel);
			hid=uni.getStorageSync('HID');
			console.log(hid);
		    _self.findAll();	
		},
		onShow:function(){
			_self = this;
			logRes = this.checkLogin('../product/product',2);
		},
		
		methods: {
			findAll:function(){	
				uni.request({
					url: this.apiServer+'app/product/findall',
					method: 'GET',
					success:function(res){
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
				uni.setStorageSync('OSendid',hid),
				uni.setStorageSync('OReceiverid',logRes);
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
				content: '￥';
				font-size: 26upx;
			}
		}
	}
</style>
