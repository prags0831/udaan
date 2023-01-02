package com.example.demo.util;


import org.springframework.stereotype.Component;

import com.example.demo.entity.Payment;


@Component
public class PaymentUtil {
	public void mapToActualObject(Payment actual, Payment PaymentService) {

		actual.setCard(PaymentService.getCard());
		actual.setAmount(PaymentService.getAmount());
		actual.setName(PaymentService.getName());
		actual.setCard_num(PaymentService.getCard_num());
		actual.setExp_date(PaymentService.getExp_date());
		actual.setCvv(PaymentService.getCvv());
		actual.setPnrverified(PaymentService.getPnrverified());
		actual.setFoodamount(PaymentService.getFoodamount());
	}
}