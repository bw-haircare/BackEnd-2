package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long>
{

}
