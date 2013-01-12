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

	public static final String EMAIL_QUESTION = "Quelle est ton adresse email";
	public static final String MAILING_LIST_QUESTION = "Es tu abonne a la mailing list(OUI/NON)";
	
	public static final String EMAIL_NPERU = "nicolas.peru@gmail.com";
	public static final String OUI = "OUI";


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		receiveRequest(req, resp);
		resp.setStatus(HttpServletResponse.SC_OK);
	}

	private void receiveRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String question = req.getParameter("q");
		System.out.println("Request received with question : "+question);
		String response = handleQuestion(question);
		if(response==null){
			System.out.println("no response sent");
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}else{
			System.out.println("response sent : "+response);
			resp.getWriter().println(response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		receiveRequest(req, resp);
		resp.setStatus(HttpServletResponse.SC_CREATED);
	}
	
	public String handleQuestion(String question){
		String result = null;
		if(question!=null){
			Matcher emailMatcher = Pattern.compile(EMAIL_QUESTION).matcher(question);
			Matcher mailingListMatcher = Pattern.compile(MAILING_LIST_QUESTION).matcher(question);
			if(emailMatcher.matches()){
				result = EMAIL_NPERU;
			}else if(mailingListMatcher.matches()){
				result = OUI;
			}
		}
		return result;
	}
}
