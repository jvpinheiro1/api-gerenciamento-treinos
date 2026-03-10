package com.jvpinheiro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jvpinheiro.api.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
