package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class image extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imagesid;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"images", "hibernateLazyInitializer"})
    private User user;

    public image(String fileName, String fileType, byte[] image, User user) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.image = image;
        this.user = user;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getimagesid()
    {
        return imagesid;
    }

    public void setimagesid(long imagesid)
    {
        this.imagesid = imagesid;
    }

    public byte[] getimage()
    {
        return image;
    }

    public void setimage(byte[] image)
    {
        this.image = image;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}