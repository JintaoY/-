 package net.seehope.hxfenxiao.web.controller;




import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seehope.hxfenxiao.MallCodeService;
import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.pojo.Code;
import net.seehope.hxfenxiao.pojo.User;

/**
      *   用户获得积分 的处理
  * @author 100个nice
  * @date 2019/12/23
  */
 @RestController
 @RequestMapping("/mall/code")
 public class CodeController {
     
     @Autowired
     private MallCodeService mallCodeService;
     
    /*用户获得积分处理
     * 1、判断是否为正品
     * 2、判断是否为窜货
     * 3、判断是否已经被领取了积分
     * 4、领取积分 修改 状态 1为未领取 0为已经领取
     * */
     
     @PutMapping("/getIntegral")
     public JSONResult getIntegral(String productCode,String userid) {
         
         System.out.println("1"+productCode);
         System.out.println(userid);
         
         //判断是否为正品
         Code code = mallCodeService.findByProductCode(productCode);
         if(code == null) {
             return JSONResult.isErr(10012, "该产品为假货，请重新输入或者举报");
         }
         
         //判断是否为窜货，当这个产品的did不是第三级经销商为窜货
         String did = code.getDid();
         String[] split = StringUtils.split(did, '.');
         //因为三级经销的did 都会是xx.xx.xx 当以 . 进行切分时，长度为3
         if(split.length != 3) {
             
             return JSONResult.isErr(10013, "该产品为非法窜货，请重新输入或者举报");
         }
         
         //判断是否已经被领取了积分 状态 1为未领取 0为已经领取
         if(code.getStatus() != 1) {
             
             return JSONResult.isErr(10014, "该产品已经被获取积分啦，请重新输入");
         }
         
         //判断userid的用户是否存在
         User user = mallCodeService.findByUserId(userid);
         if(user == null) {
             return JSONResult.isErr(10015, "当前用户不存在，请重新输入");
         }
         
         //领取积分
         mallCodeService.getIntegral(code, userid);
         
         //领取积分成功，反正状态码200
         return JSONResult.isOk(null);
         
     }
     
}
