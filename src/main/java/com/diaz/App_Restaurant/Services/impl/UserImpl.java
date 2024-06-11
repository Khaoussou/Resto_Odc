package com.diaz.App_Restaurant.Services.impl;

import com.diaz.App_Restaurant.Entities.User;
import com.diaz.App_Restaurant.Services.UserService;
import com.diaz.App_Restaurant.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User addnewUser(User user) {
        String passW = user.getPassword();
        user.setPassword(passwordEncoder.encode(passW));
        return userRepository.save(user);
    }

    @Override
    public User findUser(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Cet utilisateur n'existe pas !");
        }
        return user;
    }

    @Override
    public List<User> user() {
        return userRepository.findAll();
    }
}
