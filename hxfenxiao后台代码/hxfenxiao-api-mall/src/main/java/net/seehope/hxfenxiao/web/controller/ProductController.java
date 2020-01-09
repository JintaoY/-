package net.seehope.hxfenxiao.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.mapper.ProductMapper;
import net.seehope.hxfenxiao.pojo.Product;

@RestController
@RequestMapping("/mall/product")
public class ProductController {
	@Autowired
	private ProductMapper productMapper;
	
	 @RequestMapping("/findall")
	 public List<Product> findAllProduct(){
		 List<Product> products=productMapper.selectAll();
		
		 return products;
	 }
     
	
}
