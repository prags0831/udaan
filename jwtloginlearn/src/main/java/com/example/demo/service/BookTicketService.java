package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.BookTicket;



public interface BookTicketService {
	String saveBookTicket(BookTicket b);
	void updateBookTicket(BookTicket b);
	void deleteBookTicket(String email);
	Optional<BookTicket> getOneBookTicket(String email);
	List<BookTicket> getAllBookTicket();
	boolean isBookTicketExist(String email);
}
