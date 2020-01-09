package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`code`")
public class Code implements Serializable {
    /**
     * 包装码
     */
    @Column(name = "`packcode`")
    private String packcode;

    /**
     * 小码
     */
    @Column(name = "`productcode`")
    private String productcode;

    /**
     * 产品id
     */
    @Column(name = "`pid`")
    private Integer pid;

    /**
     * 领取积分状态 1未领取 0领取
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 所归属经销商
     */
    @Column(name = "`did`")
    private String did;

    private static final long serialVersionUID = 1L;

    /**
     * 获取包装码
     *
     * @return packcode - 包装码
     */
    public String getPackcode() {
        return packcode;
    }

    /**
     * 设置包装码
     *
     * @param packcode 包装码
     */
    public void setPackcode(String packcode) {
        this.packcode = packcode;
    }

    /**
     * 获取小码
     *
     * @return productcode - 小码
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * 设置小码
     *
     * @param productcode 小码
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    /**
     * 获取产品id
     *
     * @return pid - 产品id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置产品id
     *
     * @param pid 产品id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取领取积分状态 1未领取 0领取
     *
     * @return status - 领取积分状态 1未领取 0领取
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置领取积分状态 1未领取 0领取
     *
     * @param status 领取积分状态 1未领取 0领取
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取所归属经销商
     *
     * @return did - 所归属经销商
     */
    public String getDid() {
        return did;
    }

    /**
     * 设置所归属经销商
     *
     * @param did 所归属经销商
     */
    public void setDid(String did) {
        this.did = did;
    }
}