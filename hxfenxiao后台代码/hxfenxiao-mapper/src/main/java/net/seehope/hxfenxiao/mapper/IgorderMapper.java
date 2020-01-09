package net.seehope.hxfenxiao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.seehope.hxfenxiao.pojo.Igorder;
import net.seehope.hxfenxiao.pojo.Igorder2;

/**
 * 通用 Mapper 代码生成器
 *
 * @author mapper-generator
 */
public interface IgorderMapper extends tk.mybatis.mapper.common.Mapper<Igorder> {
    List<Igorder2> findIgOrderProductName();

    List<Igorder2> findAllOrders(@Param("id") String id);

    Igorder2 findOneOrder(@Param("id") String id);
}
