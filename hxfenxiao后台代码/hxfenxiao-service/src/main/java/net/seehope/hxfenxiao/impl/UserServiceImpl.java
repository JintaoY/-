 package net.seehope.hxfenxiao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seehope.hxfenxiao.UserService;
import net.seehope.hxfenxiao.mapper.UserMapper;
import net.seehope.hxfenxiao.pojo.User;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    /**
     * 根据用户的电话号码查询存在的个数
     * @param uphoneNum
     * @return
     */
    @Override
    public int findByuphoneNum(String uphoneNum) {
        // TODO Auto-generated method stub
        
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uphonenum", uphoneNum);
        
        int count = userMapper.selectCountByExample(example);
        
        return count;
    }

    /**
     * 根据手机号码进行登录
     * @param uphoneNum
     * @return
     */
    @Override
    public User login(String uphoneNum) {
        // TODO Auto-generated method stub
        
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uphonenum", uphoneNum);
        
        User user = userMapper.selectOneByExample(example);
        
        return user;
    }

    /**
     * 根据手机号码进行用户注册
     * @param uphoneNum
     * @return
     */
    @Override
    public User register(String uphoneNum) {
        // TODO Auto-generated method stub
        
        //UUID生成用户的ID 和 设置手机号码  
        User user = new User();
        user.setUserid(String.valueOf(UUID.randomUUID()));
        user.setUphonenum(uphoneNum);
                
        userMapper.insert(user);
        
        
        
        return user;
    }

    /**
     * 根据用户ID获得用户的信息
     * @param userid
     * @return
     */
    @Override
    public User findByUserid(String userid) {
        // TODO Auto-generated method stub
        User user = userMapper.selectByPrimaryKey(userid);
        
         return user;
    }

    /**
     * 更新用户信息
     */
    @Override
    public void updateUser(User user) {
    	// TODO Auto-generated method stub
    	userMapper.updateByPrimaryKeySelective(user);
    }
    
    /**
     * 相同用户名
     */
    @Override
    public int findByUusername(String uusername,String userid) {
    	// TODO Auto-generated method stub
    	Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uusername", uusername);
        List<User> uList = userMapper.selectByExample(example);
        int count = 0;
        for (User user : uList) {
			if(!user.getUserid().equals(userid)) {
				count++;
			}
		}
        System.out.println("count"+count);
        return count;
    }
}
