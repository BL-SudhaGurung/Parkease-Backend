package com.parkease.auth.security;

import io.jsonwebtoken.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

//    private static String secret = "secretkey";

    public  String extractUsername(String token) {
        return Jwts.parser()

                .setSigningKey(secret)

                .parseClaimsJws(token)

                .getBody()

                .getSubject();
    }


    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(expiration)
                )
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token, String username) {

        try {

            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);

            return true;

        }catch (ExpiredJwtException e) {
//        log.error("Token expired: {}", e.getMessage());
        return false;
    } catch (MalformedJwtException e) {
//        log.error("Malformed token: {}", e.getMessage());
        return false;
    } catch (JwtException e) {
//        log.error("Invalid token: {}", e.getMessage());
        return false;
    }
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(refreshExpiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}



