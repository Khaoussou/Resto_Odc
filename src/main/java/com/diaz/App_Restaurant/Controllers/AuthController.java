package com.diaz.App_Restaurant.Controllers;

import com.diaz.App_Restaurant.Dto.UserDto;
import com.diaz.App_Restaurant.Entities.User;
import com.diaz.App_Restaurant.Services.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "login")
    public Map<String, String> connect(@RequestBody UserDto userDto) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password())
        );

        if (authenticate.isAuthenticated()) {
            return this.jwtService.generate(userDto.email());
        } else {
            log.info("Connexion impossible !");
        }
        return null;
    }

    @PostMapping(path = "logout")
    public void deconnexion() {
        jwtService.deconnexion();
    }
}
