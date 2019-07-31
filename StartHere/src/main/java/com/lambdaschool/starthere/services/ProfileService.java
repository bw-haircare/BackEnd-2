package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Profile;

import java.util.ArrayList;

public interface ProfileService {

    ArrayList<Profile> findAll();

    Profile findProfileById(long userid);

    Profile save(Profile profile, long userid);

    Profile update(Profile profile, long userid);

}
