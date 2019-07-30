package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Quote;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface QuoteService
{
    List<Quote> findAll();

    Quote findQuoteById(long id);

    List<Quote> findByUserName(String username);

    void delete(long id);

    Quote save(Quote quote);

    Quote saveQuote(MultipartFile quoteFile, Long userId);


}
