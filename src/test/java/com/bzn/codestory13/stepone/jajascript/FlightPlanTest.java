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
		assertThat(flightPlan.gain).isEqualTo(12);
		assertThat(flightPlan.path).hasSize(2).containsSequence("vol1","vol2");
	}
	@Test
	public void twoIncompatibleFlightsShouldReturnMaxGain() throws Exception {
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight("vol1", 0, 5, 5));
		flights.add(new Flight("vol2", 0, 10, 7));
		flightPlan = FlightPlan.calculate(flights);
		assertThat(flightPlan.gain).isEqualTo(7);
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
		assertThat(flightPlan.gain).isEqualTo(18);
		assertThat(flightPlan.path).hasSize(2).containsSequence("MONAD42","LEGACY01");
	}
	
	
	
	
	@Test
	public void optimizeShouldWorkWithLotOfFlights(){
		List<Flight> flights = new ArrayList<Flight>();
		int flightsNumber = 15;
		for (int i = 0; i < flightsNumber; i++) {
			flights.add(new Flight("volA"+i, i, 1,1));
			flights.add(new Flight("volB"+i, i, 1,1));
		}
		flightPlan = FlightPlan.calculate(flights);
		assertThat(flightPlan.gain).isEqualTo(flightsNumber);
		assertThat(flightPlan.path).hasSize(flightsNumber);
	}
	@Test
	public void bigRequestShouldWork() {
	 String input = "[ { \"VOL\": \"helpful-steroid-73\", \"DEPART\": 2, \"DUREE\": 5, \"PRIX\": 25 }, { \"VOL\": \"clever-spectator-97\", \"DEPART\": 3, \"DUREE\": 6, \"PRIX\": 18 }, { \"VOL\": \"mute-watch-93\", \"DEPART\": 3, \"DUREE\": 4, \"PRIX\": 5 }, { \"VOL\": \"awful-vector-44\", \"DEPART\": 3, \"DUREE\": 5, \"PRIX\": 14 }, { \"VOL\": \"enchanting-designer-54\", \"DEPART\": 4, \"DUREE\": 16, \"PRIX\": 5 }, { \"VOL\": \"long-vampire-2\", \"DEPART\": 9, \"DUREE\": 4, \"PRIX\": 21 }, { \"VOL\": \"teeny-fridge-90\", \"DEPART\": 5, \"DUREE\": 5, \"PRIX\": 13 }, { \"VOL\": \"confused-bird-95\", \"DEPART\": 6, \"DUREE\": 2, \"PRIX\": 2 }, { \"VOL\": \"smoggy-pointless-42\", \"DEPART\": 6, \"DUREE\": 1, \"PRIX\": 11 }, { \"VOL\": \"gleaming-misogyny-98\", \"DEPART\": 7, \"DUREE\": 15, \"PRIX\": 7 }, { \"VOL\": \"calm-flowerpot-45\", \"DEPART\": 11, \"DUREE\": 10, \"PRIX\": 14 }, { \"VOL\": \"grotesque-pediatric-43\", \"DEPART\": 11, \"DUREE\": 6, \"PRIX\": 11 }, { \"VOL\": \"huge-worker-79\", \"DEPART\": 12, \"DUREE\": 3, \"PRIX\": 6 }, { \"VOL\": \"easy-mechanic-97\", \"DEPART\": 14, \"DUREE\": 8, \"PRIX\": 7 }, { \"VOL\": \"dull-peddle-15\", \"DEPART\": 12, \"DUREE\": 8,"+
		"\"PRIX\": 4 }, { \"VOL\": \"aggressive-bicycle-44\", \"DEPART\": 18, \"DUREE\": 5, \"PRIX\": 21 }, { \"VOL\": \"ashamed-computer-84\", \"DEPART\": 18, \"DUREE\": 5, \"PRIX\": 23 }, { \"VOL\": \"frantic-salsa-47\", \"DEPART\": 16, \"DUREE\": 6, \"PRIX\": 6 }, { \"VOL\": \"inexpensive-sometime-60\", \"DEPART\": 15, \"DUREE\": 3, \"PRIX\": 12 }, { \"VOL\": \"rich-cocktail-18\", \"DEPART\": 16, \"DUREE\": 17, \"PRIX\": 7 }, { \"VOL\": \"brave-rainwater-58\", \"DEPART\": 24, \"DUREE\": 5, \"PRIX\": 13 }, { \"VOL\": \"testy-villager-85\", \"DEPART\": 24, \"DUREE\": 5, \"PRIX\": 15 }, { \"VOL\": \"sore-redhead-57\", \"DEPART\": 23, \"DUREE\": 1, \"PRIX\": 1 }, { \"VOL\": \"bored-sweatshirt-41\", \"DEPART\": 20, \"DUREE\": 3, \"PRIX\": 9 }, { \"VOL\": \"faithful-beast-4\", \"DEPART\": 22, \"DUREE\": 15, \"PRIX\": 2 }, { \"VOL\": \"raspy-goon-40\", \"DEPART\": 27, \"DUREE\": 2, \"PRIX\": 21 }, { \"VOL\": \"thoughtful-golf-42\", \"DEPART\": 28, \"DUREE\": 4, \"PRIX\": 14 }, { \"VOL\": \"wild-wildebeest-75\", \"DEPART\": 27, \"DUREE\": 10, \"PRIX\": 10 }, { \"VOL\": \"homeless-tearjerker-76\", \"DEPART\": 26, \"DUREE\": 5, \"PRIX\": 15 }, { \"VOL\": \"real-cowgirl-40\", \"DEPART\": 25, \"DUREE\": 6, \"PRIX\": 4 }, { \"VOL\": \"dull-weight-75\", \"DEPART\": 33, \"DUREE\": 8, \"PRIX\": 3 }, { \"VOL\": \"frightened-electrician-53\", \"DEPART\": 31, \"DUREE\": 3, \"PRIX\": 12 }, { \"VOL\": \"large-jumpsuit-88\", \"DEPART\": 30, \"DUREE\": 5, \"PRIX\": 4 }, { \"VOL\": \"talented-mule-40\", \"DEPART\": 32, \"DUREE\": 5, \"PRIX\": 6 }, { \"VOL\": \"charming-frame-84\", \"DEPART\": 33, \"DUREE\": 10, \"PRIX\": 1 }, { \"VOL\": \"doubtful-barber-82\", \"DEPART\": 37, \"DUREE\": 1, \"PRIX\": 6 }, { \"VOL\": \"tender-bargain-60\", \"DEPART\": 39, \"DUREE\": 3, \"PRIX\": 23 }, { \"VOL\": \"flat-ripple-67\", \"DEPART\": 37, \"DUREE\": 1, \"PRIX\": 4 }, { \"VOL\": \"important-steam-67\", \"DEPART\": 36, \"DUREE\": 7, \"PRIX\": 15 }, { \"VOL\": \"black-eyebrow-38\", \"DEPART\": 38, \"DUREE\": 3, \"PRIX\": 5 }, { \"VOL\": \"frightened-tuba-14\", \"DEPART\": 43, \"DUREE\": 9, \"PRIX\": 8 }, { \"VOL\": \"quiet-translator-90\", \"DEPART\": 42, \"DUREE\": 8, \"PRIX\": 20 }, { \"VOL\": \"crazy-stepchild-52\", \"DEPART\": 41, \"DUREE\": 5, \"PRIX\": 4 },"+ 
		"{ \"VOL\": \"creepy-renter-6\", \"DEPART\": 44, \"DUREE\": 1, \"PRIX\": 15 }"+
		 ", { \"VOL\": \"wide-tinder-92\", \"DEPART\": 44, \"DUREE\": 12, \"PRIX\": 6 }, { \"VOL\": \"nervous-stratum-30\", \"DEPART\": 45, \"DUREE\": 10, \"PRIX\": 13 }, { \"VOL\": \"clean-raceway-17\", \"DEPART\": 47, \"DUREE\": 5, \"PRIX\": 8 }, { \"VOL\": \"noisy-jukebox-94\", \"DEPART\": 47, \"DUREE\": 9, \"PRIX\": 8 }, { \"VOL\": \"tiny-deck-88\", \"DEPART\": 48, \"DUREE\": 9, \"PRIX\": 11 }, { \"VOL\": \"cloudy-switchblade-39\", \"DEPART\": 45, \"DUREE\": 7, \"PRIX\": 1 }, { \"VOL\": \"light-place-96\", \"DEPART\": 51, \"DUREE\": 1, \"PRIX\": 8 }, { \"VOL\": \"uninterested-acorn-13\", \"DEPART\": 54, \"DUREE\": 10, \"PRIX\": 8 }, { \"VOL\": \"loud-popcorn-44\", \"DEPART\": 54, \"DUREE\": 5, \"PRIX\": 6 }, { \"VOL\": \"blue-eyed-flowerpot-64\", \"DEPART\": 51, \"DUREE\": 4, \"PRIX\": 12 }, { \"VOL\": \"cute-pillar-67\", \"DEPART\": 52, \"DUREE\": 2, \"PRIX\": 3 }, { \"VOL\": \"deafening-transistor-48\", \"DEPART\": 56, \"DUREE\": 1, \"PRIX\": 2 }, { \"VOL\": \"delightful-molding-42\", \"DEPART\": 55, \"DUREE\": 10, \"PRIX\": 8 }, { \"VOL\": \"delightful-scone-54\", \"DEPART\": 58, \"DUREE\": 2, \"PRIX\": 8 }, { \"VOL\": \"terrible-marinade-8\", \"DEPART\""+
		  ": 57, \"DUREE\": 7, \"PRIX\": 13 }, { \"VOL\": \"envious-barrel-70\", \"DEPART\": 59, \"DUREE\": 8, \"PRIX\": 1 }, { \"VOL\": \"cooperative-strand-67\", \"DEPART\": 63, \"DUREE\": 5, \"PRIX\": 16 }, { \"VOL\": \"pleasant-pilot-8\", \"DEPART\": 63, \"DUREE\": 3, \"PRIX\": 23 }, { \"VOL\": \"resonant-couch-70\", \"DEPART\": 62, \"DUREE\": 2, \"PRIX\": 8 }, { \"VOL\": \"repulsive-choke-87\", \"DEPART\": 62, \"DUREE\": 1, \"PRIX\": 9 }, { \"VOL\": \"grotesque-pellet-50\", \"DEPART\": 62, \"DUREE\": 16, \"PRIX\": 6 }, { \"VOL\": \"ancient-barefaced-36\", \"DEPART\": 69, \"DUREE\": 10, \"PRIX\": 18 }, { \"VOL\": \"screeching-rainy-35\", \"DEPART\": 68, \"DUREE\": 9, \"PRIX\": 11 }, { \"VOL\": \"homely-sternum-51\", \"DEPART\": 65, \"DUREE\": 4, \"PRIX\": 6 }, { \"VOL\": \"curved-usher-13\", \"DEPART\": 68, \"DUREE\": 4, \"PRIX\": 8 }, { \"VOL\": \"grotesque-easel-57\", \"DEPART\": 66, \"DUREE\": 8, \"PRIX\": 6 } ]";
	 flightPlan = FlightPlan.calculate(Optimize.convertJsonToListOfFlight(input));
	assertThat(flightPlan.gain).isEqualTo(236);
	}
}


