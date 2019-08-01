package com.lambdaschool.starthere.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.starthere.models.Profile;
import com.lambdaschool.starthere.models.Role;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.UserRoles;
import com.lambdaschool.starthere.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    private List<User> users;
    @BeforeEach
    void setUp() throws Exception {
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
        User u2 = new User("bang", "bang", admins);
        u1.setuserid(1);
        u1.setProfile(p1);
        u2.setuserid(2);
        u2.setProfile(p1);
        users.add(u1);
        users.add(u2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listAllUsers() throws Exception {
        String apiUrl = "/users/users";

        Mockito.when(userService.findAll()).thenReturn(users);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(users);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    void addNewUser() {
    }
}