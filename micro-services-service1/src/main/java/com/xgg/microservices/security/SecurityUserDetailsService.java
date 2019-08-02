package com.xgg.microservices.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
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
