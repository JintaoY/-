 package net.seehope.hxfenxiao.config.validate;

import java.awt.image.BufferedImage;

/**
 * 图片验证码类
 * @author 100个nice
 * @date 2019/12/14
 */
public class ImageValidateCode extends ValidateCode {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 图片验证码的图片
     */
    private BufferedImage image;
    
    
    public ImageValidateCode(String code, BufferedImage image, Integer expireIn) {
        super(code, expireIn);
        this.image = image;
    }
    
    


    public ImageValidateCode() {
        super();
    }


    public BufferedImage getImage() {
        return image;
    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    

}
