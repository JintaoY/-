package net.seehope.hxfenxiao.mapper;

import net.seehope.hxfenxiao.pojo.Distributor;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface DistributorMapper extends tk.mybatis.mapper.common.Mapper<Distributor> {
    
    /**
             * 修改经销商信息
     * @param distributor
     */
    public void updateInfo(Distributor distributor);
    
    
}




