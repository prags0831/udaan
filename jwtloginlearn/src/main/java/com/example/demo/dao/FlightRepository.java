package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Flight;



public interface FlightRepository extends JpaRepository<Flight, Integer> {

}
