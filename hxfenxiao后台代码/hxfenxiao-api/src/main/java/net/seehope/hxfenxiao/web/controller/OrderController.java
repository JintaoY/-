package net.seehope.hxfenxiao.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;

import net.seehope.hxfenxiao.OrderService;
import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.mapper.CodeMapper;
import net.seehope.hxfenxiao.mapper.OrderMapper;
import net.seehope.hxfenxiao.pojo.Code;
import net.seehope.hxfenxiao.pojo.Order2;

@RestController
@RequestMapping("/hx/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CodeMapper codeMapper;
    @Autowired
    private OrderMapper orderMapper;

    // 查找所有订单
    @GetMapping("/allOrders")
    public List<Order2> allOrders() {
        return orderService.getAllOrders();
    }

    // 查看指定订单
    @GetMapping("/oneOrder")
    public JSONResult oneOrder(@RequestParam Map<String, Object> params) {
        return JSONResult.isOk(orderService.getOneOrder(params));
    }

    
    
    // 根据输入大码生成商品码并修改订单状态，对每件商品添加所有权
    @PostMapping("/changeOrder")
    public void sendGoods(@RequestParam("orderid") String orderid, @RequestParam("packcode") String packcode,
        @RequestParam("pid") Integer pid, @RequestParam("receiverid") String receiverid,
        @RequestParam("delivery") String delivery) {
        System.out.println(delivery);
        System.out.println(packcode);
        if (StringUtils.equals(delivery, "3")) {
            orderMapper.deleteByPrimaryKey(orderid);
            Code code = new Code();
            code.setPackcode(packcode);
            codeMapper.delete(code);
        } else {
            orderService.sendGoodsAndOrder(orderid, packcode, pid, receiverid);
        }
    }

}
