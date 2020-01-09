<template>
	<form>
	<view class="cu-form-group margin-top">
		<view class="title">举报经销商</view>
		<picker @change="PickerChange" :value="index" :range="dnamelist">
			<view class="picker">
				{{index>-1?dnamelist[index]:'请选择'}}
			</view>
		</picker>
	</view>
	<view class="cu-form-group align-start">
		<view class="title">举报内容</view>
		<textarea maxlength="-1" :disabled="modalName" @input="textareaBInput" placeholder="输入内容"></textarea>
	</view>
	<view class="padding flex flex-direction">
		<button class="cu-btn bg-blue lg" :disabled="modifyMobile" @tap="addComplaint">提交</button>
	</view>
	</form>
</template>

<script>
	var _self,logRes,userid;
	export default {
		data() {
			return {
				index: -1,
				modalName: true, 
				modifyMobile: true,
				content: '',
				dnamelist:[]
			};
		},
		onLoad() {
			_self = this;
			uni.request({
				url: _self.apiServer+'mall/complaint/allDname',
				method: 'GET',
				data: {
				},
				success: res => {
					_self.dnamelist = res.data.data;
					console.log(_self.dnamelist);
				},
				fail: () => {},
				complete: () => {}
			});
			
		},
		methods: {
			PickerChange(e) {
				this.index = e.detail.value;
			},
			textareaBInput(e) {
				this.content = e.detail.value;
			},
			//提交
			addComplaint() {
			_self = this;
			logRes = _self.checkLogin('../complaint/complaint',1);
			console.log(logRes[0]);
			_self.userid = logRes[0];
			uni.request({
				url: _self.apiServer + 'mall/complaint/addcomplaint',
				method: 'POST',
				header: { 'content-type': 'application/x-www-form-urlencoded' },
				data: {
					userid: _self.userid,
					dname: _self.dnamelist[_self.index],
					content: _self.content
				},
				success: res => {
					console.log(res);
					console.log(res.data.status);
					uni.showModal({
					    title: '举报成功',
					    success: function (res) {
					        if (res.confirm) {
					            console.log('用户点击确定');
								uni.navigateBack({
									delta:1
								})
					        } else if (res.cancel) {
					            console.log('用户点击取消');
					        }
					    }
					});
				},
				fail: e => {
					console.log(e);
				},
				complete: () => {}
			});
		},
		},
		watch:{
			index(e){
				this.modalName = false;
			},
			content(newVal,oldVal){
				if(newVal == ""){
					this.modifyMobile = true;
				}else{
					this.modifyMobile = false;
				}
			}
		}
	}
</script>

	
<style>
	@import url("../../colorui/animation.css");
	@import url("../../colorui/icon.css");
	@import url("../../colorui/main.css");
</style>
