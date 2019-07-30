package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long quotesid;

    private String fileName;
    private String fileType;

    @Lob
    private byte quote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"quotes", "hibernateLazyInitializer"})
    private User user;

    public Quote(String fileName, String fileType, byte[] quote, User user) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.quote = quote;
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

    public long getQuotesid()
    {
        return quotesid;
    }

    public void setQuotesid(long quotesid)
    {
        this.quotesid = quotesid;
    }

    public byte getQuote()
    {
        return quote;
    }

    public void setQuote(byte quote)
    {
        this.quote = quote;
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