package net.seehope.hxfenxiao.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;

import net.seehope.hxfenxiao.AppOrderService;
import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.mapper.DistributorMapper;
import net.seehope.hxfenxiao.pojo.Order2;
import net.seehope.hxfenxiao.properties.CodeProperties;

@RestController
@RequestMapping("/app/order")
public class AppOrderController {
    @Autowired
    private AppOrderService appOrderService;
    @Autowired
    private HttpServletResponse res;
    @Autowired
    private DistributorMapper distributorMapper;

    public void turnback(String url) {
        res.setHeader("Content-Type", "text/html;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        try {
            res.getWriter().append("该包装码已经存在");
            res.sendRedirect("/hx/order-manage.html");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 查看所有订单
     * 
     * @param id
     * @return
     */
    @GetMapping("/allOrders")
    public List<Order2> dealersQueryAllOrders(String id) {
        // TODO Auto-generated method stub
        return appOrderService.dealersQueryAllOrders(id);
    }

    /**
     * 查看详细订单
     * 
     * @param orderid
     * @param id
     * @return
     */
    @GetMapping("/oneOrder")
    public Order2 dealersOneOrder(String orderid, String id) {
        // TODO Auto-generated method stub
        return dealersOneOrder(orderid, id);
    }

    /**
     * 下单
     */
    @PostMapping("/buy")
    public JSONResult dealersBuy(@Param("pid") String pid, @Param("pnum") Integer pnum,
        @Param("receiverid") String receiverid, @Param("receiveraddress") String receiveraddress,
        @Param("receivername") String receivername, @Param("receiverphone") String receiverphone,
        @Param("sendid") String sendid) {
        if (StringUtils.isEmpty(sendid)) {
            sendid = CodeProperties.BELONG;
        }
        // System.out.println(pid + pnum + receiverid + receiveraddress + receivername + receiverphone + sendid);
        appOrderService.dealersBuy(pid, pnum, receiverid, receiveraddress, receivername, receiverphone, sendid);
        return JSONResult.isOk("../order");
    }

    /**
     * 发货
     */
    @GetMapping("/send")
    public JSONResult dealersSendGoods(@Param("orderid") String orderid, @Param("id") String id,
        @Param("packcode") String packcode) {
        appOrderService.dealersSendGoods(orderid, id, packcode);
        return JSONResult.isOk("../order");
    }

    /**
     * 收货
     */
    @GetMapping("/get")
    public JSONResult dealersGetGoods(@Param("orderid") String orderid, @Param("id") String id,
        @Param("packcode") String packcode) {
        appOrderService.dealersGetGoods(orderid, id, packcode);
        return JSONResult.isOk("../order");
    }

    /**
     * 退货
     */
    @GetMapping("/return")
    public JSONResult dealersReturnGoods(@Param("orderid") String orderid, @Param("id") String id) {
        appOrderService.dealersReturnGoods(orderid, id);
        return JSONResult.isOk("../order");
    }

    /**
     * 同意退货
     */
    @GetMapping("/agreeReturn")
    public void dealersAgreeReturnGoods(@Param("orderid") String orderid, @Param("id") String id,
        @Param("packcode") String packcode) {
        appOrderService.dealersAgreeReutrnGoods(orderid, id, packcode);
    }

    @GetMapping("/myinfo")
    public JSONResult findMyInfo(@Param("did") String did) {
        return JSONResult.isOk(distributorMapper.selectByPrimaryKey(did));
    }
}
