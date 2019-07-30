package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Quote;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.repository.QuoteRepository;
import com.lambdaschool.starthere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "quoteService")
public class QuoteServiceImpl implements QuoteService
{
    @Autowired
    private QuoteRepository quoterepos;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Quote> findAll()
    {
        List<Quote> list = new ArrayList<>();
        quoterepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Quote findQuoteById(long id)
    {
        return quoterepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (quoterepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (quoterepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                quoterepos.deleteById(id);
            } else
            {
                throw new ResourceNotFoundException(id + " " + authentication.getName());
            }
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Quote save(Quote quote)
    {
        return quoterepos.save(quote);
    }

    @Override
    public Quote saveQuote(MultipartFile quoteFile, Long userId) {
        String quoteFileName = StringUtils.cleanPath((quoteFile.getOriginalFilename()));
        User user = userRepository.findByUserId(userId);
        try {
            Quote quote = new Quote(quoteFileName, quoteFile.getContentType(), quoteFile.getBytes(), user);
            quoterepos.save(quote);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<Quote> findByUserName(String username)
    {
        List<Quote> list = new ArrayList<>();
        quoterepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}
