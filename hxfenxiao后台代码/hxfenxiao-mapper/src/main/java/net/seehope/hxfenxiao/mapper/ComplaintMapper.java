package net.seehope.hxfenxiao.mapper;

import java.util.List;

import net.seehope.hxfenxiao.pojo.Complaint;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface ComplaintMapper extends tk.mybatis.mapper.common.Mapper<Complaint> {
   public List<Complaint> queryAllComplaintAndUserAndDistributor();
}




