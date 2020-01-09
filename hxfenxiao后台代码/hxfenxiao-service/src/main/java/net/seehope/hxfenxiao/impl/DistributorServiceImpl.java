 package net.seehope.hxfenxiao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.seehope.hxfenxiao.DistributorService;
import net.seehope.hxfenxiao.mapper.DistributorMapper;
import net.seehope.hxfenxiao.pojo.Distributor;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
 * @author 100个nice
 * @date 2019/12/11
 */
@Service
 public class DistributorServiceImpl implements DistributorService{
    
    @Autowired
    private DistributorMapper distributorMapper;

    /**
     * 添加一级经销商
     */
    @Override
    public void add1(Distributor distributor) {
        // TODO Auto-generated method stub
        
        
        distributorMapper.insert(distributor);
       
    }
    
    
    /**
     * 查询所有的经销商
     * @param did
     * @return
     */
    @Override
    public List<Distributor> findAll(Integer dlevel){
        
        Example example = new Example(Distributor.class);
        Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("dlevel", dlevel);
        
        List<Distributor> distributors = distributorMapper.selectByExample(example);
        
        return distributors;
    }

    /**
     * 分页查询所有的 经销商
     */
    @Override
    public List<Distributor> findAll(Integer dlevel, Integer page, Integer size) {
        // TODO Auto-generated method stub
        PageHelper.startPage(page, size);
        Example example = new Example(Distributor.class);
        Criteria criteria = example.createCriteria();   
        
        criteria.andEqualTo("dlevel", dlevel);
        
        List<Distributor> distributors = distributorMapper.selectByExample(example);
        
        return distributors;
    }

    /**
     * 停用账号
     */
    @Override
    public void closeStatus(String did) {
        
        /*Example example = new Example(Distributor.class);
        Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("did", did);*/
        
        Distributor distributor = distributorMapper.selectByPrimaryKey(did);
        //判断用户的状态，是启动状态还是关闭状态
        if(distributor.getDstatus() == 0) {
            distributor.setDstatus(1);
            
        }else if(distributor.getDstatus() == 1) {
            distributor.setDstatus(0);
        }
       
        
        distributorMapper.updateByPrimaryKey(distributor);
    }


    /**
     * 修改一级经销商的信息
     */
    @Override
    public void update1(Distributor distributor) {
        // TODO Auto-generated method stub
         
        /* distributorMapper.updateByPrimaryKey(distributor);*/
        //distributorMapper.updateInfo(distributor);
        distributorMapper.updateByPrimaryKeySelective(distributor);
        
    }

    /**
     * 经销商登陆 通过经销商ID和经销商手机号进行登陆
     * @param did
     * @param dphonenum
     * @return
     */
    @Override
    public Distributor appLogin(String did, String dphonenum) {
        // TODO Auto-generated method stub
        
        Example example = new Example(Distributor.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("did", did);
        criteria.andEqualTo("dphonenum", dphonenum);
        
        Distributor distributor = distributorMapper.selectOneByExample(example);
        
        return distributor;
    }

    /**
     * 根据did获取其下属经销商信息
     * @param did
     * @return
     */
    @Override
    public List<Distributor> getSubordinates(String did) {
        // TODO Auto-generated method stub

        Example example = new Example(Distributor.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("hid", did);
        
        List<Distributor> distributors = distributorMapper.selectByExample(example);
        
        return distributors;
    }


    /**
     * 根据did进行模糊查询
     * @param dlevel
     * @param did
     * @return
     */
    @Override
    public List<Distributor> findByDid(Integer dlevel, String did,Integer page, Integer size) {
        // TODO Auto-generated method stub
        
        PageHelper.startPage(page, size);
        Example example = new Example(Distributor.class);
        Criteria criteria = example.createCriteria();
        
        criteria.andEqualTo("dlevel", dlevel);
        criteria.andLike("did", did);
        
        List<Distributor> distributors = distributorMapper.selectByExample(example);
        
        return distributors;
    }
    
    /**
     * 查询经销商个人信息
     */
    @Override
    public Distributor findDistributorsData(String did) {
    	// TODO Auto-generated method stub
    	
    	Distributor distributor = distributorMapper.selectByPrimaryKey(did);
    	return distributor;
    }
    
    
    
}
