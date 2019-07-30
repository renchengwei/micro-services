package com.xgg.microservices.spring.configs;

import com.xgg.microservices.spring.security.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login.html")
                .loginProcessingUrl("/user/login").successForwardUrl("/user/login")
                .and()
                .authorizeRequests().antMatchers("/login.html","/user/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().and().csrf().disable();

        /*http
                .authorizeRequests()
                .anyRequest().authenticated().antMatchers("/user/login").permitAll()
                .and()
                .formLogin().loginPage("/user/login").loginProcessingUrl("/user/loginProcess").and()
                .httpBasic().and()
                .sessionManagement()
                .maximumSessions(1).maxSessionsPreventsLogin(true)
                .expiredUrl("/user/login").and()
                .invalidSessionUrl("/user/login");*/
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService securityUserDetailsService() {
        return new SecurityUserDetailsService();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
