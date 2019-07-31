package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface imageService
{
    List<image> findAll();

    image findimageById(long id);

    List<image> findByUserName(String username);

    void delete(long id);

    image save(image image);

    image saveimage(MultipartFile imageFile, Long userid);


}
