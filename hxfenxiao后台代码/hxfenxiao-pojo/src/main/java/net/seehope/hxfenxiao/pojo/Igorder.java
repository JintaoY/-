package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`igorder`")
public class Igorder implements Serializable {
    /**
     * igorderid 积分订单ID
     */
    @Id
    @Column(name = "`igorderid`")
    private String igorderid;

    /**
     * pid 产品ID
     */
    @Column(name = "`pid`")
    private String pid;

    /**
     * pnum 购买数量
     */
    @Column(name = "`pnum`")
    private Integer pnum;

    /**
     * receivername 收货人
     */
    @Column(name = "`receivername`")
    private String receivername;

    /**
     * receiveraddress 收货人地址
     */
    @Column(name = "`receiveraddress`")
    private String receiveraddress;

    /**
     * receiverphone 联系电话
     */
    @Column(name = "`receiverphone`")
    private String receiverphone;

    /**
     * receiverid 下单用户id
     */
    @Column(name = "`receiverid`")
    private String receiverid;

    /**
     * delivery 订单状态0为未发货，1为已发货，2为已收货
     */
    @Column(name = "`delivery`")
    private Integer delivery;

    private static final long serialVersionUID = 1L;

    /**
     * 获取igorderid 积分订单ID
     *
     * @return igorderid - igorderid 积分订单ID
     */
    public String getIgorderid() {
        return igorderid;
    }

    /**
     * 设置igorderid 积分订单ID
     *
     * @param igorderid igorderid 积分订单ID
     */
    public void setIgorderid(String igorderid) {
        this.igorderid = igorderid;
    }

    /**
     * 获取pid 产品ID
     *
     * @return pid - pid 产品ID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置pid 产品ID
     *
     * @param pid pid 产品ID
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取pnum 购买数量
     *
     * @return pnum - pnum 购买数量
     */
    public Integer getPnum() {
        return pnum;
    }

    /**
     * 设置pnum 购买数量
     *
     * @param pnum pnum 购买数量
     */
    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    /**
     * 获取receivername 收货人
     *
     * @return receivername - receivername 收货人
     */
    public String getReceivername() {
        return receivername;
    }

    /**
     * 设置receivername 收货人
     *
     * @param receivername receivername 收货人
     */
    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    /**
     * 获取receiveraddress 收货人地址
     *
     * @return receiveraddress - receiveraddress 收货人地址
     */
    public String getReceiveraddress() {
        return receiveraddress;
    }

    /**
     * 设置receiveraddress 收货人地址
     *
     * @param receiveraddress receiveraddress 收货人地址
     */
    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress = receiveraddress;
    }

    /**
     * 获取receiverphone 联系电话
     *
     * @return receiverphone - receiverphone 联系电话
     */
    public String getReceiverphone() {
        return receiverphone;
    }

    /**
     * 设置receiverphone 联系电话
     *
     * @param receiverphone receiverphone 联系电话
     */
    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone;
    }

    /**
     * 获取receiverid 下单用户id
     *
     * @return receiverid - receiverid 下单用户id
     */
    public String getReceiverid() {
        return receiverid;
    }

    /**
     * 设置receiverid 下单用户id
     *
     * @param receiverid receiverid 下单用户id
     */
    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    /**
     * 获取delivery 订单状态0为未发货，1为已发货，2为已收货
     *
     * @return delivery - delivery 订单状态0为未发货，1为已发货，2为已收货
     */
    public Integer getDelivery() {
        return delivery;
    }

    /**
     * 设置delivery 订单状态0为未发货，1为已发货，2为已收货
     *
     * @param delivery delivery 订单状态0为未发货，1为已发货，2为已收货
     */
    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }
}