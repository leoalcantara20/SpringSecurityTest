package com.teste.teste.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAutenticacaoFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
     
                Authentication autenticacao = GeradorTokenAutenticacao.getAuthentication((HttpServletRequest)request);

                SecurityContextHolder.getContext().setAuthentication(autenticacao);

                chain.doFilter(request, response);
    }
    
}