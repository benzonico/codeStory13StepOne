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
		int flightsNumber = 9000;
		for (int i = 0; i < flightsNumber; i++) {
			flights.add(new Flight("volA"+i, i, 1,1));
			flights.add(new Flight("volB"+i, i, 1,0));
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
	
	@Test
	public void hugeRequest(){		
		String input = 
	"[ { \"VOL\": \"calm-crow-61\", \"DEPART\": 2, \"DUREE\": 2, \"PRIX\": 7 }, { \"VOL\": \"successful-showoff-96\", \"DEPART\": 4, \"DUREE\": 8, \"PRIX\": 4 }, { \"VOL\": \"crooked-author-44\", \"DEPART\": 1, \"DUREE\": 10, \"PRIX\": 4 }, { \"VOL\": \"thoughtless-vessel-79\", \"DEPART\": 3, \"DUREE\": 1, \"PRIX\": 7 }, { \"VOL\": \"square-driftwood-25\", \"DEPART\": 3, \"DUREE\": 14, \"PRIX\": 6 }, { \"VOL\": \"steep-stammerer-87\", \"DEPART\": 7, \"DUREE\": 10, \"PRIX\": 17 }, { \"VOL\": \"little-mackerel-13\", \"DEPART\": 5, \"DUREE\": 3, \"PRIX\": 11 }, { \"VOL\": \"dangerous-autograph-45\", \"DEPART\": 7, \"DUREE\": 1, \"PRIX\": 6 }, { \"VOL\": \"petite-ripoff-88\", \"DEPART\": 5, \"DUREE\": 1, \"PRIX\": 11 }, { \"VOL\": \"envious-fire-66\", \"DEPART\": 5, \"DUREE\": 8, \"PRIX\": 5 }, { \"VOL\": \"crazy-tiling-72\", \"DEPART\": 14, \"DUREE\": 6, \"PRIX\": 22 }, { \"VOL\": \"obedient-windburn-49\", \"DEPART\": 14, \"DUREE\": 1, \"PRIX\": 9 }, { \"VOL\": \"elegant-coyote-14\", \"DEPART\": 14, \"DUREE\": 4, \"PRIX\": 4 }, { \"VOL\": \"dead-seabed-32\", \"DEPART\": 13, \"DUREE\": 7, \"PRIX\": 8 }, { \"VOL\": \"annoyed-pastry-68\", \"DEPART\": 14, \"DUREE\": 3, \"PRIX\": 1 }, { \"VOL\": \"upset-quadriplegic-76\", \"DEPART\": 16, \"DUREE\": 5, \"PRIX\": 9 }, { \"VOL\": \"annoying-stationery-63\", \"DEPART\": 16, \"DUREE\": 1, \"PRIX\": 8 }, { \"VOL\": \"nice-leprechaun-95\", \"DEPART\": 18, \"DUREE\": 3, \"PRIX\": 4 }, { \"VOL\": \"precious-shortbread-18\", \"DEPART\": 17, \"DUREE\": 6, \"PRIX\": 6 }, { \"VOL\": \"bloody-uncle-37\", \"DEPART\": 18, \"DUREE\": 18, \"PRIX\": 2 }, { \"VOL\": \"hollow-coliseum-99\", \"DEPART\": 20, \"DUREE\": 7, \"PRIX\": 21 }, { \"VOL\": \"ill-pit-31\", \"DEPART\": 22, \"DUREE\": 9, \"PRIX\": 13 }, { \"VOL\": \"straight-visitation-50\", \"DEPART\": 20, \"DUREE\": 7, \"PRIX\": 4 }, { \"VOL\": \"purring-cuckoo-75\", \"DEPART\": 24, \"DUREE\": 5, \"PRIX\": 12 }, { \"VOL\": \"hurt-lemonade-26\", \"DEPART\": 24, \"DUREE\": 20, \"PRIX\": 4 }, { \"VOL\": \"motionless-virgo-34\", \"DEPART\": 29, \"DUREE\": 9, \"PRIX\": 18 }, { \"VOL\": \"filthy-tuxedo-59\", \"DEPART\": 27, \"DUREE\": 5, \"PRIX\": 5 }, { \"VOL\": \"eager-block-37\", \"DEPART\": 25, \"DUREE\": 6, \"PRIX\": 3 }, { \"VOL\": \"graceful-newsstand-77\", \"DEPART\": 27, \"DUREE\": 9, \"PRIX\": 11 }, { \"VOL\": \"scrawny-sailor-22\", \"DEPART\": 28, \"DUREE\": 19, \"PRIX\": 2 }, { \"VOL\": \"long-banking-19\", \"DEPART\": 34, \"DUREE\": 7, \"PRIX\": 9 }, { \"VOL\": \"sparkling-smugness-97\", \"DEPART\": 33, \"DUREE\": 5, \"PRIX\": 23 }, { \"VOL\": \"wandering-hailstone-71\", \"DEPART\": 31, \"DUREE\": 10, \"PRIX\": 2 }, { \"VOL\": \"tiny-ice-93\", \"DEPART\": 32, \"DUREE\": 3, \"PRIX\": 8 }, { \"VOL\": \"embarrassed-gem-58\", \"DEPART\": 31, \"DUREE\": 18, \"PRIX\": 7 }, { \"VOL\": \"gentle-nunnery-60\", \"DEPART\": 36, \"DUREE\": 9, \"PRIX\": 20 }, { \"VOL\": \"evil-steamboat-62\", \"DEPART\": 38, \"DUREE\": 8, \"PRIX\": 20 }, { \"VOL\": \"itchy-salt-94\", \"DEPART\": 39, \"DUREE\": 4, \"PRIX\": 3 }, { \"VOL\": \"calm-plaza-49\", \"DEPART\": 38, \"DUREE\": 7, \"PRIX\": 12 }, { \"VOL\": \"rich-symbolism-84\", \"DEPART\": 38, \"DUREE\": 7, \"PRIX\": 3 }, { \"VOL\": \"angry-box-13\", \"DEPART\": 41, \"DUREE\": 10, \"PRIX\": 19 }, { \"VOL\": \"whispering-locket-57\", \"DEPART\": 41, \"DUREE\": 10, \"PRIX\": 7 }, { \"VOL\": \"faint-dessert-42\", \"DEPART\": 44, \"DUREE\": 3, \"PRIX\": 10 }, { \"VOL\": \"strange-shoestring-42\", \"DEPART\": 44, \"DUREE\": 4, \"PRIX\": 6 }, {\"VOL\": \"lazy-silkworm-39\", \"DEPART\": 41, \"DUREE\": 20, \"PRIX\": 7 }, { \"VOL\": \"annoyed-specialization-71\", \"DEPART\": 47, \"DUREE\": 3, \"PRIX\": 27 }, { \"VOL\": \"brave-kettledrum-70\", \"DEPART\": 45, \"DUREE\": 6, \"PRIX\": 8 }, { \"VOL\": \"eager-scone-36\", \"DEPART\": 48, \"DUREE\": 3, \"PRIX\": 3 }, { \"VOL\": \"envious-gynecology-5\", \"DEPART\": 45, \"DUREE\": 10, \"PRIX\": 10 }, { \"VOL\": \"different-trailer-62\", \"DEPART\": 47, \"DUREE\": 9, \"PRIX\": 2 }, { \"VOL\": \"adorable-tour-48\", \"DEPART\": 53, \"DUREE\": 9, \"PRIX\": 28 }, { \"VOL\": \"helpless-squeamishness-17\", \"DEPART\": 50, \"DUREE\": 3, \"PRIX\": 6 }, { \"VOL\": \"filthy-greens-83\", \"DEPART\": 53, \"DUREE\": 1, \"PRIX\": 10 }, { \"VOL\": \"great-underwear-8\", \"DEPART\": 51, \"DUREE\": 3, \"PRIX\": 7 }, { \"VOL\": \"resonant-toenail-39\", \"DEPART\": 54, \"DUREE\": 17, \"PRIX\": 1 }, { \"VOL\": \"nice-knob-42\", \"DEPART\": 57, \"DUREE\": 7, \"PRIX\": 29 }, { \"VOL\": \"magnificent-sideburns-88\", \"DEPART\": 58, \"DUREE\": 9, \"PRIX\": 16 }, { \"VOL\": \"bewildered-stalemate-14\", \"DEPART\": 56, \"DUREE\": 10, \"PRIX\": 9 }, { \"VOL\": \"time-society-98\", \"DEPART\": 58, \"DUREE\": 8, \"PRIX\": 10 }, { \"VOL\": \"innocent-nose-20\", \"DEPART\": 56, \"DUREE\": 5, \"PRIX\": 4 }, { \"VOL\": \"time-pinprick-69\", \"DEPART\": 60, \"DUREE\": 5, \"PRIX\": 17 }, { \"VOL\": \"selfish-squid-95\", \"DEPART\": 61, \"DUREE\": 3, \"PRIX\": 16 }, { \"VOL\": \"mushy-lap-47\", \"DEPART\": 61, \"DUREE\": 1, \"PRIX\": 8 }, { \"VOL\": \"bright-jig-15\", \"DEPART\": 64, \"DUREE\": 7, \"PRIX\": 9 }, { \"VOL\": \"innocent-thresher-53\", \"DEPART\": 63, \"DUREE\": 2, \"PRIX\": 3 }, { \"VOL\": \"lively-curl-57\", \"DEPART\": 67, \"DUREE\": 7, \"PRIX\": 28 }, { \"VOL\": \"Early-liar-10\", \"DEPART\": 69, \"DUREE\": 5, \"PRIX\": 12 }, { \"VOL\": \"curved-traffic-16\", \"DEPART\": 67, \"DUREE\": 7, \"PRIX\": 6 }, { \"VOL\": \"clean-teardrop-91\", \"DEPART\": 68, \"DUREE\": 5, \"PRIX\": 6 }, { \"VOL\": \"vast-pointless-31\", \"DEPART\": 69, \"DUREE\": 15, \"PRIX\": 3 }, { \"VOL\": \"better-molehill-50\", \"DEPART\": 71, \"DUREE\": 7, \"PRIX\": 20 }, { \"VOL\": \"embarrassed-visor-37\", \"DEPART\": 71, \"DUREE\": 5, \"PRIX\": 9 }, { \"VOL\": \"happy-showoff-5\", \"DEPART\": 74, \"DUREE\": 2, \"PRIX\": 7 }, { \"VOL\": \"obnoxious-lens-25\", \"DEPART\": 73, \"DUREE\": 5, \"PRIX\": 7 }, { \"VOL\": \"crazy-barrier-1\", \"DEPART\": 74, \"DUREE\": 4, \"PRIX\": 3 }, { \"VOL\": \"resonant-drizzle-29\", \"DEPART\": 75, \"DUREE\": 6, \"PRIX\": 20 }, { \"VOL\": \"long-showoff-1\", \"DEPART\": 79, \"DUREE\": 8, \"PRIX\": 17 }, { \"VOL\": \"upset-anklet-69\", \"DEPART\": 78, \"DUREE\": 2, \"PRIX\": 10 }, { \"VOL\": \"light-vender-7\", \"DEPART\": 76, \"DUREE\": 10, \"PRIX\": 12 }, { \"VOL\": \"graceful-zeppelin-36\", \"DEPART\": 79, \"DUREE\": 12, \"PRIX\": 3 }, { \"VOL\": \"nutty-kite-55\", \"DEPART\": 81, \"DUREE\": 7, \"PRIX\": 28 }, { \"VOL\": \"magnificent-satin-5\", \"DEPART\": 81, \"DUREE\": 5, \"PRIX\": 18 }, { \"VOL\": \"silly-file-61\", \"DEPART\": 81, \"DUREE\": 8, \"PRIX\": 10 }, { \"VOL\": \"angry-stalemate-41\", \"DEPART\": 82, \"DUREE\": 6, \"PRIX\": 12 }, { \"VOL\": \"mammoth-cheddar-84\", \"DEPART\": 83, \"DUREE\": 4, \"PRIX\": 1 }, { \"VOL\": \"obedient-puffball-34\", \"DEPART\": 86, \"DUREE\": 2, \"PRIX\": 26 }, { \"VOL\": \"lazy-choke-75\", \"DEPART\": 86, \"DUREE\": 8, \"PRIX\": 13 }, { \"VOL\": \"elegant-dime-81\", \"DEPART\": 86, \"DUREE\": 1, \"PRIX\": 4 }, { \"VOL\": \"energetic-ale-68\", \"DEPART\": 87, \"DUREE\": 3, \"PRIX\": 14 }, { \"VOL\": \"determined-rake-67\", \"DEPART\": 88, \"DUREE\": 7, \"PRIX\": 4 }, { \"VOL\": \"excited-guitarist-40\", \"DEPART\": 90, \"DUREE\": 2, \"PRIX\": 5 }, { \"VOL\": \"enchanting-stairwell-24\", \"DEPART\": 91, \"DUREE\": 6, \"PRIX\": 12 }, { \"VOL\": \"round-somewhat-8\", \"DEPART\": 91, \"DUREE\": 4, \"PRIX\": 8 }, { \"VOL\": \"drab-gut-22\", \"DEPART\": 90, \"DUREE\": 1, \"PRIX\": 14 }, { \"VOL\": \"deafening-squabble-21\", \"DEPART\": 93, \"DUREE\": 7, \"PRIX\": 3 } ]";
		List<Flight> flights = Optimize.convertJsonToListOfFlight(input);
		System.out.println(flights.size());
		flightPlan = FlightPlan.calculate(flights);
		assertThat(flightPlan.gain).isEqualTo(305);
		}	
}


