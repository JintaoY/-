 package net.seehope.hxfenxiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

/**
  * App端启动类
  * @author 100个nice
  * @date 2019/12/16
  */
 @MapperScan("net.seehope.hxfenxiao.mapper")
 @SpringBootApplication
 public class AppApplication {
     
     public static void main(String[] args) throws Exception {
         SpringApplication.run(AppApplication.class, args);
     }
}
