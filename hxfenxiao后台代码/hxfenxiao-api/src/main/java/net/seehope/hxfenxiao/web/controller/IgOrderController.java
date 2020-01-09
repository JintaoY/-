package net.seehope.hxfenxiao.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import net.seehope.hxfenxiao.IgOrderService;
import net.seehope.hxfenxiao.mapper.ProductMapper;
import net.seehope.hxfenxiao.pojo.Igorder;
import net.seehope.hxfenxiao.pojo.Igorder2;
import net.seehope.hxfenxiao.pojo.Product;

/**
 * 积分订单控制层
 * 
 * @author xxx
 * @data 2019年12月11日
 */
@RestController
@RequestMapping("/igorder")
public class IgOrderController {

	@Autowired
	private IgOrderService igOrderService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;
	
	 @Autowired
	 private ProductMapper productMapper;

	/**
	 * 分页查询所有积分订单
	 * 
	 * @data 2019年12月12日 上午10:01:14
	 * @author xxx
	 * @return
	 */
	@GetMapping("/findalligorder")
	public PageInfo<Igorder> findAllIgOrder(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "2") Integer size) {

		List<Igorder> igorders = igOrderService.getAllIgOrderPage(page, size);

		PageInfo<Igorder> pageInfo = new PageInfo<Igorder>(igorders);
		return pageInfo;
	}

	/**
	 * 修改状态
	 * 
	 * @data 2019年12月15日 下午8:58:46
	 * @author xxx
	 * @param map
	 */
	@PutMapping("/modifydelivery")
	public void modifyIgOrderDelivery(@RequestBody Map<String, Igorder> map) {

		System.out.println("igorderid:" + map.get("params").getIgorderid());

		String igorderid = map.get("params").getIgorderid();

		igOrderService.modifyDelivery(igorderid);
	}

	/**
	 * 存储id
	 * 
	 * @data 2019年12月16日 上午11:19:48
	 * @author xxx
	 * @param map
	 */
	@PostMapping("/saveProductId")
	public void saveProductId(@RequestBody Map<String, Product> map) {

		System.out.println("igpid:"+map.get("params").getProductid());

		Integer productid = map.get("params").getProductid();
		HttpSession session = request.getSession();
		session.setAttribute("productid", productid);

	}

	/**
	 * 根据id查找积分商品
	 * @data 2019年12月17日 下午9:46:32
	 * @author xxx
	 * @return
	 */
	@GetMapping("/findIntegralProduct")
	public Product findIntegralProduct() {
		 HttpSession session = request.getSession();
	       int productid =  (int) session.getAttribute("productid");
	       System.out.println("sessionproductid:"+productid);
	      Product product = productMapper.selectByPrimaryKey(productid);
	      return product;
	}
	
	/**
	 * 更新商品积分
	 * @data 2019年12月17日 下午9:57:33
	 * @author xxx
	 * @param product
	 */
	@PostMapping("/updateProductInteger")
	public void updateProductInteger(Product product) {
		
		System.out.println("updateproduct:"+product.getProductscore()+","+product.getProductchangescore()+","+product.getProductid());
		productMapper.updateByPrimaryKeySelective(product);
		
	}
	
	/**
	 * 统计报表获取积分订单的商品名（去重）
	 * 
	 * @data 2019年12月17日 上午11:49:54
	 * @author xxx
	 * @return
	 */
	@GetMapping("/findIgorderProductName")
	public List<String> findProductname() {
		// 获取积分订单的商品id
		List<Igorder2> igorder2s = igOrderService.findIgOrderProductName();
		List<String> pnameList = new ArrayList<>();
		for (Igorder2 igorder2 : igorder2s) {
			pnameList.add(igorder2.getProductname());
		}
		System.out.println("pnameList" + pnameList);

		return pnameList;
	}

	/**
	 * 统计报表获取商品的兑换总数
	 * @data 2019年12月18日 上午9:47:57
	 * @author xxx
	 * @return
	 */
	@GetMapping("/getCount")
	public List<Integer> getIgOrderCount() {
		List<String> pidList = new ArrayList<>();
		// 获取积分订单的商品id
		List<Igorder2> igorder2s = igOrderService.findIgOrderProductName();
		for (Igorder2 igorder2 : igorder2s) {
			pidList.add(igorder2.getPid());
		}
		System.out.println("pidList" + pidList);
		//存储商品的兑换数量
		List<Integer> countList = new ArrayList<>();
		for (int i = 0; i < pidList.size(); i++) {
			System.out.println(pidList.get(i));
			int pid = igOrderService.getIgorderCount(pidList.get(i));
			countList.add(pid);
		}
		System.out.println("countList" + countList);
		return countList;
	}

}
