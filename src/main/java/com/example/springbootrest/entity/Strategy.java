package com.example.springbootrest.entity;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name="strategies")
public class Strategy {

    public Strategy() {}

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="file_name")
    private String fileName;

    @Column(name="content_type")
    private String contentType;

    @Lob
    @Column(name="file_data")
    private byte[] fileData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return fileName;
    }

    public void setFilename(String filename) {
        this.fileName = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] file_data) {
        this.fileData = file_data;
    }

    @Override
    public String toString() {
        return "Strategy{" +
                "id=" + id +
                ", filename='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", file_data=" + Arrays.toString(fileData) +
                '}';
    }
}
