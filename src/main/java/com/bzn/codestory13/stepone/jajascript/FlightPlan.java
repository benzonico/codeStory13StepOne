package com.bzn.codestory13.stepone.jajascript;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FlightPlan {

	public int gain = 0;
	public String[] path;
	
	public FlightPlan(){
		gain = 0;
	}
	
	public FlightPlan(int gain, String[] path) {
		this.gain= gain;
		this.path = path;
	}

	public FlightPlan(FlightPlan fp, Flight vol){
		this.gain = fp.gain+vol.PRIX;
		this.path = ArrayUtils.addAll(fp.path, vol.VOL);
	}


	private static FlightPlan max;
	public static FlightPlan calculate(List<Flight> flights) {
		
		max = new FlightPlan(0, new String[0]);
		if(flights.isEmpty()) return max;
		int maxArrivee = flights.get(flights.size()-1).arrivee();
		FlightPlan[] bestPaths = new FlightPlan[maxArrivee+1];
		Arrays.fill(bestPaths, new FlightPlan(0, new String[0]));
		
		int flightIndex = 0;
		for (int h = 1; h < maxArrivee+1; h++) {
			List<Flight> arriveAtH = findFlightsArrivingAtH(h, flightIndex,flights);
			flightIndex +=arriveAtH.size();
			Flight bestFlightH = null;
			int gainAtH = 0;
			for(Flight flight : arriveAtH){
				if(flight.PRIX+bestPaths[flight.DEPART].gain>gainAtH){
					gainAtH = flight.PRIX+bestPaths[flight.DEPART].gain;
					bestFlightH = flight;
				}
			}
			
			bestPaths[h] = max;
			if(bestFlightH!=null){
				FlightPlan bestPathH = new FlightPlan(bestPaths[bestFlightH.DEPART], bestFlightH);
				if(bestPathH.gain>max.gain){
					max = bestPathH;
					bestPaths[h] = bestPathH;
				}
			}
		}
		return max;
		
	}

	private static List<Flight> findFlightsArrivingAtH(int h, int flightIndex,
			List<Flight> flights) {
		int toIndex = flightIndex;
		boolean continueLoop = true;
		while( continueLoop && toIndex<flights.size()){
			Flight currentFlight = flights.get(toIndex);
			if(currentFlight.arrivee()==h){
				toIndex++;
				continueLoop = true;
			}else{
				continueLoop = false;
			}
			
			
		}
		return flights.subList(flightIndex, toIndex);
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(gain).append(path).toString();
	}

}
