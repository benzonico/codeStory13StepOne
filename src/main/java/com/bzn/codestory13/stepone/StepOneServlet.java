	package com.bzn.codestory13.stepone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.bzn.codestory13.stepone.numbers.ArithmeticParser;

public class StepOneServlet extends HttpServlet {

	private static final long serialVersionUID = -6871516696997178809L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		receiveRequest(req, resp);
		resp.setStatus(HttpServletResponse.SC_OK);
	}

	private void receiveRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String question = req.getParameter("q");
		GoogleMail.SendForCodeStory("[CodeStory] Request Received ", question +"\n response is to be sent" );
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
			GoogleMail.SendForCodeStory("[CodeStory] Request Received ", question +"\n Reponse Sent :"+response );
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
			result = TextQuestion.Answer(question);
			if(StringUtils.isBlank(result)){
				result = new ArithmeticParser().calculate(question.replace(',', '.')).replace('.', ',');
			}
		}
		return result;
	}
}
