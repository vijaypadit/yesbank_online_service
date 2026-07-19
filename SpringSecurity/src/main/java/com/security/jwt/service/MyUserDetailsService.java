package com.security.jwt.service;

import com.security.jwt.enity.AuthUser;
import com.security.jwt.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new MyUserDetails(authUser);
    }
}
