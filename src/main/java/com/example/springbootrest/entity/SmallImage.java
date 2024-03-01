package com.example.springbootrest.entity;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name="small_images")
public class SmallImage {

    @Id
    @Column(name="siid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int siid;
    @Column(name="pid")
    private int pid;
    @Column(name="image_name")
    private String image_name;
    @Column(name="content_type")
    private String content_type;
    @Column(name="image_data")
    private byte[] image_data;

    public SmallImage() {
    }

    public SmallImage(int pid, String image_name, String content_type, byte[] image_data) {
        this.pid = pid;
        this.image_name = image_name;
        this.content_type = content_type;
        this.image_data = image_data;
    }

    public int getSiid() {
        return siid;
    }

    public void setSiid(int siid) {
        this.siid = siid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public byte[] getImage_data() {
        return image_data;
    }

    public void setImage_data(byte[] image_data) {
        this.image_data = image_data;
    }

    @Override
    public String toString() {
        return "SmallPicture{" +
                "siid=" + siid +
                ", pid=" + pid +
                ", image_name='" + image_name + '\'' +
                ", content_type='" + content_type + '\'' +
                ", image_data=" + Arrays.toString(image_data) +
                '}';
    }
}
