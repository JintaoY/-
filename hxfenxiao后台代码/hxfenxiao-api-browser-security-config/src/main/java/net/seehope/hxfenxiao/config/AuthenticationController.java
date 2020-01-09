 package net.seehope.hxfenxiao.config;

 
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.properties.BootConstant;
import net.seehope.hxfenxiao.properties.BootProperties;

/**
 * 验证控制器  返回登录页面
 * @author 100个nice
 * @date 2019/12/14
 */
@RestController
 public class AuthenticationController {
    
    //我们请求的缓冲 比如 登陆页面的html
    private RequestCache requstCache = new HttpSessionRequestCache();
    
    //重定向的工具
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Autowired
    private BootProperties bootProperties;
    
    
    @RequestMapping(BootConstant.BROWSER_LOGIN_CONTROLLER_MAPPING)
    public JSONResult requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
        
        //获得请求的路径
        SavedRequest saveRequest = requstCache.getRequest(request, response);
        
        
        if(saveRequest != null) {
            
            redirectStrategy.sendRedirect(request, response, bootProperties.getBrowerProperties().getLoginPage());
        }
        return JSONResult.isErr(400, "请求的url不能为NULL");
    }
        
}
