package net.seehope.hxfenxiao;

import java.util.List;

import net.seehope.hxfenxiao.pojo.Igorder;
import net.seehope.hxfenxiao.pojo.Igorder2;

/**
 * 积分管理业务层
 * @author xxx
 * @data 2019年12月11日
 */
public interface IgOrderService {

	/**
	 * 查询所有积分订单
	 * @data 2019年12月11日 下午9:15:01
	 * @author xxx
	 */
	public List<Igorder> getAllIgOrder();
	
	/**
	 * 分页查询所有积分订单
	 * @data 2019年12月11日 下午9:15:01
	 * @author xxx
	 */
	public List<Igorder> getAllIgOrderPage(Integer page,Integer size);
	
	/**
	 * 修改积分订单状态
	 * @data 2019年12月14日 下午10:09:59
	 * @author xxx
	 */
	public void modifyDelivery(String igorderid);
	
	/**
	 * 查找积分订单商品的商品名
	 * @data 2019年12月16日 下午8:16:47
	 * @author xxx
	 * @param productid
	 */
	public List<Igorder2> findIgOrderProductName();
	
	/**
	 * 根据商品id查询积分订单数量
	 * @data 2019年12月17日 上午11:04:44
	 * @author xxx
	 * @param object
	 * @return
	 */
	public int getIgorderCount(String pid);
}
