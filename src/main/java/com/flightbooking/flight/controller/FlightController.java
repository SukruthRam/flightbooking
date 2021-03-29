package com.flightbooking.flight.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.flight.dao.FlightRepository;
import com.flightbooking.flight.entity.Flight;
import com.flightbooking.flight.exception.ThrowException;
import com.flightbooking.flight.service.FlightService;
import com.flightbooking.flight.service.Result;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
	
	@Autowired
	private FlightService fs;
	
	
	@PutMapping("/{flightid}/{airline}/{src}/{dest}/{cost}")
	public Flight createFlightById(@RequestBody Flight flight, @PathVariable("flightid") String flightid, 
			@PathVariable("airline") String airline, @PathVariable("src") String src, @PathVariable("dest") String dest,
			@PathVariable("cost") int cost
			) {
		
		Flight newFlight = new Flight(flightid, airline, src, dest, cost);
		String flightdes = airline+flightid;
		Flight exixtingFlight = null;
		int count = 0;
		
		if(this.fs.getFlights().size() > 0)
		{
			count++;
		}
		if(count > 0)
		{
			exixtingFlight = this.fs.getFlightbydetails(flightdes, src, dest);
			
		}
		
		else
		{
			return this.fs.addFlight(newFlight);
		}
		
		if(exixtingFlight != null)
		{
			if(exixtingFlight.getFlightid().equals(flightid) &&
					exixtingFlight.getAirline().equals(airline) &&
						exixtingFlight.getSrc().equals(src) &&
							exixtingFlight.getDest().equals(dest) &&
								exixtingFlight.getCost() == cost)
			{
				throw new ThrowException("Already Present");
			}
			else
			{
				exixtingFlight.setFlightid(flightid);
				exixtingFlight.setAirline(airline);
				exixtingFlight.setSrc(src);
				exixtingFlight.setDest(dest);
				exixtingFlight.setCost(cost);
				return this.fs.updateFlight(exixtingFlight);
			}
		}
		
		else
		{
			return this.fs.addFlight(newFlight);
		}
		
		
	}
	
	@PutMapping("/{flightdes}")
	public ResponseEntity<Flight> createFlightById(@RequestBody Flight flight, @PathVariable("flightdes") String flightdes) {
		
		Flight exixtingFlight = null;
		
		exixtingFlight = this.fs.getFlightbyid(flightdes);
		
		if(exixtingFlight != null)
		{
			return this.fs.deleteFlight(exixtingFlight);
		}
		
		else
		{
			throw new ThrowException("No Flights");
		}
		
	}
	
	@GetMapping("/{airline}/{src}/{dest}")
	public Result getUserById(@RequestBody Flight flight, @PathVariable("airline") String airline,
			@PathVariable("src") String src, @PathVariable("dest") String dest) {
		
		Result allFlight = this.fs.getFlights(airline, src, dest);
		
		if(allFlight.getCost() == 0)
		{
			throw new ThrowException("No Flights");
		}
		
		
		return allFlight;
		
	}
	
	
}
