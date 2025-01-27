package com.denisvasilchenko.tms.security.service;

import com.denisvasilchenko.tms.security.dto.JwtAuthenticationResponse;
import com.denisvasilchenko.tms.security.dto.SignInRequest;
import com.denisvasilchenko.tms.security.dto.SignUpRequest;
import com.denisvasilchenko.tms.model.User;
import com.denisvasilchenko.tms.model.UserRole;
import com.denisvasilchenko.tms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(UserRole.ROLE_USER);

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(userRoles)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }

        System.out.println("auth service");
        var user = userService.userDetailsService()
                .loadUserByUsername(request.getEmail());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
