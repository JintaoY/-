 package net.seehope.hxfenxiao.config.validate;

import java.io.IOException;

import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ValidateCodeProcessor {
     
     void createValidateCode(ServletWebRequest webRequest) throws Exception;
     
     /**
      * 当前处理器支持哪一种验证码生成，那么这个方法返回这个验证码的名字
      * @return
      */
     String support();
     
     /**
      * 处理验证码
      * @param request
      * @throws JsonParseException
      * @throws JsonMappingException
      * @throws IOException
      */
     public void validate(ServletWebRequest request) throws JsonParseException, JsonMappingException, IOException;
}
