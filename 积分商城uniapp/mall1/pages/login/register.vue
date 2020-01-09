<template>
	<view class="container">
		<view class="left-bottom-sign"></view>
		<view class="back-btn yticon icon-zuojiantou-up" @click="navBack"></view>
		<view class="right-top-sign"></view>
		<!-- 设置白色背景防止软键盘把下部绝对定位元素顶上来盖住输入框等 -->
		<view class="wrapper">
			<view class="left-top-sign">REGISTER</view>
			<view class="welcome">
				欢迎新用户注册!
			</view>
			<view class="input-content">
				
				<view class="input-item">
					<text class="tit">手机号码</text>
					<input 
						type="text" 
						placeholder="请输入您的手机号码"
						maxlength="11"
						@confirm="toLogin"
						v-model="mobile"
					/>
				</view>
				
				<view class="input-item">
					<text class="tit">验证码</text>
					<input 
						type="text" 
						placeholder="请输入验证码"
						v-model="validateCode"
					/>
				</view>
				<button class="getCode-btn" @tap="getValidate" >获得验证码</button>
				<!-- <view>{{validate}}</view> -->
				
			</view>
			<button class="confirm-btn" @tap="toRegister" :disabled="logining">注册</button>
			
		</view>
		<view class="register-section">
			已经有账号?
			<text @tap="toLogin">去登录</text>
		</view>
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
				mobile:"",
				//手机验证码
				validateCode:""
			}
		},
		methods: {
			toRegister:function(){
				
				//判断经销商编号、手机号和验证码是否正确的格式，错误不提交
				if(_self.validateCode.length != 6 ){uni.showToast({title:"请输入正确的验证码",icon:"none"});return;}
				if(_self.mobile.length != 11){uni.showToast({title:"请输入正确的手机号码",icon:"none"}); return;}
				
				uni.request({
					url: _self.apiServer+'mall/user/register',
					method: 'PUT',
					header: {'content-type' : "application/x-www-form-urlencoded"},
					data: {
						mobile : _self.mobile,
						validateCode : _self.validateCode
					},
					success: res => {
						console.log(res);
						console.log(res.data.status);
						//从后台传过来一个json对象  获取其状态	
						if(res.data.status == 200){
							
							uni.showToast({title:"注册成功"});
							uni.setStorageSync('USERID',res.data.data.userid);
							
							// 注册成功返回主页面
							uni.redirectTo({
								url:"../my/modify"
							})
						}
						else if(res.data.status == 2104){
							uni.showToast({
								title:"验证码错误，请输入正确的验证码",
								icon:"none"
							})
						}
						else{
							//当失败时显示失败信息
							uni.showToast({
								title:res.data.msg,
								icon:"none",
							});
						}
					},
					fail: (e) => {
						console.log(e);
						uni.showToast({
							title:"验证码获得失败，请重新尝试!",
							icon:"none"
						})
					},
					complete: () => {}
				});
				
			},
			//发送请求从后台获得验证码
			getValidate:function(){
				//_self.validateCode = '123456';
				if(_self.mobile.length!=11){
					uni.showToast({
						title:"请输入正确的手机号码",
						icon:"none"
					})
					return;
				}
				
				uni.request({
					url: _self.apiServer+'mall/user/getValidateCodeRegister',
					method: 'GET',
					data: {
						mobile:_self.mobile
					},
					success: res => {
						//console.log(res.data.data);
						//从后台传过来一个json对象  获取其状态
						if(res.data.status == 200){
							uni.showToast({
								title:"验证码有效期为5分钟",
								icon:"success"
								
							})
							//进行验证码赋值  当有条件的情况下 可以用短信取代
							_self.validateCode = res.data.data;
							
						}
						else if(res.data.status == 2102){
							uni.showToast({
								title:"当前号码存在,注册失败",
								icon:"none"
							})
						}
						else{
							
							uni.showToast({
								title:res.data.msg,
								icon:"none"
							})
						}
					},
					fail: (e) => {},
					complete: () => {}
				});
			},
			toLogin:function(){
				uni.navigateTo({
					url:"login"
				})
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
	.container{
		padding-top: 30px;
		position:relative;
		width: 100vw;
		height: 90vh;
		overflow: hidden;
		background: #fff;
	}
	.wrapper{
		position:relative;
		z-index: 90;
		background: #fff;
		padding-bottom: 40upx;
	}
	.back-btn{
		position:absolute;
		left: 40upx;
		z-index: 9999;
		padding-top: var(--status-bar-height);
		top: 40upx;
		font-size: 40upx;
		color: $font-color-dark;
	}
	.left-top-sign{
		font-size: 120upx;
		color: $page-color-base;
		position:relative;
		left: -16upx;
	}
	.right-top-sign{
		position:absolute;
		top: 80upx;
		right: -30upx;
		z-index: 95;
		&:before, &:after{
			display:block;
			content:"";
			width: 400upx;
			height: 80upx;
			background: #b4f3e2;
		}
		&:before{
			transform: rotate(50deg);
			border-radius: 0 50px 0 0;
		}
		&:after{
			position: absolute;
			right: -198upx;
			top: 0;
			transform: rotate(-50deg);
			border-radius: 50px 0 0 0;
			/* background: pink; */
		}
	}
	.left-bottom-sign{
		position:absolute;
		left: -270upx;
		bottom: -320upx;
		border: 100upx solid #d0d1fd;
		border-radius: 50%;
		padding: 180upx;
	}
	.welcome{
		position:relative;
		left: 50upx;
		top: -90upx;
		font-size: 46upx;
		color: #555;
		text-shadow: 1px 0px 1px rgba(0,0,0,.3);
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

	.confirm-btn{
		width: 630upx;
		height: 76upx;
		line-height: 76upx;
		border-radius: 50px;
		margin-top: 70upx;
		background: $uni-color-primary;
		color: #fff;
		font-size: $font-lg;
		&:after{
			border-radius: 100px;
		}
	}
	/* 获得验证码css */
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
	.forget-section{
		font-size: $font-sm+2upx;
		color: $font-color-spec;
		text-align: center;
		margin-top: 40upx;
	}
	.register-section{
		position:absolute;
		left: 0;
		bottom: 50upx;
		width: 100%;
		font-size: $font-sm+2upx;
		color: $font-color-base;
		text-align: center;
		text{
			color: $font-color-spec;
			margin-left: 10upx;
		}
	}
	.cu-form-group .title {
		min-width: calc(4em + 15px);
	}
</style>
