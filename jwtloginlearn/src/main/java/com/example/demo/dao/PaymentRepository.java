package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Payment;

public interface PaymentRepository  extends JpaRepository<Payment, String>{

}
