package com.teste.teste.config;

import java.sql.Date;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class GeradorTokenAutenticacao {

    static final long TEMPO_EXPIRACAO = 3600000; //1 HORA
    static final String SECRET = "brasilia2020";
    static final String PREFIXO_TOKEN = "Bearer";
    static final String HEADER_STRING = "Authorization";

    static void addAuthentication(HttpServletResponse resp, String userName){
        String jwt = Jwts.builder()
                        .setSubject(userName)
                        .setExpiration(new Date(System.currentTimeMillis()+TEMPO_EXPIRACAO))
                        .signWith(SignatureAlgorithm.HS512, SECRET)
                        .compact();
        resp.addHeader(HEADER_STRING, PREFIXO_TOKEN+" "+jwt);
    }

    static Authentication getAuthentication(HttpServletRequest req){
        String token = req.getHeader(HEADER_STRING);
        if(token!=null){
            String user = Jwts.parser()
                            .setSigningKey(SECRET)            
                            .parseClaimsJws(token.replace(PREFIXO_TOKEN, ""))
                            .getBody()
                            .getSubject();
            if(user!=null){
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }
        return null;
    }
    
}