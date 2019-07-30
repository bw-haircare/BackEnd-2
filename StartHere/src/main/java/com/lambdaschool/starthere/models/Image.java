package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "Images")
public class Image extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Imagesid;

    @Column(nullable = false)
    private String Image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"Images", "hibernateLazyInitializer"})
    private User user;

    public Image()
    {
    }

    public Image(String Image, User user)
    {
        this.Image = Image;
        this.user = user;
    }

    public long getImagesid()
    {
        return Imagesid;
    }

    public void setImagesid(long Imagesid)
    {
        this.Imagesid = Imagesid;
    }

    public String getImage()
    {
        return Image;
    }

    public void setImage(String Image)
    {
        this.Image = Image;
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