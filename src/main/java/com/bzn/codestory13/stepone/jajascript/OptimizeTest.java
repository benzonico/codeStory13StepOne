package com.bzn.codestory13.stepone.jajascript;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class OptimizeTest {

	@Test
	public void deserializationShouldCallPlanflight() {
		Optimize servlet = new Optimize();
		String response  = servlet.optimizeFlights("[\n{\"VOL\": \"AF514\", \"DEPART\":0, \"DUREE\":5, \"PRIX\": 10}\n]");
		assertThat(response).isEqualTo("{\"gain\":10,\"path\":[\"AF514\"]}");
	}

}
