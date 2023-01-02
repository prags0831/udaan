package com.example.demo.util;

import org.springframework.stereotype.Component;

import com.example.demo.entity.BookTicket;



@Component
public class BookTicketUtil {
	public void mapToActualObject(BookTicket actual, BookTicket BookTicketService) {

		actual.setFlight_id(BookTicketService.getFlight_id());
		actual.setName(BookTicketService.getName());
		actual.setAge(BookTicketService.getAge());
		actual.setGender(BookTicketService.getGender());
		actual.setPhone(BookTicketService.getPhone());
		actual.setEmail(BookTicketService.getEmail());
		actual.setFood(BookTicketService.getFood());
	}
}
