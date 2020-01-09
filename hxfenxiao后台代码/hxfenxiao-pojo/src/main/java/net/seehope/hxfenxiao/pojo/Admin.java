package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`admin`")
public class Admin implements Serializable {
    /**
     * adminid 用户ID
     */
    @Id
    @Column(name = "`adminid`")
    private String adminid;

    /**
     * ausername 账号
     */
    @Column(name = "`ausername`")
    private String ausername;

    /**
     * apassword 密码
     */
    @Column(name = "`apassword`")
    private String apassword;

    /**
     * aphoneNum 电话
     */
    @Column(name = "`aphoneNum`")
    private String aphonenum;

    /**
     * aemail 邮箱
     */
    @Column(name = "`aemail`")
    private String aemail;

    /**
     * astatus 状态0 未开启 1 开启
     */
    @Column(name = "`astatus`")
    private Integer astatus;

    private static final long serialVersionUID = 1L;

    /**
     * 获取adminid 用户ID
     *
     * @return adminid - adminid 用户ID
     */
    public String getAdminid() {
        return adminid;
    }

    /**
     * 设置adminid 用户ID
     *
     * @param adminid adminid 用户ID
     */
    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    /**
     * 获取ausername 账号
     *
     * @return ausername - ausername 账号
     */
    public String getAusername() {
        return ausername;
    }

    /**
     * 设置ausername 账号
     *
     * @param ausername ausername 账号
     */
    public void setAusername(String ausername) {
        this.ausername = ausername;
    }

    /**
     * 获取apassword 密码
     *
     * @return apassword - apassword 密码
     */
    public String getApassword() {
        return apassword;
    }

    /**
     * 设置apassword 密码
     *
     * @param apassword apassword 密码
     */
    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    /**
     * 获取aphoneNum 电话
     *
     * @return aphoneNum - aphoneNum 电话
     */
    public String getAphonenum() {
        return aphonenum;
    }

    /**
     * 设置aphoneNum 电话
     *
     * @param aphonenum aphoneNum 电话
     */
    public void setAphonenum(String aphonenum) {
        this.aphonenum = aphonenum;
    }

    /**
     * 获取aemail 邮箱
     *
     * @return aemail - aemail 邮箱
     */
    public String getAemail() {
        return aemail;
    }

    /**
     * 设置aemail 邮箱
     *
     * @param aemail aemail 邮箱
     */
    public void setAemail(String aemail) {
        this.aemail = aemail;
    }

    /**
     * 获取astatus 状态0 未开启 1 开启
     *
     * @return astatus - astatus 状态0 未开启 1 开启
     */
    public Integer getAstatus() {
        return astatus;
    }

    /**
     * 设置astatus 状态0 未开启 1 开启
     *
     * @param astatus astatus 状态0 未开启 1 开启
     */
    public void setAstatus(Integer astatus) {
        this.astatus = astatus;
    }
}