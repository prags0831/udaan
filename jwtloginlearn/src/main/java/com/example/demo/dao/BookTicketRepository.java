package com.example.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BookTicket;

public interface BookTicketRepository extends JpaRepository<BookTicket, String>{
}
