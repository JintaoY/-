 package net.seehope.hxfenxiao.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.seehope.hxfenxiao.properties.BootProperties;

/**
 * 退出登陆 处理器
 * @author 100个nice
 * @date 2019/12/14
 */
@Component
@Slf4j
public class MyLogoutSuccessHandlerConfig implements LogoutSuccessHandler {

    @Autowired
    private BootProperties bootProperties;
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info(authentication.getPrincipal()+"触发了退出登陆处理器");
        
        if(StringUtils.isNotBlank(bootProperties.getBrowerProperties().getLogoutProcessingUrl())) {
            
            redirectStrategy.sendRedirect(request, response, bootProperties.getBrowerProperties().getLogoutPage());
        }
        else {
            
            response.setContentType("application/json;utf-8");
            response.getWriter().write("退出登陆成功");
        }
    }

}
