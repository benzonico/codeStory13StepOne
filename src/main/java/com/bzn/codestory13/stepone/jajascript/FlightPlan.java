package com.bzn.codestory13.stepone.jajascript;

import java.util.ArrayList;
import java.util.List;

public class FlightPlan {

	public long gain = 0;
	public List<String> path;
	public FlightPlan(){
		gain = 0;
		path = new ArrayList<String>();
	}
	public FlightPlan(long gain, List<String> path) {
		super();
		this.gain = gain;
		this.path = path;
	}
	public static FlightPlan calculate(List<Flight> flights) {
		List<FlightPlan> flightPlans = calculate(flights,0,new FlightPlan());
		FlightPlan maxGain = new FlightPlan(-1,null);
		for(FlightPlan fp : flightPlans){
			if(fp.gain>maxGain.gain){
				maxGain = fp;
			}
		}
		return maxGain;
		
	}
	
	private static List<FlightPlan> calculate(final List<Flight> flights,final long timeAvalaible,final FlightPlan flightPlan){
		List<FlightPlan> result = new ArrayList<FlightPlan>(); 	
		if(flights.isEmpty()){
			result.add(flightPlan);
			return result;
		} 
		long currentGain = flightPlan.gain;
		
		for(Flight flight : flights){
			long newTimeAvalaible=flight.DEPART+flight.DUREE;
			List<Flight> possibleNextFlights = possibleNextFlights(flights,newTimeAvalaible);
			List<String> currentPath = new ArrayList<String>(flightPlan.path);
			currentPath.add(flight.VOL);
			FlightPlan fp = new FlightPlan(currentGain+flight.PRIX,currentPath);
			result.addAll(calculate(possibleNextFlights, newTimeAvalaible, fp ));
		}
		return result;
	}
	
	private static List<Flight> possibleNextFlights(List<Flight> flights, long newTimeAvalaible) {
		List<Flight> result = new ArrayList<Flight>();
		for(Flight flight :flights){
			if(flight.DEPART>=newTimeAvalaible){
				result.add(flight);
			}
		}
		return result;
	}


}
