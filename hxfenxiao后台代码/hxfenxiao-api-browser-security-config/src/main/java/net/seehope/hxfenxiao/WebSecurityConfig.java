package net.seehope.hxfenxiao;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import net.seehope.hxfenxiao.config.UsernamePasswordUserDetailServiceConfiguration;
import net.seehope.hxfenxiao.config.validate.ValidateCodeFilter;
import net.seehope.hxfenxiao.config.validate.ValidateCodeProcessor;
import net.seehope.hxfenxiao.handler.MyAuthenticationFailHandler;
import net.seehope.hxfenxiao.handler.MyAuthenticationSuccessHandler;
import net.seehope.hxfenxiao.handler.MyLogoutSuccessHandlerConfig;
import net.seehope.hxfenxiao.properties.BootConstant;
import net.seehope.hxfenxiao.properties.BootProperties;

/**
 * 自定义Security策略
 * 
 * @author 100个nice
 * @date 2019/12/13
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private DataSource dataSource;
    
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        //jdbc的数据库
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        //tokenRepositoryImpl.setCreateTableOnStartup(true);
        return tokenRepositoryImpl;
    }
    
    @Autowired
    private BootProperties bootProperties;
    
    @Autowired
    private UserDetailsService usernamePasswordUserDetailServiceConfiguration;
    
    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    private MyAuthenticationFailHandler authenticationFailHandler;
    
    @Autowired
    private MyLogoutSuccessHandlerConfig logoutSuccessHandlerConfig;
    
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setValidateCodeProcessors(validateCodeProcessors);
        validateCodeFilter.setBootProperties(bootProperties);
        validateCodeFilter.afterPropertiesSet();
        
        
        // TODO Auto-generated method stub
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()
            .loginPage(BootConstant.BROWSER_LOGIN_CONTROLLER_MAPPING)
            .loginProcessingUrl(bootProperties.getBrowerProperties().getLoginProcessingUrl())
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailHandler)
            
        .and().logout()//退出登陆相关配置
              .logoutUrl(bootProperties.getBrowerProperties().getLogoutProcessingUrl())
              .logoutSuccessHandler(logoutSuccessHandlerConfig)
              .deleteCookies("JSESSIONID")
        
        .and().rememberMe()//接下来就是记住我的配置
              .tokenRepository(persistentTokenRepository())//使用jdbc数据源存储登录状态
              .tokenValiditySeconds(60*60*3)//token的有限期
              .userDetailsService(usernamePasswordUserDetailServiceConfiguration)
              
        .and().sessionManagement()//对登陆的状态进行控制  使用redis存储 并且下一个会顶替上一个人登陆  
              .maximumSessions(1)//表示session最大的并发量为1，同时允许当前用户几个会话
              .maxSessionsPreventsLogin(false)//// false 下一个顶替上一个  true 下一个不会顶替上一个
        .and()   
        .and()
        .authorizeRequests()
        .antMatchers(BootConstant.BROWSER_LOGIN_CONTROLLER_MAPPING,bootProperties.getBrowerProperties().getLoginPage(),
                bootProperties.getBrowerProperties().getLoginProcessingUrl(),bootProperties.getBrowerProperties().getPermitLib(),
                bootProperties.getBrowerProperties().getPermitStatic(),bootProperties.getBrowerProperties().getPermitTemp(),
                bootProperties.getBrowerProperties().getPermitImages(),BootConstant.CODE_GENERATOR_URL+"**")
        .permitAll()
        .anyRequest().authenticated();
         
         http.csrf().disable();
         
       //spring security SAMEORIGIN表示该页面可以在相同域名页面的 frame 中展示。
         http.headers().frameOptions().sameOrigin();
    }

}
