package com.example.springbootrest.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {
    public Order(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oid")
    int oid;
    @Column(name="uid")
    int uid;
    @Column(name="detail")
    String detail;
    @Column(name="discount_amount")
    float discountAmount;
    @Column(name="status_code")
    int status;
    @Column(name="ts")
    LocalDateTime ts;

    public Order(int uid, String detail, float discountAmount) {
        this.oid = 0;
        this.uid = uid;
        this.detail = detail;
        this.discountAmount = discountAmount;
        this.status = 0;
        String dateTimeStr = "1970-01-01T00:00:00";
        this.ts = LocalDateTime.parse(dateTimeStr);
    }

    @PrePersist
    protected void onCreate() {
        ts = LocalDateTime.now();
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discount_amount) {
        this.discountAmount = discount_amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status_code) {
        this.status = status_code;
    }

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", detail=" + detail +
                ", discount_amount=" + discountAmount +
                ", status_code=" + status +
                ", ts='" + ts + '\'' +
                '}';
    }
}
