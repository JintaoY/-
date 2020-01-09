package net.seehope.hxfenxiao.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.seehope.hxfenxiao.mapper.ProductMapper;
import net.seehope.hxfenxiao.mapper.UserMapper;
import net.seehope.hxfenxiao.pojo.Distributor;
import net.seehope.hxfenxiao.pojo.Product;

@RestController
@RequestMapping("/product")
@ResponseBody
public class ProductController {

	 @Autowired
	 private HttpServletRequest request;
	 
	 @Autowired
	 private HttpServletResponse response;
	 
	 @Autowired
	 private ProductMapper productMapper;
	 
	// @GetMapping("/findall")
      @RequestMapping("/findall")
	 public List<Product> findAllProduct(){
		 List<Product> products=productMapper.selectAll();
		
		 return products;
	 }
	 @PostMapping("/add")
	 public void addProduct(@RequestParam("file") MultipartFile file, Product product) throws IOException {
	 
		
		 // 上传的位置
	    String realPath = "D:\\javaeeeclipse\\fenxiao2\\fenxiao\\hxfenxiao1\\hxfenxiao-api\\src\\main\\resources\\static\\hx\\images";

	    System.out.println("real "+realPath);
	   
	    //获得文件名
	    String filename = file.getOriginalFilename();
	    
 	    System.out.println(filename);
	   
	    file.transferTo(new File(realPath+"/"+filename));
	    filename="images/"+filename;
	    product.setProductimage(filename);
	    java.util.Date  date=new java.util.Date();
	    java.sql.Date  data1=new java.sql.Date(date.getTime());
	    product.setProductdate(data1);
	    product.setProductsale(0);
	    productMapper.insert(product);
		    
		
	 }

	 @DeleteMapping("/del")
	 public void deleteProduct(@RequestParam(required = false) @PathVariable int productid) {
		 System.out.println("del:"+productid);
		 productMapper.deleteByPrimaryKey(productid);
		 
	 }

	 
	 @PostMapping("/save")
	 public void saveProductid(@RequestParam(required = false) @PathVariable Integer productid) {
		 System.out.println(productid);
		 HttpSession session = request.getSession();
		 session.setAttribute("productid", productid);
	 }
	 
	 @RequestMapping("findbyId")
	 public Product findbyId() {
		 HttpSession session = request.getSession();
	        int productid =  (int) session.getAttribute("productid");
	        System.out.println("findbyid:"+productid);
	      Product product=productMapper.selectByPrimaryKey(Integer.valueOf(productid));
	       System.out.println(product.getProductname());
	       System.out.println("date:"+product.getProductdate());
	       return product;
	 }
	 
	 @PostMapping("/update")
	 public void update(@RequestParam("file") MultipartFile file, Product product) throws IllegalStateException, IOException {

		 // 上传的位置
	    String realPath = "D:\\javaeeeclipse\\fenxiao2\\fenxiao\\hxfenxiao1\\hxfenxiao-api\\src\\main\\resources\\static\\hx\\images";
	    //上传经销商工程的相册
	    System.out.println("real "+realPath);
	    //  获得文件名
	    String filename = file.getOriginalFilename();
	    
	    System.out.println(filename);
	   
	    file.transferTo(new File(realPath,filename));
	    filename="images/"+filename;
	    product.setProductimage(filename);

	    productMapper.updateByPrimaryKeySelective(product);
		    
	 }

	 
	 
	
	 
	 

}
