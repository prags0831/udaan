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

import com.example.demo.dao.PaymentRepository;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import com.example.demo.util.PaymentUtil;

@RestController
@RequestMapping("/rest/payment")
@CrossOrigin("*")
public class PaymentController {

	private Logger log = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService service;
	@Autowired
	private PaymentUtil util;
	@Autowired
	private PaymentRepository feed;
	
	@PostMapping("/save")
	public ResponseEntity<String> savePayment(
			@RequestBody Payment payment)
	{
		log.info("Entered into method with Payment data to save");

		ResponseEntity<String> resp = null;
		try {

			log.info("About to call save Operation");

			String card_num = service.savePayment(payment);
			log.debug("Payment saved with id "+card_num);

			String body = "Payment '"+card_num+"' added successfully";

			resp =  new ResponseEntity<String>(body, HttpStatus.CREATED); //201

			log.info("Sucess response constructed");
		} catch (Exception e) {
			log.error("Unable to save Payment : problem is :"+e.getMessage());
			resp =  new ResponseEntity<String>(
					"Unable to Create Payment", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}

		log.info("About to Exist save method with Response");
		return resp;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllPayment() {
		log.info("Entered into method to fetch Payment data");
		ResponseEntity<?> resp = null ;
		try {

			log.info("About to call fetch Payment service");
			List<Payment> list = service.getAllPayment();
			if(list!=null && !list.isEmpty()) {
				log.info("Data is not empty=>"+list.size());
				list.sort((s1,s2)->s1.getName().compareTo(s2.getName()));
				/* JDK 1.8
				list = list.stream()
						.sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
						.collect(Collectors.toList());
				 */
				resp = new ResponseEntity<List<Payment>>(list, HttpStatus.OK);
			} else {
				log.info("No Payment exist: size "+list.size());

				//resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				resp = new ResponseEntity<String>(
						"No Payment Found",
						HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Unable to fetch Payment : problem is :"+e.getMessage());

			resp =  new ResponseEntity<String>(
					"Unable to Payment Flight", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
		log.info("About to Exist fetch all method with Response");
		return resp;
	}
	
	
	@GetMapping("/one/{card_num}")
	public ResponseEntity<?> getOnePayment(
			@PathVariable String card_num
			) 
	{
		log.info("Entered into Get one Payment method");
		ResponseEntity<?> resp = null;
		try {
			log.info("About to make service call to fetch one record");
			Optional<Payment> opt =  service.getOnePayment(card_num);
			if(opt.isPresent()) {
				log.info("Payment exist=>"+card_num);
				resp = new ResponseEntity<Payment>(opt.get(), HttpStatus.OK);
				//resp = ResponseEntity.ok(opt.get());
			} else {
				log.warn("Given Payment id not exist=>"+card_num);
				resp = new ResponseEntity<String>(
						"Flight '"+card_num+"' not exist", 
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Unable to process request fetch " + e.getMessage());
			resp = new ResponseEntity<String>(
					"Unable to process Payment fetch", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}
	
	@DeleteMapping("/remove/{card_num}")
	public ResponseEntity<String> removePayment(
			@PathVariable String card_num
			)
	{

		log.info("Entered into delete method");
		ResponseEntity<String> resp = null;

		try {

			log.info("About to make service call for data check");
			boolean exist = service.isPaymentExist(card_num);
			if(exist) {
				service.deletePayment(card_num);
				log.info("Payment exist with given id and deleted=>"+card_num);
				resp = new ResponseEntity<String>(
						"Payment '"+card_num+"' deleted",
						HttpStatus.OK);
			} else {
				log.warn("Given Payment id not exist =>"+card_num);
				resp = new ResponseEntity<String>(
						"Payment '"+card_num+"' not exist",
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
	
	
	@PutMapping("/modify/{flight_id}")
	public ResponseEntity<String> updatePayment(
			@PathVariable String card_num,
			@RequestBody Payment payment
			)
	{
		log.info("Entered into Update method");

		ResponseEntity<String> resp =null;

		try {
			log.info("About to check given id exist or not db");
			Optional<Payment> opt =  service.getOnePayment(card_num);
			if(opt.isPresent()) {
				log.info("Flight present in database");
				Payment actual = opt.get();
				util.mapToActualObject(actual,payment);
				service.updatePayment(actual);
				resp = new ResponseEntity<String>(
						"Payment '"+card_num+"' Updated", 
						//HttpStatus.RESET_CONTENT
						HttpStatus.OK
						);
				log.info("Payment update done successfully");
			} else {
				log.info("Payment not exist=>"+card_num);
				resp = new ResponseEntity<String>(
						"Payment '"+card_num+"' not found", 
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