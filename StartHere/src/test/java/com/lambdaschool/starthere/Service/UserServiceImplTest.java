package com.lambdaschool.starthere.Service;


import com.lambdaschool.starthere.StartHereApplication;
import com.lambdaschool.starthere.models.Role;
import com.lambdaschool.starthere.models.User;
import com.lambdaschool.starthere.models.UserRoles;
import com.lambdaschool.starthere.services.RoleService;
import com.lambdaschool.starthere.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserById() {
        assertEquals(4, userService.findUserById(4).getuserid());
    }

    @Test
    public void findAllUsers() {
        assertEquals(5, userService.findAll().size());
    }

    @Test
    public void save() {
        Role r3 = new Role("data");
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        User u2 = new User("cinnamon", "1234567", datas);
        assertEquals("cinnamon", userService.findByUserName("cinnamon").getUsername());
    }

}
