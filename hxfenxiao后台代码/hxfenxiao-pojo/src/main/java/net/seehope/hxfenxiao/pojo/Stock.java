package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`stock`")
public class Stock implements Serializable {
    /**
     * did 经销商id
     */
    @Column(name = "`did`")
    private String did;

    /**
     * pname 库存产品名
     */
    @Column(name = "`pname`")
    private String pname;

    /**
     * pnum 库存产品数量
     */
    @Column(name = "`pnum`")
    private Integer pnum;

    private static final long serialVersionUID = 1L;

    /**
     * 获取did 经销商id
     *
     * @return did - did 经销商id
     */
    public String getDid() {
        return did;
    }

    /**
     * 设置did 经销商id
     *
     * @param did did 经销商id
     */
    public void setDid(String did) {
        this.did = did;
    }

    /**
     * 获取pname 库存产品名
     *
     * @return pname - pname 库存产品名
     */
    public String getPname() {
        return pname;
    }

    /**
     * 设置pname 库存产品名
     *
     * @param pname pname 库存产品名
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * 获取pnum 库存产品数量
     *
     * @return pnum - pnum 库存产品数量
     */
    public Integer getPnum() {
        return pnum;
    }

    /**
     * 设置pnum 库存产品数量
     *
     * @param pnum pnum 库存产品数量
     */
    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }
}