package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Image;
import com.lambdaschool.starthere.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/Images")
public class ImagesController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    ImageService ImageService;

    @GetMapping(value = "/Images",
                produces = {"application/json"})
    public ResponseEntity<?> listAllImages(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Image> allImages = ImageService.findAll();
        return new ResponseEntity<>(allImages, HttpStatus.OK);
    }


    @GetMapping(value = "/Image/{ImageId}",
                produces = {"application/json"})
    public ResponseEntity<?> getImage(HttpServletRequest request,
                                      @PathVariable
                                              Long ImageId)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        Image q = ImageService.findImageById(ImageId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findImageByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String userName)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Image> theImages = ImageService.findByUserName(userName);
        return new ResponseEntity<>(theImages, HttpStatus.OK);
    }


    @PostMapping(value = "/Image")
    public ResponseEntity<?> addNewImage(HttpServletRequest request, @Valid
    @RequestBody
            Image newImage) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newImage = ImageService.save(newImage);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newImageURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Imageid}").buildAndExpand(newImage.getImagesid()).toUri();
        responseHeaders.setLocation(newImageURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/Image/{id}")
    public ResponseEntity<?> deleteImageById(HttpServletRequest request,
                                             @PathVariable
                                                     long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        ImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
