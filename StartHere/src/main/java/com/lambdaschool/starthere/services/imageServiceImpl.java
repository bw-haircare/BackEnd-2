package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.image;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.repository.imageRepository;
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

@Service(value = "imageService")
public class imageServiceImpl implements imageService
{
    @Autowired
    private imageRepository imagerepos;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<image> findAll()
    {
        List<image> list = new ArrayList<>();
        imagerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public image findimageById(long id)
    {
        return imagerepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (imagerepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (imagerepos.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                imagerepos.deleteById(id);
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
    public image save(image image)
    {
        return imagerepos.save(image);
    }

    @Override
    public image saveimage(MultipartFile imageFile, Long userid) {
        String imageFileName = StringUtils.cleanPath((imageFile.getOriginalFilename()));
        User user = userRepository.findByuserid(userid);
        try {
            image image = new image(imageFileName, imageFile.getContentType(), imageFile.getBytes(), user);
            imagerepos.save(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<image> findByUserName(String username)
    {
        List<image> list = new ArrayList<>();
        imagerepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}
