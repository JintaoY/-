 package net.seehope.hxfenxiao;

import java.util.List;

import net.seehope.hxfenxiao.pojo.Distributor;

/**
  * 经销商管理业务层
  * @author 100个nice
  * @date 2019/12/11
  */
 public interface DistributorService {
    
     /**
      * 添加一级经销商
      */
    public void add1(Distributor distributor); 
    
    /**
     * 查询所有的经销商
     * @param dlevel
     * @return
     */
    public List<Distributor> findAll(Integer dlevel);
    
    
    /**
     * 分页查询所有的经销商
     * @param dlevel
     * @return
     */
    public List<Distributor> findAll(Integer dlevel,Integer page,Integer size);
    
    /**
     * 关闭账号状态
     * @param did
     */
    public void closeStatus(String did);
    
    /**
     *  修改 一级经销商的信息
     * @param distributor
     */
    public void update1(Distributor distributor);
    
    
    /**
     * 经销商登陆 通过经销商ID和经销商手机号进行登陆
     * @param did
     * @param dphonenum
     * @return
     */
    public Distributor appLogin(String did,String dphonenum);
    
    /**
     * 根据did获取其下属经销商信息
     * @param did
     * @return
     */
    public List<Distributor> getSubordinates(String did);

    
    
    /**
     * 根据did进行模糊查询
     * @param dlevel
     * @param did
     * @param size 
     * @param page 
     * @return
     */
    public List<Distributor> findByDid(Integer dlevel,String did, Integer page, Integer size);

    
    /**
     * 查询经销商个人信息
     * @data 2019年12月18日 下午7:44:46
     * @author xxx
     * @param did
     * @return
     */
    public Distributor findDistributorsData(String did);
}
