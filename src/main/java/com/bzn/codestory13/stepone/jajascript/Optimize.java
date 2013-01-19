package com.bzn.codestory13.stepone.jajascript;

import java.io.IOException;
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String jsonFlights = IOUtils.toString(req.getInputStream(),Charsets.UTF_8);
		String result = optimizeFlights(jsonFlights);
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		resp.getWriter().println(result);
		
	}
	
	public String optimizeFlights(String jsonFlights){
		Gson gson = new Gson();
		List<Flight> flights = gson.fromJson(jsonFlights, new TypeToken<List<Flight>>(){}.getType());
		FlightPlan plan = new FlightPlan();
		plan.calculate(flights);
		return gson.toJson(plan);
	}
	
}
