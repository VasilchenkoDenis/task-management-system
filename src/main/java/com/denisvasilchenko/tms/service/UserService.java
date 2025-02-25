package com.denisvasilchenko.tms.service;

import com.denisvasilchenko.tms.model.User;
import com.denisvasilchenko.tms.model.UserRole;
import com.denisvasilchenko.tms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

//    public List<User> findAll() {
//        return userRepository.findAll();
//    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User create (User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: "+username+" not found"));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email: "+email+" not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }


    public User getCurrentUser() {
        var userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByEmail(userEmail);
    }

    @Deprecated
    public User getAdmin (){
        var user = getCurrentUser();
        Set<UserRole> userRoles = user.getRoles();
        userRoles.add(UserRole.ROLE_ADMIN);
        user.setRoles(userRoles);
        return save(user);
    }
}
