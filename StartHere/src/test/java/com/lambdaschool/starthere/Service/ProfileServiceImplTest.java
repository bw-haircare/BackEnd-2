package com.lambdaschool.starthere.Service;

import com.lambdaschool.starthere.StartHereApplication;
import com.lambdaschool.starthere.services.ProfileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
public class ProfileServiceImplTest {

    @Autowired
    ProfileService profileService;

    @Before

    @Test
    public void save() {

    }


}
