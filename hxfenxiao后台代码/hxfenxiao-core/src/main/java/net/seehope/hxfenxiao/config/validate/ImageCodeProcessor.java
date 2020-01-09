 package net.seehope.hxfenxiao.config.validate;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;



@Component(value = "imageCodeProcessor")
 public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode> {

    @Override
    protected void send(ServletWebRequest webRequest, ImageValidateCode validateCode)
        throws IOException, ServletRequestBindingException {
        // TODO Auto-generated method stub
        
        ImageIO.write(validateCode.getImage(), "JPEG", webRequest.getResponse().getOutputStream());
    }
    
    /**
     * 方法重写 当new 子类对象时  父类的方法也会被子类覆盖
     */
    @Override
    public String support() {
        // TODO Auto-generated method stub
         return "image";
    }
    
    
}
