 package net.seehope.hxfenxiao.config.validate;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
  * 所有验证码的父类 有图片 有图补  有短信 有邮箱 有指纹 有人眼识别
  * 
  * @author 100个nice
  * @date 2019/12/14
  */
 public class ValidateCode implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
     
    //验证码
    private String code;
    
    //过期的时间
    private LocalDateTime expireTime;

    public ValidateCode() {
        super();
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        super();
        this.code = code;
        this.expireTime = expireTime;
    }

    
    //构造器 参数为验证码 和多少时间后过期的  可以获得过期的时间
    public ValidateCode(String code, Integer expireIn) {
        super();
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    
    /**
     * 判断当前时间是否已经过期
     * @return
     */
    public boolean isExpire() {
        return LocalDateTime.now().isAfter(this.getExpireTime());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
    
    
    
    
    
    

}
