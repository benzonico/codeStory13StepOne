package com.bzn.codestory13.stepone.jajascript;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Optimize extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Gson gson = new Gson();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		long time = System.nanoTime();
		String jsonFlights = IOUtils.toString(req.getInputStream(),Charsets.UTF_8);
		System.out.println("Received Json request");
		System.out.println(jsonFlights);
		String result = optimizeFlights(jsonFlights);
		resp.setStatus(HttpServletResponse.SC_CREATED);
		resp.getWriter().println(result);
		System.out.println(result);
		time = (System.nanoTime()-time)/1000000;
		System.out.println("found in "+time+"ms");
//		GoogleMail.SendForCodeStory("Jajascript request ",time+"ms  "+cacheHit+"\n"+jsonFlights+"\n\n\n"+ result);
		
	}
	
	public String optimizeFlights(String jsonFlights){
		List<Flight> flights = convertJsonToListOfFlight(jsonFlights);
		Collections.sort(flights);
		FlightPlan plan = FlightPlan.calculate(flights);
		return gson.toJson(plan);
	}
	
	public static List<Flight> convertJsonToListOfFlight(String jsonFlights){
		return gson.fromJson(jsonFlights, new TypeToken<List<Flight>>(){}.getType());
	}
	
}
