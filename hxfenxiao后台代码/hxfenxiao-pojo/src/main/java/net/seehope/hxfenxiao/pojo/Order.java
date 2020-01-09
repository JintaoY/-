package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`orders`")
public class Order implements Serializable {
    /**
     * 订单号
     */
    @Id
    @Column(name = "`orderid`")
    private String orderid;

    /**
     * 购买产品id
     */
    @Column(name = "`pid`")
    private String pid;

    /**
     * 购买数量
     */
    @Column(name = "`pnum`")
    private Integer pnum;

    /**
     * 收货人
     */
    @Column(name = "`receivername`")
    private String receivername;

    /**
     * 收货地址
     */
    @Column(name = "`receiveraddress`")
    private String receiveraddress;

    /**
     * 联系电话
     */
    @Column(name = "`receiverphone`")
    private String receiverphone;

    /**
     * 下单经销商ID
     */
    @Column(name = "`receiverid`")
    private String receiverid;

    /**
     * 状态订单
     */
    @Column(name = "`delivery`")
    private Integer delivery;

    /**
     * 发货经销商ID
     */
    @Column(name = "`sendid`")
    private String sendid;

    /**
     * 下单时间
     */
    @Column(name = "`orederdate`")
    private Date orederdate;

    /**
     * 包装码
     */
    @Column(name = "`packcode`")
    private String packcode;

    public String getPackcode() {
        return packcode;
    }

    public void setPackcode(String packcode) {
        this.packcode = packcode;
    }

    private static final long serialVersionUID = 1L;

    /**
     * 获取订单号
     *
     * @return orderid - 订单号
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * 设置订单号
     *
     * @param orderid
     *            订单号
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * 获取购买产品id
     *
     * @return pid - 购买产品id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置购买产品id
     *
     * @param pid
     *            购买产品id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取购买数量
     *
     * @return pnum - 购买数量
     */
    public Integer getPnum() {
        return pnum;
    }

    /**
     * 设置购买数量
     *
     * @param pnum
     *            购买数量
     */
    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    /**
     * 获取收货人
     *
     * @return receivername - 收货人
     */
    public String getReceivername() {
        return receivername;
    }

    /**
     * 设置收货人
     *
     * @param receivername
     *            收货人
     */
    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    /**
     * 获取收货地址
     *
     * @return receiveraddress - 收货地址
     */
    public String getReceiveraddress() {
        return receiveraddress;
    }

    /**
     * 设置收货地址
     *
     * @param receiveraddress
     *            收货地址
     */
    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress = receiveraddress;
    }

    /**
     * 获取联系电话
     *
     * @return receiverphone - 联系电话
     */
    public String getReceiverphone() {
        return receiverphone;
    }

    /**
     * 设置联系电话
     *
     * @param receiverphone
     *            联系电话
     */
    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone;
    }

    /**
     * 获取下单经销商ID
     *
     * @return receiverid - 下单经销商ID
     */
    public String getReceiverid() {
        return receiverid;
    }

    /**
     * 设置下单经销商ID
     *
     * @param receiverid
     *            下单经销商ID
     */
    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    /**
     * 获取状态订单
     *
     * @return delivery - 状态订单
     */
    public Integer getDelivery() {
        return delivery;
    }

    /**
     * 设置状态订单
     *
     * @param delivery
     *            状态订单
     */
    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    /**
     * 获取发货经销商ID
     *
     * @return sendid - 发货经销商ID
     */
    public String getSendid() {
        return sendid;
    }

    /**
     * 设置发货经销商ID
     *
     * @param sendid
     *            发货经销商ID
     */
    public void setSendid(String sendid) {
        this.sendid = sendid;
    }

    /**
     * 获取下单时间
     *
     * @return orederdate - 下单时间
     */
    public Date getOrederdate() {
        return orederdate;
    }

    /**
     * 设置下单时间
     *
     * @param orederdate
     *            下单时间
     */
    public void setOrederdate(Date orederdate) {
        this.orederdate = orederdate;
    }
}