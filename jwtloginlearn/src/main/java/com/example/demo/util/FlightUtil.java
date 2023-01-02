package com.example.demo.util;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Flight;


@Component
public class FlightUtil {
public void mapToActualObject(Flight actual, Flight FlightService) {
		
		actual.setFlight_id(FlightService.getFlight_id());
		actual.setSource(FlightService.getSource());
		actual.setDestination(FlightService.getDestination());
		actual.setDeparture_time(FlightService.getDeparture_time());
		actual.setArrival_time(FlightService.getArrival_time());
		actual.setTravel_time(FlightService.getTravel_time());
		actual.setFare(FlightService.getFare());
	}
}
