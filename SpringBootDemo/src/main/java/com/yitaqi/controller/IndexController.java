package com.yitaqi.controller;


import com.yitaqi.pojo.User;
import com.yitaqi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xue
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    @ResponseBody
    public Map<String, String> index() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("zhangsan", "123");
        return map;
    }

    @GetMapping("/show")
    public List<User> getUser(int age) {

        return userService.getUser(age);
    }

}
