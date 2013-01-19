package com.bzn.codestory13.stepone.jajascript;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FlightPlanTest {

	private FlightPlan flightPlan;
	
	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void emptyFlight() throws Exception {
		List<Flight> flights = new ArrayList<Flight>();
		flightPlan = FlightPlan.calculate(flights);
		assertThat(flightPlan.gain).isEqualTo(0);
		assertThat(flightPlan.path).hasSize(0);
	}
	@Test
	public void twoFlightsSuccessively() throws Exception {
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight("vol1", 0, 5, 5));
		flights.add(new Flight("vol2", 5, 10, 7));
		flightPlan = FlightPlan.calculate(flights);
		assertThat(flightPlan.gain).isEqualTo(12L);
		assertThat(flightPlan.path).hasSize(2).containsSequence("vol1","vol2");
	}
	@Test
	public void twoIncompatibleFlightsShouldReturnMaxGain() throws Exception {
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight("vol1", 0, 5, 5));
		flights.add(new Flight("vol2", 0, 10, 7));
		flightPlan = FlightPlan.calculate(flights);
		assertThat(flightPlan.gain).isEqualTo(7L);
		assertThat(flightPlan.path).hasSize(1).containsSequence("vol2");
	}
	

	@Test
	public void exempleEnonceShouldWork() {
		String input = "[{ \"VOL\": \"MONAD42\", \"DEPART\": 0, \"DUREE\": 5, \"PRIX\": 10 },"+
				"{ \"VOL\": \"META18\", \"DEPART\": 3, \"DUREE\": 7, \"PRIX\": 14 },"+
				"{ \"VOL\": \"LEGACY01\", \"DEPART\": 5, \"DUREE\": 9, \"PRIX\": 8 },"+
				"{ \"VOL\": \"YAGNI17\", \"DEPART\": 5, \"DUREE\": 9, \"PRIX\": 7 }"+
				"]";
		flightPlan = FlightPlan.calculate(Optimize.convertJsonToListOfFlight(input));
		assertThat(flightPlan.gain).isEqualTo(18L);
		assertThat(flightPlan.path).hasSize(2).containsSequence("MONAD42","LEGACY01");
	}
	
//	@Test
	public void optimizeShouldWorkWithLotOfFlights(){
		List<Flight> flights = new ArrayList<Flight>();
		for (int i = 0; i < 1000; i++) {
			flights.add(new Flight("volA"+i, i, 1,1));
			flights.add(new Flight("volB"+i, i, 1,1));
		}
		flightPlan = FlightPlan.calculate(flights);
		assertThat(flightPlan.gain).isEqualTo(1000);
		assertThat(flightPlan.path).hasSize(1000);
	}

}


