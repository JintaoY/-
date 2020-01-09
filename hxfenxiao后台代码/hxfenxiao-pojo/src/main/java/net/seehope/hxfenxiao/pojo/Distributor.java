package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("本类为经销商信息的POJO类")
@Table(name = "`distributor`")
public class Distributor implements Serializable {
    /**
     * did 经销商ID,格式为1,  1.1  1.1.1
     */
    @Id
    @Column(name = "`did`")
    @ApiModelProperty(required = true,value = "经销商编号不能为空")
    @Length(min = 1,message = "经销商编号不能为空")
    private String did;

    /**
     * dphoneNum 手机号码
     */
    @Column(name = "`dphoneNum`")
    @ApiModelProperty(required = true,value = "手机号码的长度必须满足正则")
    //@Length(min = 11,max = 11)
    @Pattern(regexp = "^[1][3,4,5,8][0-9]{9}$",message = "手机号码的长度必须满足正则\"")
    private String dphonenum;

    /**
     * dname 经销商姓名
     */
    @ApiModelProperty(required = true,value = "姓名不能为空")
    @NotBlank(message = "姓名不能为空")
    @Column(name = "`dname`")
    private String dname;

    /**
     * dsex 经销商性别
     */
    
    @Column(name = "`dsex`")
    private String dsex;

    /**
     * daddress 经销商收货地址
     */
    @Column(name = "`daddress`")
    private String daddress;

    /**
     * dstatus 经销商状态0 未开启 1 开启
     */
    @ApiModelProperty(required = true,value = "经销商状态0 未开启 1 开启")
    @NotNull
    @Column(name = "`dstatus`")
    private Integer dstatus;

    /**
     * dintegral 经销商的积分，当被投诉被扣除积分
     */
    @ApiModelProperty(required = false,value = "经销商的积分")
    @Column(name = "`dintegral`")
    private Integer dintegral;

    /**
     * hid 上级经销商did
     */
    @ApiModelProperty(required = false,value = "上级经销商did")
    @Column(name = "`hid`")
    private String hid;
    
    @ApiModelProperty(required = false,value = "经销商级别")
    @Column(name = "`dlevel`")
    private Integer dlevel;

    private static final long serialVersionUID = 1L;

    /**
     * 获取did 经销商ID,格式为1,  1.1  1.1.1
     *
     * @return did - did 经销商ID,格式为1,  1.1  1.1.1
     */
    public String getDid() {
        return did;
    }

    /**
     * 设置did 经销商ID,格式为1,  1.1  1.1.1
     *
     * @param did did 经销商ID,格式为1,  1.1  1.1.1
     */
    public void setDid(String did) {
        this.did = did;
    }

    /**
     * 获取dphoneNum 手机号码
     *
     * @return dphoneNum - dphoneNum 手机号码
     */
    public String getDphonenum() {
        return dphonenum;
    }

    /**
     * 设置dphoneNum 手机号码
     *
     * @param dphonenum dphoneNum 手机号码
     */
    public void setDphonenum(String dphonenum) {
        this.dphonenum = dphonenum;
    }

    /**
     * 获取dname 经销商姓名
     *
     * @return dname - dname 经销商姓名
     */
    public String getDname() {
        return dname;
    }

    /**
     * 设置dname 经销商姓名
     *
     * @param dname dname 经销商姓名
     */
    public void setDname(String dname) {
        this.dname = dname;
    }

    /**
     * 获取dsex 经销商性别
     *
     * @return dsex - dsex 经销商性别
     */
    public String getDsex() {
        return dsex;
    }

    /**
     * 设置dsex 经销商性别
     *
     * @param dsex dsex 经销商性别
     */
    public void setDsex(String dsex) {
        this.dsex = dsex;
    }

    
  
    public String getDaddress() {
        return daddress;
    }

    public void setDaddress(String daddress) {
        this.daddress = daddress;
    }

    /**
     * 获取dstatus 经销商状态0 未开启 1 开启
     *
     * @return dstatus - dstatus 经销商状态0 未开启 1 开启
     */
    public Integer getDstatus() {
        return dstatus;
    }

    /**
     * 设置dstatus 经销商状态0 未开启 1 开启
     *
     * @param dstatus dstatus 经销商状态0 未开启 1 开启
     */
    public void setDstatus(Integer dstatus) {
        this.dstatus = dstatus;
    }

    /**
     * 获取dintegral 经销商的积分，当被投诉被扣除积分
     *
     * @return dintegral - dintegral 经销商的积分，当被投诉被扣除积分
     */
    public Integer getDintegral() {
        return dintegral;
    }

    /**
     * 设置dintegral 经销商的积分，当被投诉被扣除积分
     *
     * @param dintegral dintegral 经销商的积分，当被投诉被扣除积分
     */
    public void setDintegral(Integer dintegral) {
        this.dintegral = dintegral;
    }

    /**
     * 获取hid 上级经销商did
     *
     * @return hid - hid 上级经销商did
     */
    public String getHid() {
        return hid;
    }

    /**
     * 设置hid 上级经销商did
     *
     * @param hid hid 上级经销商did
     */
    public void setHid(String hid) {
        this.hid = hid;
    }

    public Integer getDlevel() {
        return dlevel;
    }

    public void setDlevel(Integer dlevel) {
        this.dlevel = dlevel;
    }
    
    
    
}