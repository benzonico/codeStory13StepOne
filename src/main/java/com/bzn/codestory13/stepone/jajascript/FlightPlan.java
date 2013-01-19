package com.bzn.codestory13.stepone.jajascript;

import java.util.ArrayList;
import java.util.List;

public class FlightPlan {

	public long gain;
	public List<String> path = new ArrayList<String>();
	
	public void calculate(List<Flight> flights) {
		Flight flight = flights.get(0);
		gain = flight.PRIX;
		path.add(flight.VOL);
	}

}
