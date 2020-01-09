 package net.seehope.hxfenxiao.config.validate;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;



import lombok.extern.slf4j.Slf4j;
import net.seehope.hxfenxiao.properties.BootProperties;

@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {


     private Set<String> urls = new HashSet<String>();

     private AntPathMatcher matcher = new AntPathMatcher();

     //private AuthenticationFailureHandler authenticationFailureHandler;

     private BootProperties bootProperties;

     private Map<String, ValidateCodeProcessor> validateCodeProcessors;

     /**
      * 如何设计可以让每个用户在刚开始的时候不需要做验证码，当输错了3次以上才需要做验证码
      */
     @Override
     public void afterPropertiesSet() throws ServletException {
         // TODO Auto-generated method stub
         super.afterPropertiesSet();

         String[] urlConfig =
             StringUtils.splitByWholeSeparatorPreserveAllTokens(bootProperties.getValidateCodeProperties().getValidateUrls(), ",");

         for (String string : urlConfig) {

             urls.add(string);
         }

         urls.add(bootProperties.getBrowerProperties().getLoginProcessingUrl());
     }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
       FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (matcher.match(url, request.getRequestURI())) {
                action = true;
                
            }
        }

        if (action) {
            try {
                log.info(request.getRequestURI());

                for (String key : validateCodeProcessors.keySet()) {
                    
                    validateCodeProcessors.get(key).validate(new ServletWebRequest(request, response));
                    
                    break;
                }

                //validate(new ServletWebRequest(request, response));
            } catch (ValidateCodeException e) {
                // TODO: handle exception
                // authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                System.out.println(e);
                response.sendRedirect(bootProperties.getBrowerProperties().getLoginPage());
                return;
            }
        }

        filterChain.doFilter(request, response);
         
    }
    
    
        public BootProperties getBootProperties() {
            return bootProperties;
        }
    
        public void setBootProperties(BootProperties bootProperties) {
            this.bootProperties = bootProperties;
        }
    
       
        public Set<String> getUrls() {
            return urls;
        }
    
        public void setUrls(Set<String> urls) {
            this.urls = urls;
        }
    
        public AntPathMatcher getMatcher() {
            return matcher;
        }
    
        public void setMatcher(AntPathMatcher matcher) {
            this.matcher = matcher;
        }
    
        public Map<String, ValidateCodeProcessor> getValidateCodeProcessors() {
            return validateCodeProcessors;
        }
    
        public void setValidateCodeProcessors(Map<String, ValidateCodeProcessor> validateCodeProcessors) {
            this.validateCodeProcessors = validateCodeProcessors;
        }

 }
