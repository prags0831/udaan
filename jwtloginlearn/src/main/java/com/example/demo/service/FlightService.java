package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Flight;

import java.util.List;


public interface FlightService {
	Integer saveFlight(Flight f);
	void updateFlight(Flight f);
	void deleteFlight(Integer flight_id);
	Optional<Flight> getOneFlight(Integer flight_id);
	List<Flight> getAllFlight();
	boolean isFlightExist(Integer flight_id);
}
