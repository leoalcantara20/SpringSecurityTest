package com.teste.teste.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    protected JwtLoginFilter(String url, AuthenticationManager gerenciadorAutenticacao){
        super (new AntPathRequestMatcher(url));
        setAuthenticationManager(gerenciadorAutenticacao);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp)
            throws AuthenticationException, IOException, ServletException {
        
                Credencial credencial = new ObjectMapper().readValue(req.getInputStream(), Credencial.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(credencial.getNomeUsuario(), credencial.getSenha(),Collections.emptyList())
        );
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain,
            Authentication autenticacao) throws IOException, ServletException {
                GeradorTokenAutenticacao.addAuthentication(resp, autenticacao.getName());
    }    
    
}