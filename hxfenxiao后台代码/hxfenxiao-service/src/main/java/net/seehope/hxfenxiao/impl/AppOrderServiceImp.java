package net.seehope.hxfenxiao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seehope.hxfenxiao.AppOrderService;
import net.seehope.hxfenxiao.mapper.CodeMapper;
import net.seehope.hxfenxiao.mapper.OrderMapper;
import net.seehope.hxfenxiao.pojo.Code;
import net.seehope.hxfenxiao.pojo.Order;
import net.seehope.hxfenxiao.pojo.Order2;
import net.seehope.hxfenxiao.properties.CodeProperties;
import net.seehope.hxfenxiao.properties.OrderStatusProperties;

@Service
public class AppOrderServiceImp implements AppOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public List<Order2> dealersQueryAllOrders(String id) {
        // TODO Auto-generated method stub
        return orderMapper.dealersQueryAllOrders(id);
    }

    @Override
    public Order2 dealersOneOrder(String orderid, String id) {
        // TODO Auto-generated method stub
        return orderMapper.dealersOneOrder(orderid, id);
    }

    /**
     * 下单
     */
    @Override
    public Integer dealersBuy(String pid, Integer pnum, String receiverid, String receiveraddress, String receivername,
        String receiverphone, String sendid) {
        Order order = new Order();
        // String pidStr = uuid.substring(0,5);
        order.setOrderid(UUID.randomUUID().toString().substring(0, 18));
        order.setPid(pid);
        order.setPnum(pnum);
        order.setReceiveraddress(receiveraddress);
        order.setReceiverid(receiverid);
        order.setReceivername(receivername);
        order.setReceiverphone(receiverphone);
        order.setSendid(sendid);

        return orderMapper.insertSelective(order);
    }

    /**
     * 发货
     */
    @Override
    public Integer dealersSendGoods(String orderid, String id, String packcode) {
        Order order = new Order();
        Code code = new Code();
        code.setDid(id);
        code.setPackcode(packcode);

        if (codeMapper.selectCount(code) > 0) {
            order.setPackcode(packcode);
            order.setDelivery(OrderStatusProperties.OBRDER_DELIVERY);
            order.setOrderid(orderid);
            order.setSendid(id);
            orderMapper.updateByPrimaryKeySelective(order);
            return codeMapper.updateByPackcode(packcode, CodeProperties.BELONG_TEMP);
        }

        return 0;
    }

    /**
     * 收货
     */
    @Override
    public Integer dealersGetGoods(String orderid, String id, String packcode) {
        Order order = new Order();
        order.setOrderid(orderid);
        order.setDelivery(OrderStatusProperties.OBRDER_OBTAIN);
        orderMapper.updateByPrimaryKeySelective(order);
        return codeMapper.updateByPackcode(packcode, id);
    }

    /**
     * 退货
     */
    @Override
    public Integer dealersReturnGoods(String orderid, String id) {
        Order order = new Order();
        order.setOrderid(orderid);
        order.setDelivery(OrderStatusProperties.OBRDER_RETURN_REQUEST);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 同意退货
     */
    @Override
    public Integer dealersAgreeReutrnGoods(String orderid, String id, String packcode) {
        Order order = new Order();
        order.setOrderid(orderid);
        order.setDelivery(OrderStatusProperties.OBRDER_RETURN_REQUEST_AGREE);
        orderMapper.updateByPrimaryKeySelective(order);
        // Code code = new Code();
        // code.setDid(id);
        // code.setPackcode(packcode);

        return codeMapper.updateByPackcode(packcode, id);
    }

}
