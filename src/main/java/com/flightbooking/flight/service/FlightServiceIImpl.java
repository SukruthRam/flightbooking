package com.flightbooking.flight.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.flight.dao.FlightRepository;
import com.flightbooking.flight.entity.Flight;
import com.flightbooking.flight.exception.ThrowException;

@Service
public class FlightServiceIImpl implements FlightService {

	@Autowired
	private FlightRepository dao;

	GraphImpl gimpl = new GraphImpl();

	public Flight addFlight(Flight flight) {
		return this.dao.save(flight);
	}

	public Flight updateFlight(Flight flight) {

		return this.dao.save(flight);
	}

	public Flight getFlightbyid(String id) {
		Optional<Flight> flight = this.dao.getFlightByFlighDes(id);

		if (flight.isPresent()) {
			return flight.get();
		} else {

		}
		return null;
	}

	public Flight getFlightbydetails(String id, String src, String dest) {
		Optional<Flight> flight = this.dao.getFlightByFlighDetail(id, src, dest);

		if (flight.isPresent()) {
			return flight.get();
		} else {

		}
		return null;
	}

	public Result getFlights(String airline, String src, String dest) {

		List<Flight> allFlight = dao.findAll();

		List<Flight> specificAirlineFlights = new ArrayList<Flight>();

		for (Flight fl : allFlight) {
			if (fl.getAirline().equals(airline)) {
				specificAirlineFlights.add(fl);
			}
		}

		HashMap<String, Integer> directFlight = new HashMap<String, Integer>();

		for (Flight f : allFlight) {
			if (f.getSrc().equals(src) && f.getDest().equals(dest)) {
				directFlight.put(src + " " + dest, f.getCost());
			}
		}

		HashMap<String, Integer> flights = gimpl.graph(src, dest, specificAirlineFlights);

		ArrayList<String> flightsList = new ArrayList<String>();
		int directCost = 0;
		int flightCost = 0;

		for (Integer i : directFlight.values()) {
			directCost = i;
		}

		for (Integer i : flights.values()) {
			flightCost = i;
		}

		if (flightCost > 0) {
			if (directCost < flightCost && directCost > 0) {

				for (Flight f : specificAirlineFlights) {
					if (f.getSrc().equals(src) && (f.getDest().equals(dest))) {
						flightsList.add(f.getFlightdes());
					}
				}

				Result buildResult = getResult(directFlight, flightsList.toString());

				return buildResult;
			}

			else {

				ArrayList<String> arrSt = new ArrayList<String>();
				for (String str : flights.keySet()) {
					String[] strStations = str.split(" ");
					for (int i = 0; i < strStations.length; i++) {
						arrSt.add(strStations[i]);
					}

				}

				for (int i = 0; i < arrSt.size() - 1; i++) {
					String begin = arrSt.get(i);
					String end = arrSt.get(i + 1);

					for (Flight f : specificAirlineFlights) {
						if (f.getSrc().equals(begin) && (f.getDest().equals(end))) {
							flightsList.add(f.getFlightdes());
						}
					}

				}

				Result buildResult = getResult(flights, flightsList.toString());
				return buildResult;
			}
		} else {
			if (directCost > 0) {

				for (Flight f : specificAirlineFlights) {
					if (f.getSrc().equals(src) && (f.getDest().equals(dest))) {
						flightsList.add(f.getFlightdes());
					}
				}

				Result buildResult = getResult(directFlight, flightsList.toString());
				return buildResult;

			} else {
				Result buildResult = new Result(0, " ", " ");
				return buildResult;
			}
		}

	}

	public List<Flight> getFlights() {

		List<Flight> allFlight = dao.findAll();
		return allFlight;
	}

	public ResponseEntity<Flight> deleteFlight(Flight flight) {
		this.dao.delete(flight);
		return ResponseEntity.ok().build();
	}

	public Result getResult(HashMap<String, Integer> flightMap, String flights) {

		Result buildResult = null;

		for (Entry<String, Integer> entry : flightMap.entrySet()) {

			buildResult = new Result(entry.getValue(), entry.getKey(), flights);

		}
		return buildResult;
	}

}
