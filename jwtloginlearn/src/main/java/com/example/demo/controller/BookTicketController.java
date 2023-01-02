package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookTicketRepository;
import com.example.demo.entity.BookTicket;
import com.example.demo.service.BookTicketService;
import com.example.demo.util.BookTicketUtil;



@RestController
@RequestMapping("/rest/bookticket")
@CrossOrigin
public class BookTicketController {
	private Logger log = LoggerFactory.getLogger(BookTicketController.class);

	@Autowired
	private BookTicketService service;
	@Autowired
	private BookTicketUtil util;
	@Autowired
	private BookTicketRepository feed;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveBookTicket(
			@RequestBody BookTicket bookTicket)
	{
		log.info("Entered into method with BookTicket data to save");

		ResponseEntity<String> resp = null;
		try {

			log.info("About to call save Operation");

			String email = service.saveBookTicket(bookTicket);
			log.debug("BookTicket saved with id "+email);

			String body = "BookTicket '"+email+"' added successfully";

			resp =  new ResponseEntity<String>(body, HttpStatus.CREATED); //201

			log.info("Sucess response constructed");
		} catch (Exception e) {
			log.error("Unable to save BookTicket : problem is :"+e.getMessage());
			resp =  new ResponseEntity<String>(
					"Unable to Create BookTicket", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}

		log.info("About to Exist save method with Response");
		return resp;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllBookTicket() {
		log.info("Entered into method to fetch BookTicket data");
		ResponseEntity<?> resp = null ;
		try {

			log.info("About to call fetch BookTicket service");
			List<BookTicket> list = service.getAllBookTicket();
			if(list!=null && !list.isEmpty()) {
				log.info("Data is not empty=>"+list.size());
				list.sort((s1,s2)->s1.getEmail().compareTo(s2.getEmail()));
				/* JDK 1.8
				list = list.stream()
						.sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
						.collect(Collectors.toList());
				 */
				resp = new ResponseEntity<List<BookTicket>>(list, HttpStatus.OK);
			} else {
				log.info("No BookTicket exist: size "+list.size());

				//resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				resp = new ResponseEntity<String>(
						"No BookTicket Found",
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch BookTicket : problem is :"+e.getMessage());

			resp =  new ResponseEntity<String>(
					"Unable to Fetch BookTicket", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
		log.info("About to Exist fetch all method with Response");
		return resp;
	}
	
	
	@GetMapping("/one/{email}")
	public ResponseEntity<?> getOneBookTicket(
			@PathVariable String email
			) 
	{
		log.info("Entered into Get one BookTicket method");
		ResponseEntity<?> resp = null;
		try {
			log.info("About to make service call to fetch one record");
			Optional<BookTicket> opt =  service.getOneBookTicket(email);
			if(opt.isPresent()) {
				log.info("BookTicket exist=>"+email);
				resp = new ResponseEntity<BookTicket>(opt.get(), HttpStatus.OK);
				//resp = ResponseEntity.ok(opt.get());
			} else {
				log.warn("Given BookTicket id not exist=>"+email);
				resp = new ResponseEntity<String>(
						"BookTicket '"+email+"' not exist", 
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to process request fetch " + e.getMessage());
			resp = new ResponseEntity<String>(
					"Unable to process BookTicket fetch", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}
	
	@DeleteMapping("/remove/{email}")
	public ResponseEntity<String> removeBookTicket(
			@PathVariable String email
			)
	{

		log.info("Entered into delete method");
		ResponseEntity<String> resp = null;

		try {

			log.info("About to make service call for data check");
			boolean exist = service.isBookTicketExist(email);
			if(exist) {
				service.deleteBookTicket(email);
				log.info("BookTicket exist with given id and deleted=>"+email);
				resp = new ResponseEntity<String>(
						"Flight '"+email+"' deleted",
						HttpStatus.OK);
			} else {
				log.warn("Given BookTicket id not exist =>"+email);
				resp = new ResponseEntity<String>(
						"BookTicket '"+email+"' not exist",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to perform Delete Operation =>" + e.getMessage());
			resp = new ResponseEntity<String>(
					"Unable to delete", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}

	//UPDATE METHOD
	@PutMapping("/modify/{email}")
	public ResponseEntity<String> updateBookTicket(
			@PathVariable String email,
			@RequestBody BookTicket bookTicket
			)
	{
		log.info("Entered into Update method");

		ResponseEntity<String> resp =null;

		try {
			log.info("About to check given id exist or not db");
			Optional<BookTicket> opt =  service.getOneBookTicket(email);
			if(opt.isPresent()) {
				log.info("Flight present in database");
				BookTicket actual = opt.get();
				util.mapToActualObject(actual,bookTicket);
				service.updateBookTicket(actual);
				resp = new ResponseEntity<String>(
						"BookTicket '"+email+"' Updated", 
						//HttpStatus.RESET_CONTENT
						HttpStatus.OK
						);
				log.info("BookTicket update done successfully");
			} else {
				log.info("BookTicket not exist=>"+email);
				resp = new ResponseEntity<String>(
						"BookTicket '"+email+"' not found", 
						//HttpStatus.RESET_CONTENT
						HttpStatus.BAD_REQUEST
						);
			}

		} catch (Exception e) {
			log.error("Unable to perform Update Operation=>" + e.getMessage() );
			resp = new ResponseEntity<String>(
					"Unable to process Update",
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
}


//SELECT * from flight inner join book_ticket on flight.flight_id = book_ticket.flight_id 
