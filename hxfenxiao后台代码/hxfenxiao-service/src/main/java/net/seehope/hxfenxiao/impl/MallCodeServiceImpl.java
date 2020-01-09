 package net.seehope.hxfenxiao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import net.seehope.hxfenxiao.MallCodeService;
import net.seehope.hxfenxiao.mapper.CodeMapper;
import net.seehope.hxfenxiao.mapper.ProductMapper;
import net.seehope.hxfenxiao.mapper.UserMapper;
import net.seehope.hxfenxiao.pojo.Code;
import net.seehope.hxfenxiao.pojo.Product;
import net.seehope.hxfenxiao.pojo.User;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
 public class MallCodeServiceImpl implements MallCodeService {
    
    @Autowired
    private CodeMapper codeMapper;
    
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据productCode 查询 Code信息
     * @param productCode
     * @return
     */
    @Override
    public Code findByProductCode(String productCode) {
        // TODO Auto-generated method stub
        
        Example example = new Example(Code.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productcode", productCode);
        
         return codeMapper.selectOneByExample(example);
    }
    
    
    /**
     * 获得积分
     */
    @Transactional
    @Override
    public void getIntegral(Code code, String userid) {
        //根据pid查询 获得的积分
        Integer productScore = findByPid(code.getPid());
        
        //修改code 的状态 为0  已经被领取
        updateCodeStatus(code);
        
        //增加用户的积分
        addUserIntegral(userid, productScore);
    }
    
    /**
             * 根据pid 查询 积分
     * @param pid
     * @return
     */
    @Override
    public Integer findByPid(Integer pid) {
        // TODO Auto-generated method stub
        
       Product product = productMapper.selectByPrimaryKey(pid);
       
       return product.getProductscore();
    }
    
    /**
     * 改变Code 的状态 1为未领取 0为已领取
     * @param code
     */
    @Override
    public void updateCodeStatus(Code code) {
        
        Example example = new Example(Code.class);
        Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("packcode", code.getPackcode());
        criteria.andEqualTo("productcode", code.getProductcode());
        criteria.andEqualTo("pid", code.getPid());
        
        code.setStatus(0);
        
        codeMapper.updateByExample(code, example);
        
    }

    /**
     * 根据用户的id和产品的积分 增加 用户的积分
     * @param userid
     * @param productScore
     */
    @Override
    public void addUserIntegral(String userid, Integer productScore) {
        // TODO Auto-generated method stub
         
        User user = userMapper.selectByPrimaryKey(userid);
        Integer currentproductScore = user.getUintegral()+productScore;
        
        user.setUintegral(currentproductScore);
        
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据用户的ID查询用户
     * @param userid
     * @return
     */
    @Override
    public User findByUserId(String userid) {
        // TODO Auto-generated method stub
         return userMapper.selectByPrimaryKey(userid);
    }
    
    
    

    
    
    
    
    

}
