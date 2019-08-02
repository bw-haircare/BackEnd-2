package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.image;
import com.lambdaschool.starthere.services.UserService;
import com.lambdaschool.starthere.services.imageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class imagesController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    UserService userService;

    @Autowired
    imageService imageService;



    @GetMapping(value = "/images",
                produces = {"application/json"})
    public ResponseEntity<?> listAllimages(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<image> allimages = imageService.findAll();
        return new ResponseEntity<>(allimages, HttpStatus.OK);
    }


    @GetMapping(value = "/image/{imageId}",
                produces = {"application/json"})
    public ResponseEntity<?> getimage(HttpServletRequest request,
                                      @PathVariable
                                              Long imageId)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        image q = imageService.findimageById(imageId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findimageByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String userName)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<image> theimages = imageService.findByUserName(userName);
        return new ResponseEntity<>(theimages, HttpStatus.OK);
    }
    

    @PostMapping(value = "/image/user/{userid}")
    public ResponseEntity<?> addNewimage(HttpServletRequest request, @RequestParam MultipartFile imageFile, @PathVariable String userid, @Valid
    @RequestBody image newimage) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");
        Long id = Long.valueOf(userid);

        imageService.saveimage(imageFile, id);


        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteimageById(HttpServletRequest request,
                                             @PathVariable
                                                     long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
