package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`user`")
public class User implements Serializable {
    /**
     * userid 用户id
     */
    @Id
    @Column(name = "`userid`")
    private String userid;

    /**
     * uusername 账号
     */
    @Column(name = "`uusername`")
    private String uusername;

    /**
     * upassword 密码
     */
    @Column(name = "`upassword`")
    private String upassword;

    /**
     * uintegral 积分
     */
    @Column(name = "`uintegral`")
    private Integer uintegral;

    /**
     * uphoneNum 手机号码
     */
    @Column(name = "`uphoneNum`")
    private String uphonenum;

    /**
     * uemail 邮箱
     */
    @Column(name = "`uaddress`")
    private String uaddress;

    /**
     * usex 性别
     */
    @Column(name = "`usex`")
    private String usex;

    /**
     * ustatus 状态0 未开启 1 开启
     */
    @Column(name = "`ustatus`")
    private Integer ustatus;
    
    /**
     * urealname 用户真实姓名
     */
    @Column(name = "`urealname`")
    private String urealname;
    

    private static final long serialVersionUID = 1L;

    /**
     * 获取userid 用户id
     *
     * @return userid - userid 用户id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置userid 用户id
     *
     * @param userid userid 用户id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取uusername 账号
     *
     * @return uusername - uusername 账号
     */
    public String getUusername() {
        return uusername;
    }

    /**
     * 设置uusername 账号
     *
     * @param uusername uusername 账号
     */
    public void setUusername(String uusername) {
        this.uusername = uusername;
    }

    /**
     * 获取upassword 密码
     *
     * @return upassword - upassword 密码
     */
    public String getUpassword() {
        return upassword;
    }

    /**
     * 设置upassword 密码
     *
     * @param upassword upassword 密码
     */
    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    /**
     * 获取uintegral 积分
     *
     * @return uintegral - uintegral 积分
     */
    public Integer getUintegral() {
        return uintegral;
    }

    /**
     * 设置uintegral 积分
     *
     * @param uintegral uintegral 积分
     */
    public void setUintegral(Integer uintegral) {
        this.uintegral = uintegral;
    }

    /**
     * 获取uphoneNum 手机号码
     *
     * @return uphoneNum - uphoneNum 手机号码
     */
    public String getUphonenum() {
        return uphonenum;
    }

    /**
     * 设置uphoneNum 手机号码
     *
     * @param uphonenum uphoneNum 手机号码
     */
    public void setUphonenum(String uphonenum) {
        this.uphonenum = uphonenum;
    }

   
    /**
     * 获取usex 性别
     *
     * @return usex - usex 性别
     */
    public String getUsex() {
        return usex;
    }

    /**
     * 设置usex 性别
     *
     * @param usex usex 性别
     */
    public void setUsex(String usex) {
        this.usex = usex;
    }

    /**
     * 获取ustatus 状态0 未开启 1 开启
     *
     * @return ustatus - ustatus 状态0 未开启 1 开启
     */
    public Integer getUstatus() {
        return ustatus;
    }

    /**
     * 设置ustatus 状态0 未开启 1 开启
     *
     * @param ustatus ustatus 状态0 未开启 1 开启
     */
    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getUrealname() {
        return urealname;
    }

    public void setUrealname(String urealname) {
        this.urealname = urealname;
    }
    
    
    
    
    
}