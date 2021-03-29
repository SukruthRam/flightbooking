package com.flightbooking.flight.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightbooking.flight.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{

	
    @Query(value = "SELECT * FROM flights flight where flight.flight_description = ?1", nativeQuery = true)
    Optional<Flight> getFlightByFlighDes(String flighdes);
	
    @Query(value = "SELECT * FROM flights flight where flight.flight_description = ?1 and flight.flight_source = ?2 and flight.destination = ?3"
    	, nativeQuery = true)
    Optional<Flight> getFlightByFlighDetail(String flighdes, String src, String dest); 
    
    
	
	
}
