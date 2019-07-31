package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface imageRepository extends CrudRepository<image, Long>
{

}
