 package net.seehope.hxfenxiao;

import net.seehope.hxfenxiao.pojo.Code;
import net.seehope.hxfenxiao.pojo.User;

public interface MallCodeService {
     
    /**
     * 根据productCode 查询 Code信息
     * @param productCode
     * @return
     */
     public Code findByProductCode(String productCode);
     
     /**
              * 获得积分
      * @param productCode
      * @param userid
      * @return
      */
     public void getIntegral(Code code,String userid);
     
     
     /**
      * 根据pid 查询 积分
      * @param pid
      * @return
      */
     public Integer findByPid(Integer pid);
     
     /**
      * 改变Code 的状态 1为未领取 0为已领取
      * @param code
      */
     public void updateCodeStatus(Code code);
     
     /**
      * 根据用户的id和产品的积分 增加 用户的积分
      * @param userid
      * @param productScore
      */
     public void addUserIntegral(String userid,Integer productScore);
     
     /**
      * 根据用户的ID查询用户
      * @param userid
      * @return
      */
     public User findByUserId(String userid);

     
}
