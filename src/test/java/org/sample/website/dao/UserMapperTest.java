package org.sample.website.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.website.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void saveUser() throws Exception {
        User user = new User("123", "123");
        userMapper.saveUser(user);
        assertNull(user.getId());
        user = new User("" + Math.random(), "123");
        userMapper.saveUser(user);
        assertNotNull(user.getId());
    }

    @Test
    public void getUser() throws Exception {
        assertNotNull(userMapper.getUser("123", "123"));
    }

    @Test
    public void getById() throws Exception {
        assertNotNull(userMapper.getById(1000L));
    }

}