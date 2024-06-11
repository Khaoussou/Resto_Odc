package com.diaz.App_Restaurant.Services;

import com.diaz.App_Restaurant.Entities.User;

import java.util.List;

public interface UserService {
    User addnewUser(User user);

    User findUser(String email);

    List<User> user();
}
