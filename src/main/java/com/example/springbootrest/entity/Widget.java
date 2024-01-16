package com.example.springbootrest.entity;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name="widgets")
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String widget_name;
    @Column
    private String text_description;
    @Column
    private String avatar_image_content_type;
    @Column
    private String avatar_image_filename;
    @Column
    private byte[] avatar_image_data;
    @Column
    private String large_image_content_type;
    @Column
    private String large_image_filename;
    @Column
    private byte[] large_image_data;
    @Column
    private String bg_color;

    public Widget() {}

    public Widget(String widget_name, String text_description, String bg_color, String avatar_image_content_type, String avatar_image_filename, byte[] avatar_image_data, String large_image_content_type, String large_image_filename, byte[] large_image_data) {
        this.widget_name = widget_name;
        this.text_description = text_description;
        this.avatar_image_content_type = avatar_image_content_type;
        this.avatar_image_filename = avatar_image_filename;
        this.avatar_image_data = avatar_image_data;
        this.large_image_content_type = large_image_content_type;
        this.large_image_filename = large_image_filename;
        this.large_image_data = large_image_data;
        this.bg_color = bg_color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWidget_name() {
        return widget_name;
    }

    public void setWidget_name(String widget_name) {
        this.widget_name = widget_name;
    }

    public String getText_description() {
        return text_description;
    }

    public void setText_description(String text_description) {
        this.text_description = text_description;
    }

    public byte[] getAvatar_image_data() {
        return avatar_image_data;
    }

    public void setAvatar_image_data(byte[] avatar_image_data) {
        this.avatar_image_data = avatar_image_data;
    }

    public byte[] getLarge_image_data() {
        return large_image_data;
    }

    public void setLarge_image_data(byte[] large_image_data) {
        this.large_image_data = large_image_data;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public String getAvatar_image_content_type() {
        return avatar_image_content_type;
    }

    public void setAvatar_image_content_type(String avatar_image_content_type) {
        this.avatar_image_content_type = avatar_image_content_type;
    }

    public String getAvatar_image_filename() {
        return avatar_image_filename;
    }

    public void setAvatar_image_filename(String avatar_image_filename) {
        this.avatar_image_filename = avatar_image_filename;
    }

    public String getLarge_image_content_type() {
        return large_image_content_type;
    }

    public void setLarge_image_content_type(String large_image_content_type) {
        this.large_image_content_type = large_image_content_type;
    }

    public String getLarge_image_filename() {
        return large_image_filename;
    }

    public void setLarge_image_filename(String large_image_filename) {
        this.large_image_filename = large_image_filename;
    }

    @Override
    public String toString() {
        return "Widget{" +
                "id=" + id +
                ", widget_name='" + widget_name + '\'' +
                ", text_description='" + text_description + '\'' +
                ", avatar_image_data=" + Arrays.toString(avatar_image_data) +
                ", large_image_data=" + Arrays.toString(large_image_data) +
                ", bg_color='" + bg_color + '\'' +
                '}';
    }
}
