package com.xjl.controller;

import com.xjl.manager.service.UserService;
import com.xjl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User findUserById(@PathVariable("id")Long id){
        return userService.findUserById(id);
    }
}
