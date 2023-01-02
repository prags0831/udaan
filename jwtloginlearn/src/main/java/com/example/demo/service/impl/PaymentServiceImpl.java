package com.example.demo.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PaymentRepository;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;



@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository repo;
	
	@Override
	public String savePayment(Payment f) {
		f = repo.save(f);
		return f.getCard_num();
	}

	@Override
	public void updatePayment(Payment f) {
		repo.save(f);
	}

	@Override
	public void deletePayment(String card_num) {
		repo.deleteById(card_num);
	}

	@Override
	public Optional<Payment> getOnePayment(String card_num) {
		return repo.findById(card_num);
	}

	@Override
	public List<Payment> getAllPayment() {
		return repo.findAll();
	}

	@Override
	public boolean isPaymentExist(String card_num) {
		return repo.existsById(card_num);
	}
}