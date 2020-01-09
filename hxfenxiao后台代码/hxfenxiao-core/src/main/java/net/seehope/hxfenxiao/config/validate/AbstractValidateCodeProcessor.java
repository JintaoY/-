 package net.seehope.hxfenxiao.config.validate;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;
import net.seehope.hxfenxiao.properties.BootProperties;

@Slf4j
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private Map<String, ValidatecodeGenerator> validatecodeGenerators;
    
    @Autowired
    private ValidateCodeRepository validateCodeRepository;
    
    @Autowired
    private BootProperties bootProperties;
    
    @Override
    public void createValidateCode(ServletWebRequest webRequest) throws Exception {
        // TODO Auto-generated method stub
        
        C validateCode = generator(webRequest);
        log.info("generator");
        
        save(webRequest, validateCode);
        log.info("save");
        
        send(webRequest, validateCode);
        log.info("send");
        
        
    }
    
    
    @SuppressWarnings("unchecked")
    public C generator(ServletWebRequest webRequest) {
        
        Set<String> keySet = validatecodeGenerators.keySet();
        for (String key : keySet) {
            if(StringUtils.containsIgnoreCase(key, support())) {
                
                return (C)validatecodeGenerators.get(key).generatorValidatecode();
                
            }
        }
        return null;
    }
    
    
    public void save(ServletWebRequest webRequest,C validateCode) {
        
        //只保存验证码的 验证码和过期时间
        ValidateCode currentCode = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        
        validateCodeRepository.save(webRequest, currentCode, support());
    }
    
    /**
     * 抽象方法 给子类进行实现
     */
    protected abstract void send(ServletWebRequest webRequest, C validateCode) 
            throws IOException, ServletRequestBindingException;
    
    
    @Override
    public String support() {
        // TODO Auto-generated method stub
         return null;
    }

    @Override
    public void validate(ServletWebRequest request) throws JsonParseException, JsonMappingException, IOException {
        
        String validateCode = request.getRequest().getParameter(bootProperties.getValidateCodeProperties().getValidateCode_in_html()); 
        
        if (StringUtils.isBlank(validateCode)) {
            throw new ValidateCodeException("请填写验证码");
        }
        
        
        ValidateCode currentCode = validateCodeRepository.get(request, 
                bootProperties.getValidateCodeProperties().getSessionStrategy(), support());
        
        if(currentCode == null) {
            throw new ValidateCodeException("当前验证码不存在");
        }
        
        if (currentCode.isExpire()) {
            throw new ValidateCodeException("当前验证码已经过期");
        }
        
        if(!StringUtils.equalsIgnoreCase(validateCode, currentCode.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }
        
        validateCodeRepository.remove(request, bootProperties.getValidateCodeProperties().getSessionStrategy(), support());
         
    }

}
