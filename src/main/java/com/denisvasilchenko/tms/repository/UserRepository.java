package com.denisvasilchenko.tms.repository;

import com.denisvasilchenko.tms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
