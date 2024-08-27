package com.techlab.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.techlab.company.service.UserSerive;
import com.techlab.company.entity.User;

import java.util.List;

@RestController
@RequestMapping("/companyapp")
public class UserController {

    @Autowired
    private UserSerive userService;

    
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

   
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int userId) {
        return userService.getUserById(userId);
    }

   
    @PostMapping("/user/{id}/roles")
    public User assignRoleToUser(@PathVariable("id") int userId, @RequestBody List<Integer> roleIds) {
        return userService.assignRoleToUser(userId, roleIds);
    }
}
