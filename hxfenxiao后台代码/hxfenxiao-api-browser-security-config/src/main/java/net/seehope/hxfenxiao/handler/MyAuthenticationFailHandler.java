 package net.seehope.hxfenxiao.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.seehope.hxfenxiao.properties.BootProperties;

@Component
@Slf4j
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler{
    
    public static final String RETURN_TYPE = "html"; // 登录失败时，用来判断是返回json数据还是跳转html页面
    
    @Autowired
    private ObjectMapper objectMapper;
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Autowired
    private BootProperties bootProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败:" + exception.getMessage());
        log.info("username=>" + request.getParameter("username"));
 
        if (RETURN_TYPE.equals("html")) {
            redirectStrategy.sendRedirect(request, response, bootProperties.getBrowerProperties().getLoginPage());
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("code","1002");
            map.put("msg","登录失败");
            map.put("data",exception.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(map));
        }
         
    }

}
