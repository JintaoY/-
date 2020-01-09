<template>
	<view>
		<cmd-nav-bar back title="修改密码"></cmd-nav-bar>
		<cmd-page-body type="top">
			<cmd-transition name="fade-up">
				<view class="modify">
					<view class="modify-phone">
						<cmd-input v-model="mobile.phone" type="number" maxlength="11" placeholder="请输入手机号"></cmd-input>
						<view class="modify-phone-getcode" :style="safety.state ? 'color: #2196F3;' : ''" @tap="!safety.state ? fnGetPhoneCode() : ''">
							{{ (!safety.state && '获取验证码') || safety.time + ' s' }}
						</view>
					</view>
					<view class="modify-code"><cmd-input v-model="mobile.validateCode" type="number" maxlength="6" placeholder="请输入验证码"></cmd-input></view>
					<!--          <view class="modify-password">
            <cmd-input v-model="mobile.passwordOne" type="password" displayable maxlength="26" placeholder="请输入新密码"></cmd-input>
          </view>
          <view class="modify-password">
            <cmd-input v-model="mobile.passwordTwo" type="password" maxlength="26" placeholder="请再次确认新密码"></cmd-input>
          </view> -->
					<button class="btn-modify" :class="modifyMobile ? 'btn-modify-active' : ''" :disabled="!modifyMobile" hover-class="btn-modify-hover" @tap="fnModify">
						提交
					</button>
				</view>
			</cmd-transition>
		</cmd-page-body>
	</view>
</template>

<script>
import cmdNavBar from '@/components/cmd-nav-bar/cmd-nav-bar.vue';
import cmdPageBody from '@/components/cmd-page-body/cmd-page-body.vue';
import cmdTransition from '@/components/cmd-transition/cmd-transition.vue';
import cmdInput from '@/components/cmd-input/cmd-input.vue';

var _self, did;
export default {
	components: {
		cmdNavBar,
		cmdPageBody,
		cmdTransition,
		cmdInput
	},

	data() {
		return {
			mobile: {
				phone: '',
				//手机验证码
				validateCode: ''
				// passwordOne: '',
				// passwordTwo: ''
			},
			// passwordReg: /^\w+$/,
			phoneReg: /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/,
			safety: {
				time: 60,
				state: false,
				interval: ''
			},
			modifyMobile: false
		};
	},

	watch: {
		/**
		 * 监听表单数值
		 */
		mobile: {
			handler(newValue) {
				if (
					this.phoneReg.test(newValue.phone) &&
					newValue.validateCode.length == 6 /* && newValue.passwordOne.length >= 8 &&
            newValue.passwordOne == newValue.passwordTwo */
				) {
					this.modifyMobile = true;
				} else {
					this.modifyMobile = false;
				}
			},
			deep: true
		}
	},

	methods: {
		/**
		 * 提交按钮点击执行
		 */
		fnModify() {
			var _self = this;
			//获取经销商编号id
			var logRes = _self.checkLogin('../person/modify', 1);
			console.log(logRes[0]);
			_self.did = logRes[0];

			uni.request({
				url: _self.apiServer + 'app/distributor/updataDistributor',
				method: 'POST',
				header: { 'content-type': 'application/x-www-form-urlencoded' },
				data: {
					did: _self.did,
					mobile: _self.mobile.phone,
					validateCode: _self.mobile.validateCode
				},
				success: res => {
					console.log(res);
					console.log(res.data.status);
					//从后台传过来一个json对象  获取其状态
					if (res.data.status == 200) {
						//清除缓存ID
						uni.removeStorageSync('DID');
						uni.showModal({
							title: '提交成功',
							content: '请重新登录',
							success: function (res) {
							        if (res.confirm) {
							            uni.reLaunch({ url: '/pages/login/login' });
							        } else if (res.cancel) {
							            uni.reLaunch({ url: '/pages/login/login' });
							        }
							    }})
						
					} else if (res.data.status == 1301) {
						uni.showToast({
							title: '验证码错误，请输入正确的验证码',
							icon: 'none'
						});
					} else {
						//当失败时显示失败信息
						uni.showToast({ title: res.data.msg });
					}
				},
				fail: e => {
					console.log(e);
					uni.showToast({
						title: '验证码获得失败，请重新尝试!',
						icon: 'none'
					});
				},
				complete: () => {}
			});
		},
		/**
		 * 获取验证码
		 */
		fnGetPhoneCode: function() {
			var _self = this;
			if (this.phoneReg.test(this.mobile.phone)) {
				uni.request({
					url: _self.apiServer + 'app/distributor/getValidateCode',
					method: 'GET',
					data: {
						mobile: _self.mobile.phone
					},
					success: res => {
						// 成功后显示倒计时60s后可在点击
						this.safety.state = true;
						// 倒计时
						this.safety.interval = setInterval(() => {
							if (this.safety.time-- <= 0) {
								this.safety.time = 60;
								this.safety.state = false;
								clearInterval(this.safety.interval);
							}
						}, 1000);
						if (res.data.status == 200) {
							uni.showToast({
								title: '发送成功',
								icon: 'success'
							});
							//进行验证码赋值  当有条件的情况下 可以用短信取代
							_self.mobile.validateCode = res.data.data;
						} else {
							uni.showToast({
								title: res.data.msg,
								icon: 'none'
							});
						}
					}
				});
			} else {
				uni.showToast({
					title: '手机号不正确',
					icon: 'none'
				});
			}
		}
	},

	beforeDestroy() {
		/**
		 * 关闭页面清除轮询器
		 */
		clearInterval(this.safety.interval);
	}
};
</script>

<style>
.modify {
	margin-top: 118upx;
	margin-right: 72upx;
	margin-left: 72upx;
}

.modify-phone {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	border-bottom: 2upx #dedede solid;
	margin-top: 118upx;
	margin-bottom: 40upx;
}

.modify-phone-getcode {
	color: #3f51b5;
	text-align: center;
	min-width: 140upx;
}

.modify-password,
.modify-code {
	margin-bottom: 40upx;
	border-bottom: 2upx #dedede solid;
}

.btn-modify {
	margin-top: 100upx;
	border-radius: 50upx;
	font-size: 16px;
	color: #fff;
	background: linear-gradient(to right, #88a1f9, #9ac6ff);
}

.btn-modify-active {
	background: linear-gradient(to right, #365fff, #36bbff);
}

.btn-modify-hover {
	background: linear-gradient(to right, #365fdd, #36bbfa);
}

button[disabled] {
	color: #fff;
}
</style>
