 package net.seehope.hxfenxiao;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
 public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        //让安全框架对本模块无效
        /*super.configure(http);*/
        http.csrf().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
         //super.configure(auth);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
         //super.configure(web);
    }
}
