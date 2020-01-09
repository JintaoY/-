package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;



@Table(name = "`complaint`")
public class Complaint implements Serializable {
    /**
     * cid 投诉ID
     */
    @Id
    @Column(name = "`cid`")
    private String cid;

    /**
     * userid 用户ID
     */
    @Column(name = "`userid`")
    private String userid;

    /**
     * did 经销商ID
     */
    @Column(name = "`did`")
    private String did;

    /**
     * content 投诉内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * cstatus 处理状态 0未处理 1已处理
     */
    @Column(name = "`cstatus`")
    private Integer cstatus;
    
    
    private List<User> users = new ArrayList<User>();
    private List<Distributor> distributors = new ArrayList<Distributor>();
    
    private static final long serialVersionUID = 1L;

    /**
     * 获取cid 投诉ID
     *
     * @return cid - cid 投诉ID
     */
    public String getCid() {
        return cid;
    }

    /**
     * 设置cid 投诉ID
     *
     * @param cid cid 投诉ID
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * 获取userid 用户ID
     *
     * @return userid - userid 用户ID
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置userid 用户ID
     *
     * @param userid userid 用户ID
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取did 经销商ID
     *
     * @return did - did 经销商ID
     */
    public String getDid() {
        return did;
    }

    /**
     * 设置did 经销商ID
     *
     * @param did did 经销商ID
     */
    public void setDid(String did) {
        this.did = did;
    }

    /**
     * 获取content 投诉内容
     *
     * @return content - content 投诉内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置content 投诉内容
     *
     * @param content content 投诉内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取cstatus 处理状态 0未处理 1已处理
     *
     * @return cstatus - cstatus 处理状态 0未处理 1已处理
     */
    public Integer getCstatus() {
        return cstatus;
    }

    /**
     * 设置cstatus 处理状态 0未处理 1已处理
     *
     * @param cstatus cstatus 处理状态 0未处理 1已处理
     */
    public void setCstatus(Integer cstatus) {
        this.cstatus = cstatus;
    }

	
    
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Distributor> getDistributors() {
		return distributors;
	}

	public void setDistributors(List<Distributor> distributors) {
		this.distributors = distributors;
	}

	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Complaint(String cid, String userid, String did, String content, Integer cstatus) {
		super();
		this.cid = cid;
		this.userid = userid;
		this.did = did;
		this.content = content;
		this.cstatus = cstatus;
	}
    
}