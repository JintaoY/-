<template>
	<view class="content">
		
		<view class="row b-b">
			<text class="tit">地址</text>
			<input class="input" type="text" v-model="daddress" placeholder="请输入地址" placeholder-class="placeholder" />
		</view>
		
		<button class="add-btn" @tap="confirm">确定</button>
	</view>
</template>

<script>
	var _self , logRes;
	export default {
		data() {
			return {
				daddress:'',
				did:''
			}
		},
		onLoad(e){
			 _self = this;
			 logRes = _self.checkLogin('../person/addressManage',1);
			console.log(logRes[0]);
			_self.did = logRes[0];
			 _self.daddress = JSON.parse(e.daddress);
		},
		methods: {
			//提交
			confirm(){
				console.log(_self.did);
				uni.request({
					url: _self.apiServer + 'app/distributor/updataDaddress',
					method: 'POST',
					header: { 'content-type': 'application/x-www-form-urlencoded' },
					data: {
						did: _self.did,
						daddress:_self.daddress
					},
					success: res => {
						console.log(res);
						console.log(res.data.status);
						if (res.data.status == 200) {
							
							uni.reLaunch({ url: '/pages/person/person' });
							
						} else {
							//当失败时显示失败信息
							uni.showToast({ title: res.data.msg });
						}
					},
					fail: e => {
						console.log(e);
					},
					complete: () => {}
				});
			},
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
		
		.tit{
			flex-shrink: 0;
			width: 120upx;
			font-size: 30upx;
			color: $font-color-dark;
		}
		.input{
			flex: 1;
			font-size: 30upx;
			color: $font-color-dark;
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
