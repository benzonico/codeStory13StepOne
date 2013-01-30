package com.bzn.codestory13.stepone.jajascript;

import java.util.Arrays;
import java.util.List;

public class FlightPlan {

	public int gain = 0;
	public String[] path;
	private FlightPlan parentFP;
	
	public FlightPlan(){
		gain = 0;
	}
	
	public FlightPlan(int gain, String[] path) {
		this.gain= gain;
		this.path = path;
	}

	public FlightPlan(FlightPlan fp, Flight vol){
		this.gain = fp.gain+vol.PRIX;
		this.path = new String[]{vol.VOL};
		this.parentFP = fp;
	}
	private FlightPlan calculatePath(){
		String pathToConstruct = path[0];
		FlightPlan father = parentFP;
		while (father.parentFP!=null) {
			pathToConstruct=father.path[0]+","+pathToConstruct;
			father = father.parentFP;
		}
		path = pathToConstruct.split(",");
		parentFP = null;
		return this;
	}
	
	

	private static FlightPlan max;
	public static FlightPlan calculate(List<Flight> flights) {
		
		max = new FlightPlan(0, new String[0]);
		if(flights.isEmpty()) return max;
		int maxArrivee = flights.get(flights.size()-1).arrivee();
		FlightPlan[] bestPaths = new FlightPlan[maxArrivee+1];
		Arrays.fill(bestPaths, new FlightPlan(0, new String[0]));
		
		for (int h = 1; h < maxArrivee+1; h++) {
			while(!flights.isEmpty() && flights.get(0).arrivee()==h){
				Flight flight = flights.remove(0);
				if(flight.PRIX+bestPaths[flight.DEPART].gain>max.gain){
					max = new FlightPlan(bestPaths[flight.DEPART], flight);
				}
			}
			bestPaths[h] = max;
		}
		return max.calculatePath();
		
	}

	

}
