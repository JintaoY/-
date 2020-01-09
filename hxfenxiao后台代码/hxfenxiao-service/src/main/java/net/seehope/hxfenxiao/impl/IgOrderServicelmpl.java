package net.seehope.hxfenxiao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.seehope.hxfenxiao.IgOrderService;
import net.seehope.hxfenxiao.mapper.IgorderMapper;
import net.seehope.hxfenxiao.pojo.Igorder;
import net.seehope.hxfenxiao.pojo.Igorder2;

@Service
public class IgOrderServicelmpl implements IgOrderService{

	@Autowired
	private IgorderMapper igorderMapper;
	
	private Igorder igorder = new Igorder();
	
	/**
	 * 所有积分订单
	 * @return 
	 */
	@Override
	public List<Igorder> getAllIgOrder() {
		// TODO Auto-generated method stub
		
		List<Igorder> list=igorderMapper.selectAll();
		
		return list;
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<Igorder> getAllIgOrderPage(Integer page, Integer size) {
		// TODO Auto-generated method stub
		 PageHelper.startPage(page, size);
		 List<Igorder> list=igorderMapper.selectAll();
		return list;
	}

	/**
	 * 修改状态
	 */
	@Override
	public void modifyDelivery(String igorderid) {
		// TODO Auto-generated method stub
		
		Igorder igorder =igorderMapper.selectByPrimaryKey(igorderid);
		//0为未收货，1为已收货
		if(igorder.getDelivery()==0) {
			igorder.setDelivery(1);
		}else if (igorder.getDelivery()==1) {
			igorder.setDelivery(0);
		}
		
		igorderMapper.updateByPrimaryKey(igorder);
	}

	/**
	 * 根据积分订单的商品id查找商品名
	 */
	@Override
	public List<Igorder2> findIgOrderProductName() {
		// TODO Auto-generated method stub
	 return	igorderMapper.findIgOrderProductName();
		
	}

	/**
	 * 查找某个商品的积分订单数量
	 */
	@Override
	public int getIgorderCount(String pid) {
		// TODO Auto-generated method stub
		igorder.setPid(pid);
		 return igorderMapper.selectCount(igorder);
	}

	
}
