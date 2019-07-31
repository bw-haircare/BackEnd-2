package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Profile;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.repository.ProfileRepository;
import com.lambdaschool.starthere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "profileService")
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileRepository profileRepos;

    @Autowired
    private UserRepository userrrepos;

    @Override
    public ArrayList<Profile> findAll() {
        ArrayList<Profile> list = new ArrayList<>();
        profileRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Profile findProfileById(long userid) {
        User newUser = userrrepos.findById(userid).orElseThrow(() -> new EntityNotFoundException(Long.toString(userid)));
        long profileid = newUser.getProfile().getProfileid();
        return profileRepos.findById(profileid).orElseThrow(() -> new EntityNotFoundException(Long.toString(profileid))); 
    }

    @Override
    public Profile save(Profile profile, long userid) {
        return null;
    }

    @Override
    public Profile update(Profile profile, long userid) {
        return null;
    }
}
