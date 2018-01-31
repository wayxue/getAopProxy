package com.yitaqi.service;

import com.yitaqi.SpringBootDemo.SpringBootDemoApplication;
import com.yitaqi.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class UserServiceImplTest {
    @Test
    public void father2() throws Exception {

        userService.father2();
    }

    @Test
    public void child() throws Exception {

        userService.child();
    }

    @Test
    public void father() throws Exception {

        userService.father();
    }

    @Autowired
    private UserService userService;

    @Test
    public void getUserTest(){

        List<User> users = userService.getUser(123);
        System.out.println(users.get(0).getName());
    }

    @Test
    public void saveTest() {

        userService.save("wangbaobao", 12);
    }

    @Test
    public void father3Test() {
        userService.father3();
    }

}