package com.project.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.product.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
