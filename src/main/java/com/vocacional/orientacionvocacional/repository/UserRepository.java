package com.vocacional.orientacionvocacional.repository;

import com.vocacional.orientacionvocacional.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}