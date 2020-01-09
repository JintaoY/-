 package net.seehope.hxfenxiao.web.controller;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seehope.hxfenxiao.UserService;
import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.pojo.User;

@RestController
@RequestMapping("/mall/user")
 public class UserController {
    
    
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private final static String VALIDATECODE_REDIS_USER = "validateCode_user";
    
    
    @PostMapping("/register")
    public JSONResult register() {
        
        return JSONResult.isOk(null);
    }
    

    /**
     * 用户登录时获得验证码
     * @param mobile
     * @return 2034为mall端登录时手机号码不存在
     * @return 200 获得验证码成功
     */
    @GetMapping("/getValidateCodeLogin")
    public JSONResult getValidateCodeLogin(String mobile) {
        
        int count = userService.findByuphoneNum(mobile);
        
        if(count ==  0) {
            //2034为mall端登录时手机号码不存在
            return JSONResult.isErr(2034, "当前手机号码不存存，请注册！");
        }
        
        //当注册的手机号码存在时 发送验证码
        String validateCode = RandomStringUtils.randomNumeric(6);
        System.out.println(validateCode);
        
        redisTemplate.opsForValue().set(VALIDATECODE_REDIS_USER+mobile, validateCode,5,TimeUnit.MINUTES);
        
        
        return JSONResult.isOk(validateCode);
    }
    
    /**
     * 用户进行登录
     * @param mobile
     * @param validateCode
     * @return 2301 登录时验证码输入错误或者验证码过期
     * @return 2305 mall端当前手机号码不存在 登录失败
     * 
     */
    @PostMapping("/login")
    public JSONResult login(String mobile,String validateCode) {
        
        String validateCodeIn = redisTemplate.opsForValue().get(VALIDATECODE_REDIS_USER+mobile);  
        
        if(!StringUtils.equals(validateCode, validateCodeIn)) {
            return JSONResult.isErr(2301, "验证码错误，请重新输入");
       }
        
        User user = userService.login(mobile);
        //当user为null是说明手机号码不存在，登录失败 返回
        if(user == null) {
            return JSONResult.isErr(2035, "当前手机号码不存在,登录失败");
        }
        
        return JSONResult.isOk(user);
    }
    
    
    /**
     * 用户注册时获得验证码
     * @param mobile
     * @return 2102 注册时当前号码存在 注册失败
     */
    @GetMapping("/getValidateCodeRegister")
    public JSONResult getValidateCodeRegister(String mobile) {
        
        int count = userService.findByuphoneNum(mobile);
        //当前的手机号码 存在时  注册失败
        if(count != 0) {
            return JSONResult.isErr(2102, "当前号码存在,注册失败");
        }
        
        //当前手机号码不存在时 发送手机验证码
        String validateCode = RandomStringUtils.randomNumeric(6);
        System.out.println(validateCode);
        
        redisTemplate.opsForValue().set(VALIDATECODE_REDIS_USER+mobile, validateCode);
        
        return JSONResult.isOk(validateCode);
    }
    
    /**
     * 用户注册
     * @param mobile
     * @param validateCode
     * @return 2104 注册时输入验证码错误
     */
    @PutMapping("/register")
    public JSONResult register(String mobile,String validateCode) {
        
        String validateCodeIn = redisTemplate.opsForValue().get(VALIDATECODE_REDIS_USER+mobile);
        
        if(!StringUtils.equals(validateCode, validateCodeIn)) {
            return JSONResult.isErr(2104, "验证码输入错误，请重新输入");
        }
        
        //创建一个用户
        User user = userService.register(mobile);
        
        return JSONResult.isOk(user);
    }
    
    
    @GetMapping("/findByUserid")
    public JSONResult findByUserid(String userid) {
        
        System.out.println(userid);
        
        User user = userService.findByUserid(userid);
        
        return JSONResult.isOk(user);
        
    }
    
    /**
     * 修改个人信息
     * @data 2019年12月25日 上午11:31:56
     * @author xxx
     * @return
     */
    @PostMapping("/updateUser")
    public JSONResult updateUser(String userid,String uusername,String usex,String uaddress,String uphoneNum) {
    	
    	System.out.println(userid+","+uusername+","+usex);
    	//判断是否有相同用户名
    	int count = userService.findByUusername(uusername,userid);
         
        if(count != 0) {
            return JSONResult.isErr(2122, "存在相同用户名");
        }
        User user = new User();
        user.setUaddress(uaddress);
        user.setUserid(userid);
        user.setUsex(usex);
        user.setUphonenum(uphoneNum);
        user.setUusername(uusername);
    	userService.updateUser(user);
    	return JSONResult.isOk(null);
    }
    
    /**
     * 修改密码
     * @data 2019年12月25日 上午11:32:36
     * @author xxx
     * @return
     */
    @PostMapping("/updatepassword")
   public JSONResult updatePassWord(String userid,String oldPassword,String newPassword) {
    	System.out.println("id:"+userid+",oldpw:"+oldPassword+",newpw:"+newPassword);
    	//检查输入的原密码是否正确
    	User us = userService.findByUserid(userid);
    	String upassword = us.getUpassword();
    	if (!upassword.equals(oldPassword)) {
			return JSONResult.isErr(2233, "原密码输入错误");
		}
    	User user = new User();
    	user.setUserid(userid);
    	user.setUpassword(newPassword);
    	userService.updateUser(user);
    	return JSONResult.isOk("ok");
   }
}
