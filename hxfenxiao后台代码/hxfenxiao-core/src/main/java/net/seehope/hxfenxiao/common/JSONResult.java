 package net.seehope.hxfenxiao.common;

import io.swagger.annotations.ApiModel;

@ApiModel("用于封装请求返回值的内容")
/**
 * 专门返回给前端的封装，200代表成功，其他都为失败
 * 
 * 1301 app端手机验证码错误 
 * 
 * 2031 mall登录时验证码输入错误或者验证码过期
 * 2032 mall端用户注册的手机号码已经存在
 * 
 * 2034 mall端用户登录的手机号码不存在
 * 2035 mall端当前手机号码不存在 登录失败
 * 
 * 2102 注册时当前号码存在 注册失败
 * 2104 注册时输入验证码错误
 * 
 * @author 100个nice
 * @date 2019/12/13
 */

public class JSONResult {

     private Integer status;
     
     private Object data;
     
     private String msg;

     
     public static JSONResult isOk(Object data) {
         return new JSONResult(200, data, "request success");
     }
     
     public static JSONResult isErr(Integer status,String msg) {
         
         return new JSONResult(status, null, msg);
     }
     
    public JSONResult() {
        super();
    }

    public JSONResult(Integer status, Object data, String msg) {
        super();
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
     
     
     
     
}
