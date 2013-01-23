package com.bzn.codestory13.stepone.jajascript;

import java.util.ArrayList;
import java.util.List;

public class FlightPlan {

	public int gain = 0;
	public String[] path;
	public FlightPlan(){
		gain = 0;
	}
	
	public FlightPlan(int gain2, String[] path2) {
		this.gain= gain2;
		this.path = path2;
	}


	private static int maxGain;
	private static String[] maxPath;
	public static FlightPlan calculate(List<Flight> flights) {
		maxPath = new String[0];
		maxGain = 0;
		String[] path = new String[flights.size()];
		calculate(flights,0,path,0,0);
		return new FlightPlan(maxGain, maxPath);
		
	}
	
	private static void calculate(final List<Flight> flights,final int timeAvalaible,final String[] path, final int gain,final int level){
		if(flights.isEmpty()){
			if(gain>maxGain){
				maxGain = gain;
				maxPath = new String[level];
				System.arraycopy(path, 0, maxPath, 0, level);
			}
		} 
		
		for(Flight flight : flights){
			int newTimeAvalaible=flight.DEPART+flight.DUREE;
			List<Flight> possibleNextFlights = possibleNextFlights(flights,newTimeAvalaible);
			path[level] = flight.VOL;
			calculate(possibleNextFlights, newTimeAvalaible,path, gain+flight.PRIX,level+1 );
		}
	}
	
	private static List<Flight> possibleNextFlights(List<Flight> flights, long newTimeAvalaible) {
		List<Flight> result = new ArrayList<Flight>(flights.size());
		for(Flight flight :flights){
			if(flight.DEPART>=newTimeAvalaible){
				result.add(flight);
			}
		}
		return result;
	}


}
