package com.lambdaschool.starthere.Service;

import com.lambdaschool.starthere.StartHereApplication;
import com.lambdaschool.starthere.models.Profile;
import com.lambdaschool.starthere.services.ProfileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
public class ProfileServiceImplTest {

    @Autowired
    ProfileService profileService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() {
        profileService.save(new Profile("Rushi", "Arumalla", "rushi44@gmail.com"), 5);
        assertEquals("Rushi", profileService.findProfileById(5).getFirst_name());

    }

//    @Test
//    public void update() {
//        profileService.save(new Profile("Sam", "Arumalla", "rushi44@gmail.com"), 6);
//        profileService.update(profileService.findProfileById(6), 5);
//        assertEquals("Bob", profileService.findProfileById(5).getFirst_name());
//
//    }


}
