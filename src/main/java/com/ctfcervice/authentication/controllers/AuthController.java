package com.ctfcervice.authentication.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ctfcervice.authentication.repository.UserRepository;
import javax.validation.Valid;

import com.ctfcervice.authentication.models.Users;
import com.ctfcervice.authentication.payload.JwtResponse;
import com.ctfcervice.authentication.payload.LoginRequest;
import com.ctfcervice.authentication.payload.MessageResponse;
import com.ctfcervice.authentication.payload.SignupRequest;
import com.ctfcervice.authentication.security.services.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        //create an authentication instance for the requested User
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        Long now = System.currentTimeMillis();

        //get user roles
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        //create JWT
        //TODO remove hardcode
        String token = Jwts.builder().setSubject(
                authentication.getName())
                .claim("authorities",roles)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now +  3600000 ))
                .signWith(SignatureAlgorithm.HS512, "secret".getBytes())
                .compact();

        return ResponseEntity.ok(new JwtResponse(token,userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),roles.contains("admin")));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByMail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Users user = new Users(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Users registered successfully!"));
    }
}
