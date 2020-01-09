package net.seehope.hxfenxiao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.seehope.hxfenxiao.pojo.Order;
import net.seehope.hxfenxiao.pojo.Order2;

/**
 * 通用 Mapper 代码生成器
 *
 * @author mapper-generator
 */
public interface OrderMapper extends tk.mybatis.mapper.common.Mapper<Order> {
    Order2 getoneOrder(String orderid);

    List<Order2> getallOrders(@Param("sendid") String sendid);

    // 分销商
    List<Order2> dealersQueryAllOrders(String id);

    Order2 dealersOneOrder(@Param("orderid") String orderid, @Param("id") String id);

}
