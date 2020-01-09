<template>
	<view class="content">
		<view class="row b-b">
			<text class="tit">原密码</text>
			<input class="input" password type="text" placeholder="填写原密码" v-model="oldPassword" />
		</view>

		<view class="row b-b">
			<text class="tit">新密码</text>
			<input class="input" password type="text" maxlength="12" placeholder="填写新密码" v-model="newPassword" />
		</view>

		<view class="row b-b">
			<text class="tit">确认密码</text>
			<input class="input" password type="text" maxlength="12" placeholder="请再次输入新密码" v-model="againPassword" />
		</view>

		<view class="padding">密码必须为6-12位的数字、字符的组合（不能是纯数字）</view>
		<button class="add-btn" @tap="toGo">确定</button>
	</view>
</template>

<script>
var _self, logRes, userid;
export default {
	data() {
		return {
			passwordReg: /^[a-zA-Z0-9]{6,12}$/,
			oldPassword: '',
			newPassword: '',
			againPassword: ''
		};
	},
	methods: {
		toGo() {
			_self = this;
			if (_self.oldPassword != '' && _self.oldPassword != null) {
				if (_self.newPassword != '' && _self.newPassword != null) {
					if(_self.againPassword != '' && _self.againPassword != null){
						if (_self.passwordReg.test(_self.newPassword) && _self.passwordReg.test(_self.againPassword)) {
							if (_self.newPassword == _self.againPassword) {
								uni.request({
									url: _self.apiServer + 'mall/user/updatepassword',
									method: 'POST',
									header: { 'content-type': 'application/x-www-form-urlencoded' },
									data: {
										userid: _self.userid,
										oldPassword: _self.oldPassword,
										newPassword: _self.againPassword
									},
									success: res => {
										console.log(res);
										console.log(res.data.status);
										if (res.data.status == 2233) {
											uni.showToast({
												title: '原密码输入错误',
												icon: 'none'
											});
										} else {
											uni.showModal({
												title: '修改成功',
												showCancel:false,
												success: function(res) {
													if (res.confirm) {
														uni.redirectTo({ url: '/pages/set/set' });
													}
												}
											});
										}
									},
									fail: e => {
										console.log(e);
									},
									complete: () => {}
								});
							} else {
								uni.showToast({
									title: '两次新密码输入不一致',
									icon: 'none'
								});
							}
						} else {
							uni.showToast({
								title: '密码格式不对',
								icon: 'none'
							});
						}
					}else{
						uni.showToast({
							title: '请再次输入新密码',
							icon: 'none'
						});
					}
				} else {
					uni.showToast({
						title: '请填写新密码',
						icon: 'none'
					});
				}
			} else {
				uni.showToast({
					title: '请填写原密码',
					icon: 'none'
				});
			}
		}
	},
	onLoad(item) {
		_self = this;
		logRes = _self.checkLogin('../set/modify_password', 1);
		console.log(logRes[0]);
		_self.userid = logRes[0];
	}
};
</script>

<style lang="scss">
page {
	background: $page-color-base;
	padding-top: 16upx;
}

.row {
	display: flex;
	align-items: center;
	position: relative;
	padding: 0 30upx;
	height: 110upx;
	background: #fff;
	border-bottom: 1px solid $border-color-base;

	.tit {
		flex-shrink: 0;
		width: 200upx;
		font-size: 35upx;
		color: $font-color-dark;
	}
	.input {
		flex: 1;
		font-size: 30upx;
		color: $font-color-dark;
	}
	.text {
		font-size: 30upx;
		color: $font-color-dark;
		text-align: right;
	}
	.icon-shouhuodizhi {
		font-size: 36upx;
		color: $font-color-light;
	}
}
.default-row {
	margin-top: 16upx;
	.tit {
		flex: 1;
	}
	switch {
		transform: translateX(16upx) scale(0.9);
	}
}
.add-btn {
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
