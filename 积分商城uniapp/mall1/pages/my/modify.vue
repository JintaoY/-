<template>
	<view class="content">
		<view class="row b-b">
			<text class="tit">昵称</text>
			<input class="input" type="text" v-model="user.uusername" />
		</view>
		<view class="row b-b">
			<text class="tit">真实姓名</text>
			<text class="text">{{user.urealname}}</text>
			
		</view>
		
		<view class="row b-b">
			<text class="tit">性别</text>
						<radio-group @change="radioChange">
						   <view>
						       <label class="radio"><radio :checked="'男'== user.usex ?true:false"
						   			 value="男"/>  男  </label>
						       <label class="radio"><radio :checked="'女'== user.usex ?true:false"
						   			 value="女"/>  女  </label>
						   </view> 
						</radio-group>
		</view>
		<view class="row b-b">
			<text class="tit">手机号</text>
			<input class="input" type="text" maxlength="11" v-model="user.uphonenum" />
		</view>
		
		<view class="row b-b">
			<text class="tit">地址</text>
			<input class="input" type="text"  v-model="user.uaddress" />
		</view>
		
		<button class="add-btn" @tap="toGo">确定</button>
	</view>
</template>

<script>
	var _self
	export default {
		data() {
			return {
				phoneReg: /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/,
				user: {}
			}
		},
		methods: {
			radioChange(e){
				_self = this;
				_self.user.usex = e.target.value;
			},
			toGo(){
				_self = this;
				var userid = _self.user.userid;
				var phone = _self.user.uphonenum;
				var uusername = _self.user.uusername;
				var uaddress = _self.user.uaddress;
				var usex = _self.user.usex;
				if(uusername != "" && uusername != null){
					if(phone != "" && phone != null){
						if(_self.phoneReg.test(phone) && phone.length == 11){
							if(uaddress != "" && uaddress != null){
							uni.request({
								url: _self.apiServer + 'mall/user/updateUser',
								method: 'POST',
								header: { 'content-type': 'application/x-www-form-urlencoded' },
								data: {
									userid: userid,
									uphoneNum: phone,
									uusername: uusername,
									uaddress: uaddress,
									usex: usex
								},
								success: res => {
									console.log(res);
									console.log(res.data.status);
									if(res.data.status == 2122){
										uni.showToast({
											title: '该昵称不可用',
											icon: 'none'
										});
									}else{
										uni.showModal({
											title: '提交成功',
											success: function (res) {
											        if (res.confirm) {
											            uni.reLaunch({ url: '/pages/my/my' });
											        } else if (res.cancel) {
											            console.log("用户点击取消")
											        }
											    }})
										
									}
								},
								fail: e => {
									console.log(e);
								},
								complete: () => {}
							})
							}else{
								uni.showToast({
									title: '地址不能为空',
									icon: 'none'
								});
							}
						}else{
							uni.showToast({
								title: '手机号格式不对',
								icon: 'none'
							});
						}
					}else{
						uni.showToast({
							 title: '手机号不能为空',
							 icon: 'none'
						})
					}
				}else{
					uni.showToast({
						 title: '昵称不能为空',
						 icon: 'none'
					})
				}
			}
			
		},
		onLoad(item) {
			_self = this;
			_self.user = JSON.parse(item.user);
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

