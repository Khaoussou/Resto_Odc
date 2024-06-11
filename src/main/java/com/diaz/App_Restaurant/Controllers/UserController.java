package com.diaz.App_Restaurant.Controllers;

import com.diaz.App_Restaurant.Entities.User;
import com.diaz.App_Restaurant.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "list")
    public List<User> users() {
        return userService.user();
    }

    @PostMapping(path = "add")
    public User addUser(@RequestBody User user) {
        return userService.addnewUser(user);
    }
}
