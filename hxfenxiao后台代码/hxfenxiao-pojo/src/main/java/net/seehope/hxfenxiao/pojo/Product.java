package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;



@Table(name = "`product`")
public class Product implements Serializable {
    /**
     * productid 产品id
     */
	@Id
    @Column(name = "`productid`")
    private Integer productid;

    /**
     * productname 产品名
     */
    @Column(name = "`productname`")
    private String productname;

    /**
     * productprice1 1级价格
     */
    @Column(name = "`productprice1`")
    private BigDecimal productprice1;

    /**
     * productprice2 2级价格
     */
    @Column(name = "`productprice2`")
    private BigDecimal productprice2;

    /**
     * productprice3 3级价格
     */
    @Column(name = "`productprice3`")
    private BigDecimal productprice3;

    /**
     * productscore 产品积分
     */
    @Column(name = "`productscore`")
    private Integer productscore;

    /**
     * productchangescore 兑换积分
     */
    @Column(name = "`productchangescore`")
    private Integer productchangescore;

    /**
     * 产品图片
     */
    @Column(name = "`productimage`")
    private String productimage;

    /**
     * 产品上架时间
     */
    @Column(name = "`productdate`")
    private Date productdate;

    /**
     * 产品描述
     */
    @Column(name = "`productdesc`")
    private String productdesc;

    /**
     * 产品销量
     */
    @Column(name = "`productsale`")
    private Integer productsale;

    private static final long serialVersionUID = 1L;

    /**
     * 获取productid 产品id
     *
     * @return productid - productid 产品id
     */
    public Integer getProductid() {
        return productid;
    }

    /**
     * 设置productid 产品id
     *
     * @param productid productid 产品id
     */
    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    /**
     * 获取productname 产品名
     *
     * @return productname - productname 产品名
     */
    public String getProductname() {
        return productname;
    }

    /**
     * 设置productname 产品名
     *
     * @param productname productname 产品名
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * 获取productprice1 1级价格
     *
     * @return productprice1 - productprice1 1级价格
     */
    public BigDecimal getProductprice1() {
        return productprice1;
    }

    /**
     * 设置productprice1 1级价格
     *
     * @param productprice1 productprice1 1级价格
     */
    public void setProductprice1(BigDecimal productprice1) {
        this.productprice1 = productprice1;
    }

    /**
     * 获取productprice2 2级价格
     *
     * @return productprice2 - productprice2 2级价格
     */
    public BigDecimal getProductprice2() {
        return productprice2;
    }

    /**
     * 设置productprice2 2级价格
     *
     * @param productprice2 productprice2 2级价格
     */
    public void setProductprice2(BigDecimal productprice2) {
        this.productprice2 = productprice2;
    }

    /**
     * 获取productprice3 3级价格
     *
     * @return productprice3 - productprice3 3级价格
     */
    public BigDecimal getProductprice3() {
        return productprice3;
    }

    /**
     * 设置productprice3 3级价格
     *
     * @param productprice3 productprice3 3级价格
     */
    public void setProductprice3(BigDecimal productprice3) {
        this.productprice3 = productprice3;
    }

    /**
     * 获取productscore 产品积分
     *
     * @return productscore - productscore 产品积分
     */
    public Integer getProductscore() {
        return productscore;
    }

    /**
     * 设置productscore 产品积分
     *
     * @param productscore productscore 产品积分
     */
    public void setProductscore(Integer productscore) {
        this.productscore = productscore;
    }

    /**
     * 获取productchangescore 兑换积分
     *
     * @return productchangescore - productchangescore 兑换积分
     */
    public Integer getProductchangescore() {
        return productchangescore;
    }

    /**
     * 设置productchangescore 兑换积分
     *
     * @param productchangescore productchangescore 兑换积分
     */
    public void setProductchangescore(Integer productchangescore) {
        this.productchangescore = productchangescore;
    }

    /**
     * 获取产品图片
     *
     * @return productimage - 产品图片
     */
    public String getProductimage() {
        return productimage;
    }

    /**
     * 设置产品图片
     *
     * @param productimage 产品图片
     */
    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    /**
     * 获取产品上架时间
     *
     * @return productdate - 产品上架时间
     */
    public Date getProductdate() {
        return productdate;
    }

    /**
     * 设置产品上架时间
     *
     * @param productdate 产品上架时间
     */
    public void setProductdate(Date productdate) {
        this.productdate = productdate;
    }

    /**
     * 获取产品描述
     *
     * @return productdesc - 产品描述
     */
    public String getProductdesc() {
        return productdesc;
    }

    /**
     * 设置产品描述
     *
     * @param productdesc 产品描述
     */
    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    /**
     * 获取产品销量
     *
     * @return productsale - 产品销量
     */
    public Integer getProductsale() {
        return productsale;
    }

    /**
     * 设置产品销量
     *
     * @param productsale 产品销量
     */
    public void setProductsale(Integer productsale) {
        this.productsale = productsale;
    }

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productname, BigDecimal productprice1, BigDecimal productprice2, BigDecimal productprice3,
			Integer productscore, Integer productchangescore, String productimage, String productdesc,
			Integer productsale) {
		super();
		this.productname = productname;
		this.productprice1 = productprice1;
		this.productprice2 = productprice2;
		this.productprice3 = productprice3;
		this.productscore = productscore;
		this.productchangescore = productchangescore;
		this.productimage = productimage;
		
		this.productdesc = productdesc;
		this.productsale = productsale;
	}
    
}