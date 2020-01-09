 package net.seehope.hxfenxiao.properties;

 public class ValidateCodeProperties {
     
     private int expireIn = 60 * 15;

     private int length = 6;
     
     private String sessionStrategy = "CODE_IN_SESSION";
     
     /**
      * 验证码在前端中的字段名 
      */
     private String validateCode_in_html= "validateCode";
     
     /**
      * 验证码需要验证的路径，如果有多个路径，中间以，隔开
      * @param height
      */
     private String validateUrls = "/authentication/form,/authentication/mobile";
     
     private ImageCodeProperties imageCodeProperties = new ImageCodeProperties();
     

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getValidateUrls() {
        return validateUrls;
    }

    public void setValidateUrls(String validateUrls) {
        this.validateUrls = validateUrls;
    }

    public ImageCodeProperties getImageCodeProperties() {
        return imageCodeProperties;
    }

    public void setImageCodeProperties(ImageCodeProperties imageCodeProperties) {
        this.imageCodeProperties = imageCodeProperties;
    }

    public String getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(String sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public String getValidateCode_in_html() {
        return validateCode_in_html;
    }

    public void setValidateCode_in_html(String validateCode_in_html) {
        this.validateCode_in_html = validateCode_in_html;
    }
     
     
}
