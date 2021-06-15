package com.ctfcervice.authentication.services;

import com.ctfcervice.authentication.models.Users;
import com.ctfcervice.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<Users> getAll() {

        return userRepository.findAll();

    }
}
