package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Profile;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.services.ProfileService;
import com.lambdaschool.starthere.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/profiles", produces = {"application/json"})
    public ResponseEntity<?> getListOfAllProfiles() {
        ArrayList<Profile> allProfiles = profileService.findAll();
        return new ResponseEntity<>(allProfiles, HttpStatus.OK);
    }

    @GetMapping(value = "/profile/{userid}", produces = {"application/json"})
    public ResponseEntity<?> getProfilebyuserid(@PathVariable long userid) {
        Profile p = profileService.findProfileById(userid);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping(value = "/newprofile", produces = {"application/json"})
    public ResponseEntity<?> createProfile(@Valid Authentication authentication, @RequestBody Profile profile) {
        User newUser = userService.findByUserName(authentication.getName());
        long userid = newUser.getuserid();

        profile = profileService.save(profile, userid);
        newUser.setProfile(profile);
        userService.save(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/profile/update/{userid}", produces = {"application/json"})
    public ResponseEntity<?> updateProfileWithUserId(@Valid @RequestBody Profile profile, @PathVariable long userid) {
        profileService.update(profile, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
