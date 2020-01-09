 package net.seehope.hxfenxiao.web.controller;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.seehope.hxfenxiao.DistributorService;
import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.pojo.Distributor;


/**
 * APP端 经销商管理
 * @author 100个nice
 * @date 2019/12/17
 */

@RestController
@RequestMapping("/app/distributor")
 public class DistributorController {
    
    
    private final static String VALIDATECODE_REDIS = "validateCode";
    
    @Autowired
    private DistributorService distributorService;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    
    
    @PostMapping("/login")
    public JSONResult login(String did,String mobile,String validateCode) {
        
        String validateCodeIn = redisTemplate.opsForValue().get(VALIDATECODE_REDIS+mobile);
        
        
        if(!StringUtils.equals(validateCode, validateCodeIn)) {
             return JSONResult.isErr(1301, "验证码错误，请重新输入");
        }
        
        Distributor distributor = distributorService.appLogin(did, mobile);
        
        return JSONResult.isOk(distributor);
    }
    
    
    //获得手机验证码  有条件改为真实手机验证码
    @GetMapping("/getValidateCode")
    public JSONResult getValidateCode(String mobile) {
        
        String validateCode = RandomStringUtils.randomNumeric(6);
        System.out.println(validateCode);
        
        /* 因为手机端没有cookie 和 session
         * sessionStrategy.setAttribute(new ServletWebRequest(request,response), VALIDATECODE_REDIS, validateCode);*/
        //设置验证码在5分钟后过期
        redisTemplate.opsForValue().set(VALIDATECODE_REDIS+mobile, validateCode,5,TimeUnit.MINUTES);
        
        return JSONResult.isOk(validateCode);
    }
    
    
    
    //根据did查询其下属经销商成员
    @GetMapping("/getSubordinates")
    public JSONResult getSubordinates(@Validated String did) {
        
        String[] dids = did.split("\\.");
        System.out.println(dids.length);
        
        if(dids.length>2) {
            return JSONResult.isOk(null);
        }
        
        else {
            List<Distributor> distributors = distributorService.getSubordinates(did);
            
            return JSONResult.isOk(distributors);
        }
        
    }
    
    /**
     * 查询经销商个人信息
     * @data 2019年12月18日 下午7:58:15
     * @author xxx
     * @param did
     * @return
     */
    @GetMapping("/findDistributorData")
    public JSONResult findDistributorData(String did) {
		
    	Distributor distributor = distributorService.findDistributorsData(did);
    	return JSONResult.isOk(distributor);
	}
    
    /**
     * 修改个人登录密码
     * @data 2019年12月18日 下午9:50:42
     * @author xxx
     * @param distributor
     */
    @PostMapping("/updataDistributor")
    public JSONResult updataDistributor(String did,String mobile,String validateCode) {
    	  String validateCodeIn = redisTemplate.opsForValue().get(VALIDATECODE_REDIS+mobile);
    	  System.out.println("did:"+did+"mobile:"+mobile);
    	  
    	  if(!StringUtils.equals(validateCode, validateCodeIn)) {
              return JSONResult.isErr(1301, "验证码错误，请重新输入");
         }else {
        	 Distributor distributor =  new Distributor();
        	 distributor.setDid(did);
        	 distributor.setDphonenum(mobile);
        	 distributorService.update1(distributor);
        	 return JSONResult.isOk("ok");
		}
    }
    
    /**
     * 修改性别
     * @data 2019年12月20日 下午4:00:38
     * @author xxx
     * @param did
     * @param dsex
     * @return
     */
    @PostMapping("/updataDsex")
    public JSONResult updataDsex(String did,String dsex) {
    	System.out.println("did:"+did+"dsex:"+dsex);
    	Distributor distributor =  new Distributor();
   	 	distributor.setDid(did);
   	 	distributor.setDsex(dsex);
   	 	distributorService.update1(distributor);
   	 	return JSONResult.isOk(null);
    }
    
    /**
     * 修改地址
     * @data 2019年12月23日 下午8:25:58
     * @author xxx
     * @param did
     * @param daddress
     * @return
     */
    @PostMapping("/updataDaddress")
    public JSONResult updataDaddress(String did,String daddress) {
    	System.out.println("did:"+did+"daddress:"+daddress);
    	Distributor distributor =  new Distributor();
   	 	distributor.setDid(did);
   	 	distributor.setDaddress(daddress);
   	 	distributorService.update1(distributor);
   	 	return JSONResult.isOk(null);
    }
}
