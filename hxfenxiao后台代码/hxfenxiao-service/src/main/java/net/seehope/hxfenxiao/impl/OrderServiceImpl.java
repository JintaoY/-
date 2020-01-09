package net.seehope.hxfenxiao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seehope.hxfenxiao.OrderService;
import net.seehope.hxfenxiao.mapper.CodeMapper;
import net.seehope.hxfenxiao.mapper.OrderMapper;
import net.seehope.hxfenxiao.mapper.ProductMapper;
import net.seehope.hxfenxiao.pojo.Code;
import net.seehope.hxfenxiao.pojo.Order;
import net.seehope.hxfenxiao.pojo.Order2;
import net.seehope.hxfenxiao.properties.CodeProperties;
import net.seehope.hxfenxiao.properties.OrderStatusProperties;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CodeMapper codeMapper;
    @Autowired
    private HttpServletResponse response;

    private CodeProperties codeProperties;
    private OrderStatusProperties orderStatusProperties;
    private Order order = new Order();
    private Code code = new Code();

    @Override
    public List<Order2> getAllOrders() {
        List<Order2> list = orderMapper.getallOrders(codeProperties.BELONG);
        return list;
    }

    @Override
    public Order2 getOneOrder(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return orderMapper.getoneOrder((String)params.get("id"));
    }

    @Override
    public void sendGoodsAndOrder(String orderid, String packcode, Integer pid, String receiverid) {
        order.setPackcode(packcode);
        if (orderMapper.selectCount(order) == 0) {
            order.setOrderid(orderid);
            order.setDelivery(orderStatusProperties.OBRDER_DELIVERY);
            order.setSendid(codeProperties.BELONG);
            orderMapper.updateByPrimaryKeySelective(order);

            List<Code> list = new ArrayList<Code>();

            for (int i = 1; i < 41; i++) {
                Code code1 = new Code();
                code1.setDid(codeProperties.BELONG);
                code1.setPackcode(packcode);
                code1.setPid(pid);
                code1.setProductcode(packcode + "_" + i);

                list.add(code1);
            }
            codeMapper.insertIntoCodes(list);
            try {
                response.sendRedirect("/hx/order-manage.html");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                response.setHeader("Content-Type", "text/html;charset=UTF-8");
                // 设置response的缓冲区的编码.
                response.setCharacterEncoding("UTF-8");
                response.getWriter().append("该包装码已经存在");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
