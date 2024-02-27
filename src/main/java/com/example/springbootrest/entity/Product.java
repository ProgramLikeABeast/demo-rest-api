package com.example.springbootrest.entity;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {
    public Product(){}

//    public Product(String product_name, int cid, String description,
//                   float base_price, boolean enable_hot, boolean enable_cold,
//                   String topping_blacklist, String tags){
//        this.product_name = product_name;
//        this.cid = cid;
//        this.description = description;
//        this.base_price = base_price;
//        this.enable_hot = enable_hot;
//        this.enable_cold = enable_cold;
//        this.topping_blacklist = topping_blacklist;
//        this.tags = tags;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pid")
    private int pid;

    @Column(unique = true)
    private String product_name;

    @Column(name="cid")
    private int cid;

    @Column(name="product_desc")
    private String product_desc;

    @Column(name="base_price")
    private float base_price;

    @Column(name="enable_hot")
    private boolean enable_hot;

    @Column(name="enable_cold")
    private boolean enable_cold;

    @Column(name="topping_blacklist")
    private String topping_blacklist;

    @Column(name="tags")
    private String tags;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String description) {
        this.product_desc = description;
    }

    public float getBase_price() {
        return base_price;
    }

    public void setBase_price(float base_price) {
        this.base_price = base_price;
    }

    public boolean isEnable_hot() {
        return enable_hot;
    }

    public void setEnable_hot(boolean enable_hot) {
        this.enable_hot = enable_hot;
    }

    public boolean isEnable_cold() {
        return enable_cold;
    }

    public void setEnable_cold(boolean enable_cold) {
        this.enable_cold = enable_cold;
    }

    public String getTopping_blacklist() {
        return topping_blacklist;
    }

    public void setTopping_blacklist(String topping_blacklist) {
        this.topping_blacklist = topping_blacklist;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", product_name='" + product_name + '\'' +
                ", cid=" + cid +
                ", description='" + product_desc + '\'' +
                ", base_price=" + base_price +
                ", enable_hot=" + enable_hot +
                ", enable_cold=" + enable_cold +
                ", topping_blacklist='" + topping_blacklist + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
