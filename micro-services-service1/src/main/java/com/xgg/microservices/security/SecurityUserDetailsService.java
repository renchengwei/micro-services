package com.xgg.microservices.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String encode = passwordEncoder.encode("123456");
        Collection<GrantedAuthority> authorities = getAuthority();
        SecurityUserDeatils securityUserDeatils = new SecurityUserDeatils();
        securityUserDeatils.setUserName("user1");
        securityUserDeatils.setPassword(encode);
        securityUserDeatils.setAuthorities(authorities);
        return securityUserDeatils;
    }

    private Collection<GrantedAuthority> getAuthority() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("create_user"));
        authorities.add(new SimpleGrantedAuthority("delete_user"));
        return authorities;
    }
}
