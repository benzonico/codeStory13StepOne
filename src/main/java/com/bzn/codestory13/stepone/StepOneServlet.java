	package com.bzn.codestory13.stepone;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class StepOneServlet extends HttpServlet {

	private static final long serialVersionUID = -6871516696997178809L;

	public static final String EMAIL_QUESTION = "Quelle est ton adresse email";
	public static final String BINARY_QUESTION= ".*\\(OUI/NON\\)";
	
	public static final String TERM="(.*)";
	public static final String PLUS=" ";
	public static final String MULTIPLY="\\*";
	public static final String NUMBER="(\\d+)";
	
	public static final String ADD = TERM+PLUS+TERM;
	public static final String TIMES = TERM+MULTIPLY+TERM;
	
	public static final String PARENTHESIS ="(.*)\\((.*)\\)(.*)";
	
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
		if(question==null){
			badRequestAnswer(resp);
		}else{
			String response = handleQuestion(question);
			if(StringUtils.isBlank(response)){
				badRequestAnswer(resp);
			}else{
				System.out.println("response sent : "+response);
				resp.getWriter().println(response);
			}
			GoogleMail.SendForCodeStory("Request Received "+question, "Reponse Sent :"+response);
		}
	}
	
	private void badRequestAnswer(HttpServletResponse resp){
		System.out.println("no response sent");
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
		String result = "";
		System.out.println(question);
		if(question!=null){
			Matcher emailMatcher = Pattern.compile(EMAIL_QUESTION).matcher(question);
			Matcher binaryMatcher = Pattern.compile(BINARY_QUESTION).matcher(question);
			Matcher additionMatcher = Pattern.compile(ADD).matcher(question);
			Matcher multiplyMatcher = Pattern.compile(TIMES).matcher(question);
			Matcher parenthesis = Pattern.compile(PARENTHESIS).matcher(question);
			Matcher number = Pattern.compile(NUMBER).matcher(question);
			if(emailMatcher.matches()){
				result = EMAIL_NPERU;
			}else if(binaryMatcher.matches()){
				result = BinaryQuestion.Answer(question);
			}else if(number.matches()){
				result = number.group(1);
			}else if(parenthesis.matches()){
				result = handleQuestion(handleQuestion(parenthesis.group(1)+handleQuestion(parenthesis.group(2)))+parenthesis.group(3));
			}else if(additionMatcher.matches()){
				result = String.valueOf(Integer.parseInt(handleQuestion(additionMatcher.group(1)))
						+ Integer.parseInt(handleQuestion(additionMatcher.group(2))));
			}else if(multiplyMatcher.matches()){
				result = String.valueOf(Integer.parseInt(handleQuestion(multiplyMatcher.group(1)))
	                    * Integer.parseInt(handleQuestion(multiplyMatcher.group(2))));
			} 
			
		}
		return result;
	}
}
