<template>
	<view>
		<view class="input-content">
			
			<view class="input-item">
				<text class="tit">产品编号</text>
				<input 
					type="text" 
					placeholder="请输入您的产品编号"
					v-model="productCode"
				/>
			</view>
		</view>
			
		<button class="getCode-btn" @tap="getIntegral" >兑换积分</button>
		<button class="toReturn-btn" @tap="toReturn" >返回</button>
		
		<view class="msgview">
			<text class="msgtext">{{msg}}</text>
		</view>
		
	</view>
	
	
</template>

<script>
	var _self , logRes;
	export default {
		data() {
			return {
				userid:'',
				msg:'',
				productCode:''
			}
		},
		onLoad() {
			_self = this;
			
			logRes = _self.checkLogin('../my/getIntegral',1);
			
			_self.userid = logRes[0];
			
			//console.info(_self.did);
		},
		methods: {
			//返回
			toReturn:function(){
				uni.navigateBack({
					delta: 1
				});
			},
			//获得积分
			getIntegral:function(){
				console.log(_self.did);
				console.log(_self.productCode);
				
				uni.request({
					url: _self.apiServer+'mall/code/getIntegral',
					method: 'PUT',
					data: {
						'productCode':_self.productCode,
						'userid':_self.userid
					},
					header: {'content-type' : "application/x-www-form-urlencoded"},
					success: res => {
						console.log(res.data);
						//当状态码为200 成功
						if(res.data.status == 200){					
							uni.showToast({
								title:"恭喜您,兑换成功",
								icon:"success",
								
							})
							_self.msg = "恭喜您，兑换成功！"
							
						}
						//判断是否为正品
						else if(res.data.status == 10012){
							uni.showToast({
								title:res.data.msg,
								icon:"none",
								
							})
							_self.msg = res.data.msg
							
						}
						//判断是否为窜货
						else if(res.data.status == 10013){
							uni.showToast({
								title:res.data.msg,
								icon:"none",
								
							})
							_self.msg = res.data.msg
							
						}
						//判断是否已经被领取了积分
						else if(res.data.status == 10014){
							uni.showToast({
								title:res.data.msg,
								icon:"none",
								
							})
							_self.msg = res.data.msg
							
						}
						//判断userid的用户是否存在
						else if(res.data.status == 10015){
							uni.showToast({
								title:res.data.msg,
								icon:"none",
								
							})
							_self.msg = res.data.msg
							
						}else{
							uni.showToast({
								title:res.data.msg,
								icon:"none",
								
							})
							_self.msg = res.data.msg
						}
						
					},
					fail: (e) => {
						console.log(e);
						_self.msg = "网络不流畅，请稍后再试"
					},
					complete: () => {}
				});
				
			}
		}
	}
</script>

<style  lang='scss'>
	.input-content{
		padding: 60upx;
	}
	.input-item{
		display:flex;
		flex-direction: column;
		align-items:flex-start;
		justify-content: center;
		padding: 0 30upx;
		background:$page-color-light;
		height: 120upx;
		border-radius: 4px;
		margin-bottom: 50upx;
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
	
	/* 兑换积分按钮css */
	.getCode-btn{
		width: 300upx;
		height: 76upx;
		line-height: 76upx;
		border-radius: 50px;
		margin-top: 10upx;
		background: $uni-color-success;
		color: #fff;
		font-size: $font-lg;
		&:after{
			border-radius: 100px;
		}
	}
	/* 返回按钮css */
	.toReturn-btn{
		width: 300upx;
		height: 76upx;
		line-height: 76upx;
		border-radius: 50px;
		margin-top: 50upx;
		background: $uni-color-warning;
		color: #fff;
		font-size: $font-lg;
		&:after{
			border-radius: 100px;
		}
	}
	
	.msgview{
		padding: 60upx;
		height: 76upx;
		text-align: center;
	}
	
	.msgtext{
		font-size: $font-sm+2upx;
		color: $font-color-base;
	}
	
</style>
