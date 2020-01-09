 package net.seehope.hxfenxiao;

import java.util.List;

import net.seehope.hxfenxiao.pojo.User;

public interface UserService {
     
     /**
      * 根据用户的电话号码查询存在的个数
      * @param uphoneNum
      * @return
      */
     public int findByuphoneNum(String uphoneNum);
     
     /**
      * 根据手机号码进行登录
      * @param uphoneNum
      * @return
      */
     public User login(String uphoneNum);
     
     /**
      * 根据手机号码进行用户注册
      * @param uphoneNum
      * @return
      */
     public User register(String uphoneNum);
     
     /**
      * 根据用户ID获得用户的信息
      * @param userid
      * @return
      */
     public User findByUserid(String userid);
     
     /**
      * 更新用户信息
      * @data 2019年12月24日 下午4:06:07
      * @author xxx
      * @param user
      */
     public void updateUser(User user);
     
     /**
      * 查询是否有相同用户名
      * @data 2019年12月24日 下午5:05:36
      * @author xxx
      * @param uusername
      * @return
      */
     public int findByUusername(String uusername,String userid);
}
