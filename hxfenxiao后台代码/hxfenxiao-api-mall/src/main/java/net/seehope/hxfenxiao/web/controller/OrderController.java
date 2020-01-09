package net.seehope.hxfenxiao.web.controller;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.mapper.IgorderMapper;
import net.seehope.hxfenxiao.mapper.ProductMapper;
import net.seehope.hxfenxiao.mapper.UserMapper;
import net.seehope.hxfenxiao.pojo.Igorder;
import net.seehope.hxfenxiao.pojo.Igorder2;
import net.seehope.hxfenxiao.pojo.Product;
import net.seehope.hxfenxiao.pojo.User;
import net.seehope.hxfenxiao.properties.OrderStatusProperties;

@RestController
@RequestMapping("/mall/order")
public class OrderController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IgorderMapper igorderMapper;

    @GetMapping("/myinfo")
    private JSONResult myinfo(@Param("userid") String userid) {
        // TODO Auto-generated method stub
        return JSONResult.isOk(userMapper.selectByPrimaryKey(userid));
    }

    @PostMapping("/buy")
    public JSONResult dealersBuy(@Param("pid") String pid, @Param("pnum") Integer pnum, @Param("userid") String userid,
        @Param("uaddress") String uaddress, @Param("uusername") String uusername,
        @Param("uphonenum") String uphonenum) {
        User user = userMapper.selectByPrimaryKey(userid);
        Product product = productMapper.selectByPrimaryKey(pid);
        Integer allNeed = product.getProductchangescore() * pnum;
        if (allNeed <= user.getUintegral()) {
            user.setUintegral(user.getUintegral() - allNeed);
            userMapper.updateByPrimaryKeySelective(user);
            Igorder igorder = new Igorder();
            igorder.setDelivery(OrderStatusProperties.OBRDER_REQUEST);
            String orderid = (String)UUID.randomUUID().toString().subSequence(0, 20);
            igorder.setIgorderid(orderid);
            igorder.setPid(pid);
            igorder.setPnum(pnum);
            igorder.setReceiveraddress(uaddress);
            igorder.setReceiverid(userid);
            igorder.setReceivername(uusername);
            igorder.setReceiverphone(uphonenum);
            igorderMapper.insert(igorder);
            return JSONResult.isOk(200);
        } else {
            return JSONResult.isOk(400);
        }
    }

    @GetMapping("/allOrders")
    private List<Igorder2> allOrders(@Param("id") String id) {
        // TODO Auto-generated method stub
        return igorderMapper.findAllOrders(id);
    }

    @GetMapping("/oneOrder")
    private Igorder2 oneOrder(@Param("id") String id) {
        // TODO Auto-generated method stub
        return igorderMapper.findOneOrder(id);
    }

    @GetMapping("/toGet")
    private void GetGood(@Param("igorderid") String igorderid, @Param("id") String id) {
        Igorder igorder = new Igorder();
        igorder.setIgorderid(igorderid);
        igorder.setDelivery(OrderStatusProperties.OBRDER_OBTAIN);
        igorderMapper.updateByPrimaryKeySelective(igorder);
        // TODO Auto-generated method stub

    }
}
