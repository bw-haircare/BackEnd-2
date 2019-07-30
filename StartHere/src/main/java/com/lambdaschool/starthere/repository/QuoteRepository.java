package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long>
{

}
