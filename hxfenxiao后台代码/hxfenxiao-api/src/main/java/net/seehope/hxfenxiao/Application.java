package net.seehope.hxfenxiao;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * WEB端启动类
 * 
 * @author 100个nice
 * @date 2019/12/12
 */
@ComponentScan(basePackages = {"net.seehope"})
@MapperScan("net.seehope.hxfenxiao.mapper")
@SpringBootApplication
public class Application {
    
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        
     
    }
    
    
    
}
