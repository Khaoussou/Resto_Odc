package com.diaz.App_Restaurant.Services.impl;

import com.diaz.App_Restaurant.Entities.User;
import com.diaz.App_Restaurant.Services.JwtService;
import com.diaz.App_Restaurant.Services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtImpl implements JwtService {

    public static final String BEARER = "bearer";
    private final String ENCRPYTION_KEY = "f63c7680c5ffabb7231e4824cc50b456b7aba107e8dae55d094cb47a3c4049d4";

    @Autowired
    private UserService userService;

    @Override
    public Map<String, String> generate(String username) {
        User userConnect = userService.findUser(username);
        Map<String, String> jwtMap = this.generateJwt(userConnect);

        return jwtMap;
    }

    @Override
    public Map<String, String> generateJwt(User user) {

        long currentTime = System.currentTimeMillis();
        long expTime = currentTime + 10 * 60 * 1000;

        Map<String, ? extends Serializable> claims = Map.of(
                "nom", user.getNom(),
                "role", user.getRole(),
                Claims.EXPIRATION, new Date(expTime),
                Claims.SUBJECT, user.getEmail()
        );

        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expTime))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of(BEARER, bearer);
    }

    @Override
    public String readUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Date expirDate = this.getClaim(token, Claims::getExpiration);
        return expirDate.before(new Date());
    }

    @Override
    public void deconnexion() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRPYTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }
}
