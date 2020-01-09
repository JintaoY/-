package net.seehope.hxfenxiao.pojo;

import java.io.Serializable;

public class Order2 extends Order implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String productname;
    // private Order2 order2;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    // public Order2 getOrder2() {
    // return order2;
    // }
    //
    // public void setOrder2(Order2 order2) {
    // this.order2 = order2;
    // }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}