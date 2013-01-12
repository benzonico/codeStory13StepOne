	package com.bzn.codestory13.stepone;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class StepOneServlet extends HttpServlet {

	private static final long serialVersionUID = -6871516696997178809L;

	public static final String EMAIL_QUESTION = "Quelle est ton adresse email";
	public static final String MAILING_LIST_QUESTION = "Es tu abonne a la mailing list(OUI/NON)";
	public static final String HAPPY_QUESTION = "Es tu heureux de participer(OUI/NON)";
	public static final String POST_MD_QUESTION = "Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)";
	public static final String ALWAYS_YES_QUESTION = "Est ce que tu reponds toujours oui(OUI/NON)";
	public static final String PREMIER_ENONCE = "As tu bien recu le premier enonce(OUI/NON)";
	
	public static final String EMAIL_NPERU = "nicolas.peru@gmail.com";
	public static final String OUI = "OUI";
	public static final String NON = "NON";





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
			GoogleMail.SendForCodeStory("Request Received "+question, "Reponse Sent :"+response);
			System.out.println("response sent : "+response);
			resp.getWriter().println(response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("encoding "+req.getCharacterEncoding());
		System.out.println("Receiving Markdown file : ");
		String enonce = IOUtils.toString(req.getInputStream(),Charsets.UTF_8);
		System.out.println(enonce);
		GoogleMail.SendForCodeStory("Enonce Received ", enonce);
		resp.setStatus(HttpServletResponse.SC_CREATED);
	}
	
	public String handleQuestion(String question){
		String result = null;
		if(question!=null){
			Matcher emailMatcher = Pattern.compile(EMAIL_QUESTION).matcher(question);
			Matcher mailingListMatcher = Pattern.compile(Pattern.quote(MAILING_LIST_QUESTION)).matcher(question);
			Matcher happyMatcher = Pattern.compile(Pattern.quote(HAPPY_QUESTION)).matcher(question);
			Matcher postMDMatcher = Pattern.compile(Pattern.quote(POST_MD_QUESTION)).matcher(question);
			Matcher alwaysYesMatcher = Pattern.compile(Pattern.quote(ALWAYS_YES_QUESTION)).matcher(question);
			Matcher premierMatcher = Pattern.compile(Pattern.quote(PREMIER_ENONCE)).matcher(question);
			if(emailMatcher.matches()){
				result = EMAIL_NPERU;
			}else if(mailingListMatcher.matches()){
				result = OUI;
			}else if(happyMatcher.matches()){
				result = OUI;
			}else if(postMDMatcher.matches()){
				result = OUI;
			}else if(alwaysYesMatcher.matches()){
				result = NON;
			}else if(premierMatcher.matches()){
				result = NON;
			}
			
		}
		return result;
	}
}
