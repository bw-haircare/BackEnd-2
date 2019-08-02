package com.lambdaschool.starthere.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.starthere.models.Profile;
import com.lambdaschool.starthere.models.Role;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.UserRoles;
import com.lambdaschool.starthere.services.ProfileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProfileController.class, secure = false)
class ProfileControllerTest {

    @MockBean
    private ProfileService profileService;

    @Autowired
    private MockMvc mockMvc;

    private ArrayList<User> usersList = new ArrayList<>();
    private ArrayList<Profile> profileList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");


        Profile p1 = new Profile("Rushi", "Arumalla", "rushi44@gmail.com");


        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));

        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password",  admins);

//        u1.setProfile(p1);
        u1.setuserid(1);
        usersList.add(u1);
        p1.setProfileid(1);
        profileList.add(p1); 

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createProfile() throws Exception {
        String apiUrl = "/profiles/newprofile";
        Mockito.when(profileService.save((new Profile("Rushi", "A", "haghsg@hgj.com")), 1)).thenReturn(profileList.get(2));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(profileList.get(1));

        assertEquals("RestAPI Returns List", er, tr);



    }
}