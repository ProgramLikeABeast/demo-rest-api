package com.example.springbootrest.entity;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class Category {

    public Category(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cid")
    private int cid;
    @Column(name="category", unique = true)
    private String category;
    @Column(name="root_category")
    private String root;

    public Category(int cid, String category, String root) {
        this.cid = cid;
        this.category = category;
        this.root = root;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", category='" + category + '\'' +
                ", root='" + root + '\'' +
                '}';
    }
}
