package com.flightbooking.flight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "flights")
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public Flight() {
		super();
	}

	@Column(name = "flight_id")
	private String flightid;
	
	@Column(name = "airline")
	private String airline;
	
	@Column(name = "flight_description")
	private String flightdes;
	
	@Column(name = "flight_source")
	private String src;
	
	public Flight(String flightid, String airline, String src, String dest, int cost) {
		super();
		this.flightid = flightid;
		this.airline = airline;
		this.flightdes = airline+flightid;
		this.src = src;
		this.dest = dest;
		this.cost = cost;
	}

	@Column(name = "destination")
	private String dest;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightid() {
		return flightid;
	}

	public void setFlightid(String flightid) {
		this.flightid = flightid;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFlightdes() {
		return flightdes;
	}

	public void setFlightdes(String flightdes) {
		
		this.flightdes = flightdes;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Column(name = "cost")
	private int cost;
	

}
