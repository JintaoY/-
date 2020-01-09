 package net.seehope.hxfenxiao.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 可配置类
 * @author 100个nice
 * @date 2019/12/14
 */
@Configuration
@ConfigurationProperties(prefix = "net.seehope")
 public class BootProperties {
    
    private BrowerProperties browerProperties = new BrowerProperties();
    
    
    private ValidateCodeProperties validateCodeProperties = new ValidateCodeProperties();
    
    

    public BrowerProperties getBrowerProperties() {
        return browerProperties;
    }
    
    

    public void setBrowerProperties(BrowerProperties browerProperties) {
        this.browerProperties = browerProperties;
    }

    public ValidateCodeProperties getValidateCodeProperties() {
        return validateCodeProperties;
    }

    public void setValidateCodeProperties(ValidateCodeProperties validateCodeProperties) {
        this.validateCodeProperties = validateCodeProperties;
    }
    
    
    
}
