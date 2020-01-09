 package net.seehope.hxfenxiao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;

import net.seehope.hxfenxiao.mapper.AdminMapper;
import net.seehope.hxfenxiao.pojo.Admin;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Component
public class UsernamePasswordUserDetailServiceConfiguration implements UserDetailsService {

    
    @Autowired
    private AdminMapper adminMapper;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        System.out.println(username);
        
        Example example = new Example(Admin.class);
        Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("ausername", username);
        
        Admin admin = adminMapper.selectOneByExample(example);
        
         return new User(username, admin.getApassword(), true, true, true, true, 
             AuthorityUtils.commaSeparatedStringToAuthorityList("Role_Admin"));
    }
    

}
