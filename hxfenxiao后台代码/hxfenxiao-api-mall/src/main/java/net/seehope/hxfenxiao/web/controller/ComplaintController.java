package net.seehope.hxfenxiao.web.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.mapper.ComplaintMapper;
import net.seehope.hxfenxiao.mapper.DistributorMapper;
import net.seehope.hxfenxiao.pojo.Complaint;
import net.seehope.hxfenxiao.pojo.Distributor;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@RequestMapping("/mall/complaint")
@RestController
public class ComplaintController {

	 @Autowired
	 private ComplaintMapper complaintMapper;
	 
	 @Autowired
	 private DistributorMapper distributorMapper;
	 
	 @GetMapping("/allDname")
	 public JSONResult findallComplaints(){
		 List<Distributor> distributors = distributorMapper.selectAll();
		 List<String> list = new ArrayList<String>();
		 for (Distributor distributor : distributors) {
			list.add(distributor.getDname());
		}
		 return JSONResult.isOk(list);
	 }
	 
	 @PostMapping("/addcomplaint")
	 public JSONResult addComplaints(String userid,String dname,String content) {
		 Example example = new Example(Distributor.class);
	        Criteria criteria = example.createCriteria();   
	        criteria.andEqualTo("dname", dname);
		 Distributor distributor = distributorMapper.selectOneByExample(example);
		 String did = distributor.getDid();
		 Complaint complaint = new Complaint();
		 complaint.setCid(UUID.randomUUID()+"");
		 complaint.setUserid(userid);
		 complaint.setDid(did);
		 complaint.setContent(content);
		 complaint.setCstatus(0);
		 complaintMapper.insertSelective(complaint);
		
		 return JSONResult.isOk(null);
	 }
}
