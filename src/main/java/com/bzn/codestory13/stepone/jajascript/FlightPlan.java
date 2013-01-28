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
		calculate(flights.toArray(new Flight[flights.size()]),0,path,0,0);
		return new FlightPlan(maxGain, maxPath);
		
	}
	
	private static void calculate(final Flight[] flights,final int timeAvalaible,final String[] path, final int gain,final int level){
		if(flights.length==0){
			if(gain>maxGain){
				maxGain = gain;
				maxPath = new String[level];
				System.arraycopy(path, 0, maxPath, 0, level);
			}
		} 
		Flight[] startable = startablePath(flights);
		for(Flight flight : startable){
			int newTimeAvalaible=flight.DEPART+flight.DUREE;
			Flight[] possibleNextFlights = possibleNextFlights(flights,newTimeAvalaible,gain+flight.PRIX);
			path[level] = flight.VOL;
			calculate(possibleNextFlights, newTimeAvalaible,path, gain+flight.PRIX,level+1 );
		}
	}
	
	private static Flight[] startablePath(Flight[] flights){
		List<Flight> result = new ArrayList<Flight>(flights.length);
		for(Flight flight :flights){
			boolean isStartable = true;
			for(Flight compare :flights){
				if(flight!=compare && flight.DEPART>(compare.DEPART+compare.DUREE)){
					isStartable = false;
				}
			}
			if(isStartable){
				result.add(flight);
			}
		}
		return result.toArray(new Flight[0]);
		
	}
	private static Flight[] possibleNextFlights(Flight[] flights, long newTimeAvalaible,int gain) {
		Flight[] result = null;
		int potentialGain = 0;
		int nbFlights = flights.length;
		int index = 0;
		for (int i = 0; i < nbFlights; i++) {
			Flight flight = flights[i];
			if(flight.DEPART>=newTimeAvalaible){
				if(result==null){
					result = new Flight[nbFlights-i];
					index = i;
				}
				result[i-index] = flight;
				potentialGain += flight.PRIX;
			}
		}
		if((potentialGain+gain)<maxGain || result == null){
			result = new Flight[0];
		}
		return result;
	}


}
