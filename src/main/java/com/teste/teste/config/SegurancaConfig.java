package com.teste.teste.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()       
            .authorizeRequests().antMatchers("/","/csrf","/login","/obterToken.html","/favicon.ico").permitAll()           
            // .and()            
            // .authorizeRequests()
                .anyRequest().authenticated() 
            // .and()
            // .httpBasic()
            .and()
            .addFilterBefore(new JwtLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JwtAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs"
                , "/configuration/ui"
                , "/swagger-resources/**"
                , "/configuration/**"
                , "/swagger-ui.html"
                , "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {        
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("123456")).roles("USER");


        // auth.ldapAuthentication()
        //     .contextSource().url("ldap://algo.com:33389/dc=algo,dc=com")
        //     .userSearchBase("ou=people")
        //     .userSearchFilter("uid={0}")
        //     .groupSearchBase("ou=groups")
        //     .groupSearchFilter("member={0}")
        //     .passwordCompare()
        //     .passwordEncoder(new BCryptPasswordEncoder());            

    }
}