package net.seehope.hxfenxiao.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.seehope.hxfenxiao.mapper.ComplaintMapper;
import net.seehope.hxfenxiao.mapper.DistributorMapper;
import net.seehope.hxfenxiao.pojo.Complaint;
import net.seehope.hxfenxiao.pojo.Distributor;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
	 @Autowired
	 private HttpServletRequest request;
	 
	 @Autowired
	 private HttpServletResponse response;
	 
	 @Autowired
	 private ComplaintMapper complaintMapper;
	 
	 @Autowired
	 private DistributorMapper distributorMapper;
	 
	 @RequestMapping("/comall")
	 public List<Complaint> findallComplaints(){
		 List<Complaint> complaints=complaintMapper.queryAllComplaintAndUserAndDistributor();
		 return complaints;
	 }
	// @RequestMapping(path = "/del",method = RequestMethod.DELETE)
	 @DeleteMapping("/del")
	 public void deleteComplaints(@RequestParam(required = false) @PathVariable String cid) {
		 System.out.println(cid);
		
		 complaintMapper.deleteByPrimaryKey(cid);
	 }
	@PostMapping("/update")
	 public void updateComplaints(@RequestParam(required = false) @PathVariable String cid) {
		System.out.println("update"+cid);
		Complaint complaint=complaintMapper.selectByPrimaryKey(cid);
		System.out.println("userid:"+complaint.getUserid());
		if(complaint.getCstatus()==0) {
			complaint.setCstatus(1);
			complaintMapper.updateByPrimaryKey(complaint);
			System.out.println("did:"+complaint.getDid());
			Distributor distributor=distributorMapper.selectByPrimaryKey(complaint.getDid());
			int dintegral=distributor.getDintegral()-5;
			distributor.setDintegral(dintegral);
			distributorMapper.updateByPrimaryKey(distributor);
		}
		
		
	 }

}
