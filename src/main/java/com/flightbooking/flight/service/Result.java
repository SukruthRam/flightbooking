package com.flightbooking.flight.service;

public class Result {

	private int cost;
	
	private String path;
	
	private String flights;

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFlights() {
		return flights;
	}

	public void setFlights(String flights) {
		this.flights = flights;
	}

	public Result(int cost, String path, String flights) {
		super();
		this.cost = cost;
		this.path = path;
		this.flights = flights;
	}

	
	
	
}
