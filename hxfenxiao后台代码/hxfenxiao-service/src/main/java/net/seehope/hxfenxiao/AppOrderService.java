package net.seehope.hxfenxiao;

import java.util.List;

import net.seehope.hxfenxiao.pojo.Order2;

public interface AppOrderService {

    /**
     * 经销商查看自己所有的订单
     * 
     * @param id
     * @return
     */
    List<Order2> dealersQueryAllOrders(String id);

    /**
     * 经销商查看指定订单
     * 
     * @param orderid
     * @param id
     * @return
     */
    Order2 dealersOneOrder(String orderid, String id);

    /**
     * 经销商下单
     */
    Integer dealersBuy(String pid, Integer pnum, String receiverid, String receiveraddress, String receivername,
        String receiverphone, String sendid);

    /**
     * 经销商发货
     */
    Integer dealersSendGoods(String orderid, String id, String packcode);

    /**
     * 经销商收货
     */
    Integer dealersGetGoods(String orderid, String id, String packcode);

    /**
     * 经销商退货
     */
    Integer dealersReturnGoods(String orderid, String id);

    /**
     * 经销商同意退货
     * 
     */
    Integer dealersAgreeReutrnGoods(String orderid, String id, String packcode);
}
