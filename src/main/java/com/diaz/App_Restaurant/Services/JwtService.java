package com.diaz.App_Restaurant.Services;

import com.diaz.App_Restaurant.Entities.User;

import java.util.Map;

public interface JwtService {

    Map<String, String> generate(String username);

    Map<String, String> generateJwt(User user);

    String readUsername(String token);

    boolean isTokenExpired(String token);

    void deconnexion();
}
