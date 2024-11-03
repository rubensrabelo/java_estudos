package com.project.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.product.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
