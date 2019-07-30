package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Image;

import java.util.List;

public interface ImageService
{
    List<Image> findAll();

    Image findImageById(long id);

    List<Image> findByUserName(String username);

    void delete(long id);

    Image save(Image Image);
}
