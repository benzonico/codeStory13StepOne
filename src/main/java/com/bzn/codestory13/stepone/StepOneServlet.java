package com.bzn.codestory13.stepone;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StepOneServlet extends HttpServlet {

	private static final long serialVersionUID = -6871516696997178809L;
	
	public static final String EMAIL_NPERU = "nicolas.peru@gmail.com";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String question = req.getParameter("q");
		System.out.println("Request received with question : "+question);
		String response = handleQuestion(question);
		if(response==null){
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}else{
			resp.setStatus(HttpServletResponse.SC_OK);
			System.out.println("response sent : "+response);
			resp.getWriter().println(response);
		}
	}
	
	public String handleQuestion(String question){
		String result = null;
		if(question!=null){
			Matcher emailMatcher = Pattern.compile("Quelle est ton adresse email").matcher(question);
			if(emailMatcher.matches()){
				result = EMAIL_NPERU;
			}
		}
		return result;
	}
}
