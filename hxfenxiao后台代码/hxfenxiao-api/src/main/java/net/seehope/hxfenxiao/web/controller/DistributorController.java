 package net.seehope.hxfenxiao.web.controller;


import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import net.seehope.hxfenxiao.DistributorService;
import net.seehope.hxfenxiao.common.JSONResult;
import net.seehope.hxfenxiao.pojo.Distributor;


/**
 * 经销商控制层
 * @author 100个nice
 * @date 2019/12/11
 */
@RequestMapping("/distributor")
@RestController
 public class DistributorController {
    
    @Autowired
    private DistributorService distributorService;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpServletResponse response;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private static final Logger log = LoggerFactory.getLogger(DistributorController.class);
 
    /**
     *  添加 一级经销商
     * @param distributor
             * 使用了 @Valid 后台验证
     */
    @PostMapping("/add1")
    public void add1(@Valid Distributor distributor) {
        
        //log.info(ReflectionToStringBuilder.toString(distributor));
        System.out.println(distributor.getDid());
        
        distributorService.add1(distributor);
        
    }   
    
    
    /**
     * 查询所有经销商
     */
    @GetMapping("/findAll")
    public PageInfo<Distributor> findAll(Integer dlevel,@RequestParam(name="page",defaultValue = "1") Integer page,
        @RequestParam(name="size",defaultValue = "2") Integer size) {
        
        System.out.println(dlevel);
        System.out.println(page);
        List<Distributor> distributors = distributorService.findAll(dlevel,page,size);
        
        //PageInfo就是一个分页Bean
        PageInfo<Distributor> pageInfo = new PageInfo<Distributor>(distributors);
        
        return pageInfo;    
        
    }
    
    @PutMapping("/closeStatus")
    public void closeStatus(@RequestBody Map<String, Distributor>map) throws IOException {
        
        System.out.println(map.get("params").getDid());
        
        String did = map.get("params").getDid();
        /*{params:{did:1}}*/
        distributorService.closeStatus(did);
     
    }
    @PostMapping("/saveDid")
    public void saveDid(@RequestBody Map<String, Distributor>map) {
        
        System.out.println(map.get("params").getDid());
        //{param:{did:did}} VO
        String did = map.get("params").getDid();
        
        //保持did
        redisTemplate.opsForValue().set("did", did);
        
        
    }
    
    /*@PostMapping("/saveDid1")
    public void saveDid1(@RequestBody Map<String, Distributor>map) {
        
        System.out.println(map.get("params").getDid());
        //{param:{did:did}} VO
        String did = map.get("params").getDid();
        
        //保持did
        HttpSession session = request.getSession();
        session.setAttribute("did", did);
        
        
    }*/
    
    
    @GetMapping("/getDid")
    public JSONResult getDid() {
        
        /*HttpSession session = request.getSession();
        String did = (String)session.getAttribute("did");*/
        
        String did = redisTemplate.opsForValue().get("did");
        
        System.out.println(did);
        
        return JSONResult.isOk(did);
    }
    
    
    
    @PutMapping("/update1")
    public void update1(Distributor distributor) {
        
        System.out.println(distributor.getDid());
        System.out.println(ReflectionToStringBuilder.toString(distributor, ToStringStyle.MULTI_LINE_STYLE));
        
        distributorService.update1(distributor);
        
    }
    
    @GetMapping("/findByDid")
    public PageInfo<Distributor>  findByDid(Integer dlevel,@RequestParam(name="did",defaultValue = "") String did,
        @RequestParam(name="page",defaultValue = "1") Integer page,@RequestParam(name="size",defaultValue = "2") Integer size) {
            
        
        List<Distributor> distributors= distributorService.findByDid(dlevel,"%"+did+"%",page,size);
        
        PageInfo<Distributor> pageInfo = new PageInfo<Distributor>(distributors);
        
        return pageInfo;
    }
    
    
    
    
    
    
}
