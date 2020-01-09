 package net.seehope.hxfenxiao.config.validate;

import org.springframework.web.context.request.ServletWebRequest;


/**
 * 验证码的存储规范 接口
 * @author 100个nice
 * @date 2019/12/14
 */
public interface ValidateCodeRepository  {
     
    
    /**
     * 
     * @param request
     * @param validateCode
     * @param support  是为了手机端使用的  设备ID
     */
     void save(ServletWebRequest request, ValidateCode validateCode, String support);

     ValidateCode get(ServletWebRequest request, String validateCode, String support);

     void remove(ServletWebRequest request, String validateCode, String support);
     
}
