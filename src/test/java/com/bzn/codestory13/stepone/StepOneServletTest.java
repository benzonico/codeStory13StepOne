package com.bzn.codestory13.stepone;

import static org.fest.assertions.api.Assertions.*;
import org.junit.Test;

public class StepOneServletTest {
	
	@Test
	public void nullQuestionShouldReturnNullResponse() {
		StepOneServlet servlet = new StepOneServlet();
		String response = servlet.handleQuestion(null);
		assertThat(response).isNull();
	}
	
	@Test
	public void emailQuestionShouldReturnMyEmail() throws Exception {
		StepOneServlet servlet = new StepOneServlet();
		String response = servlet.handleQuestion(StepOneServlet.EMAIL_QUESTION);
		assertThat(response).isEqualTo(StepOneServlet.EMAIL_NPERU);
	}
	
	@Test
	public void mailingListQuestionShouldReturnOUI() throws Exception {
		StepOneServlet servlet = new StepOneServlet();
		String response = servlet.handleQuestion(StepOneServlet.MAILING_LIST_QUESTION);
		assertThat(response).isEqualTo(StepOneServlet.OUI);
	}
	
	@Test
	public void happyQuestionShouldReturnOUI() throws Exception {
		StepOneServlet servlet = new StepOneServlet();
		String response = servlet.handleQuestion(StepOneServlet.HAPPY_QUESTION);
		assertThat(response).isEqualTo(StepOneServlet.OUI);
	}

}
