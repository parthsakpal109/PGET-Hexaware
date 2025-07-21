package com.hexaware.web.apichallange.Security;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
    private static final String SECRET_KEY = "absgdhjagbdbaudshfgshdjdgfjhfgsdgfdsgjgfudsgf";
    private static final int TOKEN_VALIDITY = 5 * 60 * 60;

    
    public String generateToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    
    public String extractUserName(String token) {
        return getClaims(token).getSubject();
    }

    
    public Boolean isTokenValid(String token, String username) {
        String extractedUsername = extractUserName(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
