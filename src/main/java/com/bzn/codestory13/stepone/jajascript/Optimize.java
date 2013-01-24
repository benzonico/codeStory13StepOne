package com.bzn.codestory13.stepone.jajascript;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import com.bzn.codestory13.stepone.GoogleMail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Optimize extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Gson gson = new Gson();
	
	private static Map<String, String> cache = new HashMap<String, String>();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		long time = System.nanoTime();
		String jsonFlights = IOUtils.toString(req.getInputStream(),Charsets.UTF_8);
		System.out.println("Received Json request");
		System.out.println(jsonFlights);
		String result = "";
		String cacheHit ="no cache";
		if(cache.containsKey(jsonFlights)){
			result = cache.get(jsonFlights);
			cacheHit = "Cache HIT !!!";
		}else{
//			GoogleMail.SendForCodeStory("Jajascript request", jsonFlights);
			result = optimizeFlights(jsonFlights);
			cache.put(jsonFlights, jsonFlights);
			resp.setStatus(HttpServletResponse.SC_CREATED);
		}
		resp.getWriter().println(result);
		GoogleMail.SendForCodeStory("Jajascript request ",(System.nanoTime()-time)/1000000+"ms  "+cacheHit+"\n"+jsonFlights+"\n\n\n"+ result);
		
	}
	
	public String optimizeFlights(String jsonFlights){
		List<Flight> flights = convertJsonToListOfFlight(jsonFlights);
		FlightPlan plan = FlightPlan.calculate(flights);
		return gson.toJson(plan);
	}
	
	public static List<Flight> convertJsonToListOfFlight(String jsonFlights){
		return gson.fromJson(jsonFlights, new TypeToken<List<Flight>>(){}.getType());
	}
	
}
