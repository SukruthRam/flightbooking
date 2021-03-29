package com.flightbooking.flight.service;

import java.util.*;



public class Airport {
	
private String name;
    
    private List<Airport> shortestPath = new LinkedList<Airport>();
    
    private Integer cost = Integer.MAX_VALUE;
    
    Map<Airport, Integer> nearAirport = new HashMap<Airport, Integer>();

    public void addDestination(Airport destination, int distance) {
        nearAirport.put(destination, distance);
    }
 
    public Airport(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Airport> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Airport> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer distance) {
		this.cost = distance;
	}

	public Map<Airport, Integer> getNearAirport() {
		return nearAirport;
	}

	public void setNearAirport(Map<Airport, Integer> adjacentNodes) {
		this.nearAirport = adjacentNodes;
	}

    
    
}
