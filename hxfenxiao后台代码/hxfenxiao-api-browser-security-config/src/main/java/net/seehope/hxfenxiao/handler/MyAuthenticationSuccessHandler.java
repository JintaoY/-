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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.seehope.hxfenxiao.common.JSONResult;

@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public static final String RETURN_TYPE = "html"; // 登录成功时，用来判断是返回json数据还是跳转html页面
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        
        log.info("登录成功");
        
        if (StringUtils.equals(RETURN_TYPE, "html")) {
            redirectStrategy.sendRedirect(request, response, "/hx/index.html");
            
        }else {
            
           JSONResult jSONResult = JSONResult.isOk(authentication);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(jSONResult));
        }
    }
    
    
}
