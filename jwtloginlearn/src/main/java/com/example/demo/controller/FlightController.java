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

import com.example.demo.dao.FlightRepository;
import com.example.demo.entity.Flight;
import com.example.demo.service.FlightService;
import com.example.demo.util.FlightUtil;



@RestController
@RequestMapping("/rest/flight")
@CrossOrigin
public class FlightController {
	
	private Logger log = LoggerFactory.getLogger(FlightController.class);

	@Autowired
	private FlightService service;
	@Autowired
	private FlightUtil util;
	@Autowired
	private FlightRepository feed;
	
	//SAVE METHOD
@PostMapping("/save")
public ResponseEntity<String> saveFlight(
		@RequestBody Flight flight)
{
	log.info("Entered into method with Flight data to save");

	ResponseEntity<String> resp = null;
	try {

		log.info("About to call save Operation");

		Integer flight_id = service.saveFlight(flight);
		log.debug("Flight saved with id "+flight_id);

		String body = "Flight '"+flight_id+"' added successfully";

		resp =  new ResponseEntity<String>(body, HttpStatus.CREATED); //201

		log.info("Sucess response constructed");
	} catch (Exception e) {
		log.error("Unable to save Flight : problem is :"+e.getMessage());
		resp =  new ResponseEntity<String>(
				"Unable to Create Flight", 
				HttpStatus.INTERNAL_SERVER_ERROR); //500
		e.printStackTrace();
	}

	log.info("About to Exist save method with Response");
	return resp;
}

//GET ALL METHODS
@GetMapping("/all")
public ResponseEntity<?> getAllFlight() {
	log.info("Entered into method to fetch Flight data");
	ResponseEntity<?> resp = null ;
	try {

		log.info("About to call fetch Flight service");
		List<Flight> list = service.getAllFlight();
		if(list!=null && !list.isEmpty()) {
			log.info("Data is not empty=>"+list.size());
			list.sort((s1,s2)->s1.getFlight_id().compareTo(s2.getFlight_id()));
			/* JDK 1.8
			list = list.stream()
					.sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
					.collect(Collectors.toList());
			 */
			resp = new ResponseEntity<List<Flight>>(list, HttpStatus.OK);
		} else {
			log.info("No Flight exist: size "+list.size());

			//resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			resp = new ResponseEntity<String>(
					"No Flight Found",
					HttpStatus.OK);
		}
	} catch (Exception e) {
		log.error("Unable to fetch Flight : problem is :"+e.getMessage());

		resp =  new ResponseEntity<String>(
				"Unable to Fetch Flight", 
				HttpStatus.INTERNAL_SERVER_ERROR); //500
		e.printStackTrace();
	}
	log.info("About to Exist fetch all method with Response");
	return resp;
}

//GET ONE METHOD
@GetMapping("/one/{flight_id}")
public ResponseEntity<?> getOneFlight(
		@PathVariable Integer flight_id
		) 
{
	log.info("Entered into Get one Flight method");
	ResponseEntity<?> resp = null;
	try {
		log.info("About to make service call to fetch one record");
		Optional<Flight> opt =  service.getOneFlight(flight_id);
		if(opt.isPresent()) {
			log.info("Flight exist=>"+flight_id);
			resp = new ResponseEntity<Flight>(opt.get(), HttpStatus.OK);
			//resp = ResponseEntity.ok(opt.get());
		} else {
			log.warn("Given Flight id not exist=>"+flight_id);
			resp = new ResponseEntity<String>(
					"Flight '"+flight_id+"' not exist", 
					HttpStatus.BAD_REQUEST);
		}
	} catch (Exception e) {
		log.error("Unable to process request fetch " + e.getMessage());
		resp = new ResponseEntity<String>(
				"Unable to process Flight fetch", 
				HttpStatus.INTERNAL_SERVER_ERROR);
		e.printStackTrace();
	}

	return resp;
}

//DELETE METHODS
@DeleteMapping("/remove/{flight_id}")
public ResponseEntity<String> removeFlight(
		@PathVariable Integer flight_id
		)
{

	log.info("Entered into delete method");
	ResponseEntity<String> resp = null;

	try {

		log.info("About to make service call for data check");
		boolean exist = service.isFlightExist(flight_id);
		if(exist) {
			service.deleteFlight(flight_id);
			log.info("Flight exist with given id and deleted=>"+flight_id);
			resp = new ResponseEntity<String>(
					"Flight '"+flight_id+"' deleted",
					HttpStatus.OK);
		} else {
			log.warn("Given Flight id not exist =>"+flight_id);
			resp = new ResponseEntity<String>(
					"Flight '"+flight_id+"' not exist",
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
@PutMapping("/modify/{flight_id}")
public ResponseEntity<String> updateFlight(
		@PathVariable Integer flight_id,
		@RequestBody Flight flight
		)
{
	log.info("Entered into Update method");

	ResponseEntity<String> resp =null;

	try {
		log.info("About to check given id exist or not db");
		Optional<Flight> opt =  service.getOneFlight(flight_id);
		if(opt.isPresent()) {
			log.info("Flight present in database");
			Flight actual = opt.get();
			util.mapToActualObject(actual,flight);
			service.updateFlight(actual);
			resp = new ResponseEntity<String>(
					"Flight '"+flight_id+"' Updated", 
					//HttpStatus.RESET_CONTENT
					HttpStatus.OK
					);
			log.info("Flight update done successfully");
		} else {
			log.info("Flight not exist=>"+flight_id);
			resp = new ResponseEntity<String>(
					"Flight '"+flight_id+"' not found", 
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
