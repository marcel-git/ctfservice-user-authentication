package com.ctfcervice.authentication.security.services;

import com.ctfcervice.authentication.models.Users;
import com.ctfcervice.authentication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException("Users Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
