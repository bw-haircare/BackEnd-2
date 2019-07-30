package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Image;
import com.lambdaschool.starthere.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "ImageService")
public class ImageServiceImpl implements ImageService
{
    @Autowired
    private ImageRepository Imagerepos;

    @Override
    public List<Image> findAll()
    {
        List<Image> list = new ArrayList<>();
        Imagerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Image findImageById(long id)
    {
        return Imagerepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (Imagerepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (Imagerepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                Imagerepos.deleteById(id);
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
    public Image save(Image Image)
    {
        return Imagerepos.save(Image);
    }

    @Override
    public List<Image> findByUserName(String username)
    {
        List<Image> list = new ArrayList<>();
        Imagerepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}
