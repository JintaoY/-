package net.seehope.hxfenxiao;

import java.util.List;
import java.util.Map;

import net.seehope.hxfenxiao.pojo.Order;
import net.seehope.hxfenxiao.pojo.Order2;

public interface OrderService {
    // 查看所有订单
    List<Order2> getAllOrders();

    // 查看指定订单
    Order getOneOrder(Map<String, Object> map);

    // 修改订单状态，生成产品
    void sendGoodsAndOrder(String orderid, String packcode, Integer pid, String receiverid);

}
