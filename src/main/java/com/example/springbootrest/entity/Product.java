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
    @Column(name="product_name", unique = true)
    private String productName;
    @Column(name="cid")
    private int cid;
    @Column(name="product_desc")
    private String productDesc;
    @Column(name="base_price")
    private float basePrice;
    @Column(name="has_flavor")
    private boolean hasFlavor;
    @Column(name="enable_hot")
    private boolean enable_hot;
    @Column(name="enable_cold")
    private boolean enableCold;
    @Column(name="enable_large")
    private boolean enableLarge;
    @Column(name="avail_sugar_level")
    private String availSugarLevel;
    @Column(name="avail_ice_level")
    private String availIceLevel;
    @Column(name="topping_blacklist")
    private String toppingBlacklist;
    @Column(name="free_topping_quantity")
    private int freeToppingQuantity;
    @Column(name="ice_cream_container")
    private String iceCreamContainer;
    @Column(name="tags")
    private String tags;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String product_name) {
        this.productName = product_name;
    }
    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public String getProductDesc() {
        return productDesc;
    }
    public void setProductDesc(String description) {
        this.productDesc = description;
    }
    public float getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(float base_price) {
        this.basePrice = base_price;
    }
    public boolean isEnable_hot() {
        return enable_hot;
    }
    public void setEnable_hot(boolean enable_hot) {
        this.enable_hot = enable_hot;
    }
    public boolean isEnableCold() {
        return enableCold;
    }
    public void setEnableCold(boolean enable_cold) {
        this.enableCold = enable_cold;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isHasFlavor() {
        return hasFlavor;
    }

    public void setHasFlavor(boolean hasFlavor) {
        this.hasFlavor = hasFlavor;
    }

    public boolean isEnableLarge() {
        return enableLarge;
    }

    public void setEnableLarge(boolean enableLarge) {
        this.enableLarge = enableLarge;
    }

    public String getAvailSugarLevel() {
        return availSugarLevel;
    }

    public void setAvailSugarLevel(String availSugarLevel) {
        this.availSugarLevel = availSugarLevel;
    }

    public String getAvailIceLevel() {
        return availIceLevel;
    }

    public void setAvailIceLevel(String availIceLevel) {
        this.availIceLevel = availIceLevel;
    }

    public String getToppingBlacklist() {
        return toppingBlacklist;
    }

    public void setToppingBlacklist(String toppingBlacklist) {
        this.toppingBlacklist = toppingBlacklist;
    }

    public int getFreeToppingQuantity() {
        return freeToppingQuantity;
    }

    public void setFreeToppingQuantity(int freeToppingQuantity) {
        this.freeToppingQuantity = freeToppingQuantity;
    }

    public String getIceCreamContainer() {
        return iceCreamContainer;
    }

    public void setIceCreamContainer(String iceCreamContainer) {
        this.iceCreamContainer = iceCreamContainer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", product_name='" + productName + '\'' +
                ", cid=" + cid +
                ", description='" + productDesc + '\'' +
                ", base_price=" + basePrice +
                ", enable_hot=" + enable_hot +
                ", enable_cold=" + enableCold +
                ", topping_blacklist='" + toppingBlacklist + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
