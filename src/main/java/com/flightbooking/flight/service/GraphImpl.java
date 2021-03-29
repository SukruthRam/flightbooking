package com.flightbooking.flight.service;

import java.util.*;
import java.util.Map.Entry;

import com.flightbooking.flight.entity.Flight;

public class GraphImpl {

	
	public HashMap<String, Integer> graph (String src, String dest, List<Flight> allFlight)
	
	{
		
		Airport aiport;
		HashMap airMap = new HashMap<String, List<HashMap<String,Integer>>>();
		Set<String> stations = new HashSet<String>();
		
		for(Flight fl : allFlight)
		{
			stations.add(fl.getSrc());
			stations.add(fl.getDest());
		}
		
		for(String st: stations)
		{
			List<HashMap<String,Integer>> listSt = new ArrayList<HashMap<String,Integer>>();
			listSt = getStations(st,allFlight);
			airMap.put(st, listSt);
		}
		Set<Airport> airports = createGraph(src,airMap,stations);
		
		HashMap<String, Integer> returnFlight = createFlightList(src, dest, airports);
		
		return returnFlight;
		
		
	}

	private HashMap<String, Integer> createFlightList(String src, String dest, Set<Airport> airports) {
		
		ArrayList<String> intermediateAirports = new ArrayList<String>();
		int cost = 0;
		for(Airport ap: airports)
		{
			if(ap.getName().equals(dest))
			{
				cost = ap.getCost();
				List<Airport> intermediate = ap.getShortestPath();
				intermediate.remove(0);
				for(Airport a: intermediate)
				{
					intermediateAirports.add(a.getName());
				}
				
			}
		}
		
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		
		String str = "";
		
		for(int i=0; i<intermediateAirports.size(); i++)
		{
			str = str + intermediateAirports.get(i) +" ";
		}
		
		results.put(src+" "+str+dest, cost);
		
		
		return results;
	}

	private Set<Airport> calculateShortestPathFromSource(Graph graph, Airport source) {
		source.setCost(0);

	    Set<Airport> compAirports = new HashSet<Airport>();
	    Set<Airport> finishAirports = new HashSet<Airport>();

	    finishAirports.add(source);

	    while (finishAirports.size() != 0) {
	    	Airport airport = getLowestDistance(finishAirports);
	        finishAirports.remove(airport);
	        for (Entry < Airport, Integer> adjacencyPair: 
	          airport.getNearAirport().entrySet()) {
	        	Airport adjacentAirport = adjacencyPair.getKey();
	            Integer cost = adjacencyPair.getValue();
	            if (!compAirports.contains(adjacentAirport)) {
	                calculateMinimumDistance(adjacentAirport, cost, airport);
	                finishAirports.add(adjacentAirport);
	            }
	        }
	        compAirports.add(airport);
	    }
	    return compAirports;
	}

	private static void calculateMinimumDistance(Airport adjacentAirport, Integer cost, Airport src) {
			    Integer sourceDistance = src.getCost();
			    if (sourceDistance + cost < adjacentAirport.getCost()) {
			    	adjacentAirport.setCost(sourceDistance + cost);
			        LinkedList<Airport> shortestPath = new LinkedList<Airport>(src.getShortestPath());
			        shortestPath.add(src);
			        adjacentAirport.setShortestPath(shortestPath);
			    }
			}

	private Airport getLowestDistance(Set<Airport> finishAirports) {
		Airport leastDistAirport = null;
		    int lowestDistance = Integer.MAX_VALUE;
		    for (Airport airport: finishAirports) {
		        int distance = airport.getCost();
		        if (distance < lowestDistance) {
		            lowestDistance = distance;
		            leastDistAirport = airport;
		        }
		    }
		    return leastDistAirport;
	}

	private Set<Airport> createGraph(String src, HashMap airMap, Set<String> stations) {
		
		List<Airport> airportList = new ArrayList<Airport>();
		List<Airport> modifiedairportList = new ArrayList<Airport>();
		Graph graph = new Graph();
		for(String st: stations)
		{
			airportList.add(new Airport(st));
		}
		
		for(String st: stations)
		{
			modifiedairportList.add(new Airport(st));
		}
		for(Airport ap: airportList)
		{
			
			List<HashMap<String,Integer>> listSt = (List<HashMap<String, Integer>>) airMap.get(ap.getName());
			
			for(HashMap map : listSt)
			{
				Set keys = map.keySet();
				for(Object key: keys)
				{
					String keyval = key.toString().replaceAll("[^a-zA-Z0-9 -]", "");
					for(Airport ex: airportList)
					{
						if(ex.getName().equals(keyval))
						{
							int value = (Integer) map.get(key);
							ap.addDestination(ex, value);
							int pos = 0;
							for(Airport mp: modifiedairportList)
							{
								
								if(mp.getName().equals(ap.getName()))
								{
									modifiedairportList.set(pos, ap);
								}
								pos++;
								
							}
							
							
						}
					}	
				}	
			}
			
		}
		
		for(Airport ap : modifiedairportList)
		{
			graph.addAirport(ap);
		}
		int pos = 0;
		for(Airport mp: modifiedairportList)
		{
			
			if(mp.getName().equals(src))
			{
				break;
			}
			pos++;
			
		}
		
		Set<Airport> airports = calculateShortestPathFromSource(graph, modifiedairportList.get(pos));
		return airports;
		
	}

	private List<HashMap<String,Integer>> getStations(String st, List<Flight> allFlight) {
		
		List<HashMap<String,Integer>> listSt = new ArrayList<HashMap<String,Integer>>();
		
		
		for(Flight fl: allFlight)
		{
			HashMap<String,Integer> mapvalues = new HashMap<String,Integer>();
			if(fl.getSrc().equals(st))
			{
				mapvalues.put(fl.getDest(), fl.getCost());
				listSt.add(mapvalues);
			}
			
		}
		
		return listSt;
	}
	
}
