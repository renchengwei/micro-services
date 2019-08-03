package com.xgg.microservices.security.config;

import com.xgg.microservices.security.captcha.CaptchaFilter;
import com.xgg.microservices.security.handler.XggAuthenticationFailureHandler;
import com.xgg.microservices.security.handler.XggAuthenticationSuccessHandler;
import com.xgg.microservices.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 *  @author: renchengwei
 *  @Date: 2019-08-03
 *  @Description: 浏览器安全配置
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private XggAuthenticationFailureHandler xggAuthenticationFailureHandler;
    @Autowired
    private XggAuthenticationSuccessHandler xggAuthenticationSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CaptchaFilter captchaFilter = new CaptchaFilter();
        captchaFilter.setFailureHandler(xggAuthenticationFailureHandler);
        captchaFilter.setSecurityProperties(securityProperties);
        captchaFilter.afterPropertiesSet();

        http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin()
                .successHandler(xggAuthenticationSuccessHandler)
                .failureHandler(xggAuthenticationFailureHandler)
                .loginPage(securityProperties.getBrowser().getLoginPage())
                .loginProcessingUrl(securityProperties.getBrowser().getLoginProcessingUrl())
                .successForwardUrl(securityProperties.getBrowser().getSuccessForwardUrl())
                .failureForwardUrl(securityProperties.getBrowser().getFailureForwardUrl())
                .and()
                .authorizeRequests().antMatchers(securityProperties.getBrowser().getLoginPage(),
//                    securityProperties.getBrowser().getLoginProcessingUrl(),
                    securityProperties.getBrowser().getRequireUrl(),
                    "/captcha/image")
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

}
