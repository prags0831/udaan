package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Flight {
	
	@Id
	private Integer flight_id;
	private String source;
	private String destination;
	private String departure_time;
	private String arrival_time;
	private String travel_time;
	private Integer fare;
	
	public Integer getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(Integer flight_id) {
		this.flight_id = flight_id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public String getTravel_time() {
		return travel_time;
	}
	public void setTravel_time(String travel_time) {
		this.travel_time = travel_time;
	}
	public Integer getFare() {
		return fare;
	}
	public void setFare(Integer fare) {
		this.fare = fare;
	}
}
