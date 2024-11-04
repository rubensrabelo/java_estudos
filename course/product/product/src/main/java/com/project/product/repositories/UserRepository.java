package com.project.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.product.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
