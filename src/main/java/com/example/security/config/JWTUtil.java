package com.example.security.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private final String SECRET="dslwe9-elfw2-slvjlskf-23rfds-ffls";
    private SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());
   private final long EXPIRATION_TIME=1000*60*60;
    public String generateToken(String username){
      return  Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
