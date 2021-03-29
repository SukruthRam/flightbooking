package com.flightbooking.flight.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flightbooking.flight.entity.Flight;

public interface FlightService {

	Flight addFlight(Flight flight);
	
	ResponseEntity<Flight> deleteFlight(Flight flight);
	
	Flight updateFlight(Flight flight);
	
	Flight getFlightbyid(String id);
	
	Flight getFlightbydetails(String id, String src, String dest);
	
	Result getFlights(String airline, String src, String dest);
	
	List<Flight> getFlights();
	
	Result getResult(HashMap<String, Integer> flightMap, String src);
}
