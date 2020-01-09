 package net.seehope.hxfenxiao.properties;

 /**
  * 浏览器登录的配置
  * @author 100个nice
  * @date 2019/12/14
  */
 public class BrowerProperties {
     
     /**
      * 自定义登录页面的路径
      */
     private String loginPage = "/hx/login.html";
     
     /**
      * 退出登陆返回的页面
      */
     private String logoutPage = "/hx/login.html";
     
     /**
      * 登录请求提交的地址
      */
     private String loginProcessingUrl = "/authentication/form";
     
     /**
      * 退出登陆请求的地址
      */
     private String logoutProcessingUrl = "/logout";

     /**
      * 允许访问lib包
      */
     private String permitLib = "/hx/lib/**";
     
     private String permitStatic = "/hx/static/**";
     
     private String permitTemp = "/hx/temp/**";
     
     private String permitImages = "/hx/images/**";
     
     
     
    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getPermitLib() {
        return permitLib;
    }

    public void setPermitLib(String permitLib) {
        this.permitLib = permitLib;
    }

    public String getPermitStatic() {
        return permitStatic;
    }

    public void setPermitStatic(String permitStatic) {
        this.permitStatic = permitStatic;
    }

    public String getPermitTemp() {
        return permitTemp;
    }

    public void setPermitTemp(String permitTemp) {
        this.permitTemp = permitTemp;
    }

    public String getLogoutPage() {
        return logoutPage;
    }

    public void setLogoutPage(String logoutPage) {
        this.logoutPage = logoutPage;
    }

    public String getLogoutProcessingUrl() {
        return logoutProcessingUrl;
    }

    public void setLogoutProcessingUrl(String logoutProcessingUrl) {
        this.logoutProcessingUrl = logoutProcessingUrl;
    }

	public String getPermitImages() {
		return permitImages;
	}

	public void setPermitImages(String permitImages) {
		this.permitImages = permitImages;
	}
    
    
    
     
     
     
}
