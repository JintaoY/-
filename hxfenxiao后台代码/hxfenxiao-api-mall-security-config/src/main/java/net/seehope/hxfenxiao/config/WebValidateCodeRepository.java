 package net.seehope.hxfenxiao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import net.seehope.hxfenxiao.config.validate.ValidateCode;
import net.seehope.hxfenxiao.config.validate.ValidateCodeRepository;
import net.seehope.hxfenxiao.properties.BootProperties;

/**
 * Web 端的验证存储处理
 * @author 100个nice
 * @date 2019/12/14
 */
@Component
public class  WebValidateCodeRepository implements ValidateCodeRepository {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    
    @Autowired
    private BootProperties bootProperties;
    
    @Override
    public void save(ServletWebRequest request, ValidateCode validateCode, String support) {
        // TODO Auto-generated method stub
        
         sessionStrategy.setAttribute(request, bootProperties.getValidateCodeProperties().getSessionStrategy(), validateCode);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, String validateCode, String support) {
        // TODO Auto-generated method stub
         return (ValidateCode)sessionStrategy.getAttribute(request, bootProperties.getValidateCodeProperties().getSessionStrategy());
    }

    @Override
    public void remove(ServletWebRequest request, String validateCode, String support) {
        // TODO Auto-generated method stub
         
        sessionStrategy.removeAttribute(request, bootProperties.getValidateCodeProperties().getSessionStrategy());
    }

}
