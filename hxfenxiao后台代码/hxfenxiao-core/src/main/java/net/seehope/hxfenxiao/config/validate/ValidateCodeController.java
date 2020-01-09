 package net.seehope.hxfenxiao.config.validate;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;



import net.seehope.hxfenxiao.properties.BootConstant;

/**
 * 验证码创建的三个过程
 * 1.创建
 * 2.存储
 * 3.发送
 * @author 100个nice
 * @date 2019/12/14
 */
@RestController
 public class ValidateCodeController {

    @Autowired
     private Map<String, ValidateCodeProcessor> validateCodeProcessors;
     
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpServletResponse response;
    
    @RequestMapping(BootConstant.CODE_GENERATOR_URL+"{type}")
    public void createValidateCode(@PathVariable String type) throws Exception {
        
        //从map集合中获得 ValidateCodeProcessor 的实现类 执行  形成验证码的方法
        validateCodeProcessors.get(type+"CodeProcessor").createValidateCode(new ServletWebRequest(request, response));
    }
}
