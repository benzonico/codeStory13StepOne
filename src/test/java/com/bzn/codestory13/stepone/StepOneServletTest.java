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
	
	@Test
	public void postMDQuestionShouldReturnOUI() throws Exception {
		StepOneServlet servlet = new StepOneServlet();
		String response = servlet.handleQuestion(StepOneServlet.POST_MD_QUESTION);
		assertThat(response).isEqualTo(StepOneServlet.OUI);
	}

	@Test
	public void alwaysYesQuestionShouldReturnNon() throws Exception {
		StepOneServlet servlet = new StepOneServlet();
		String response = servlet.handleQuestion(StepOneServlet.ALWAYS_YES_QUESTION);
		assertThat(response).isEqualTo(StepOneServlet.NON);
	}
	@Test
	public void premierEnonceQuestionShouldReturnNon() throws Exception {
		StepOneServlet servlet = new StepOneServlet();
		String response = servlet.handleQuestion(StepOneServlet.PREMIER_ENONCE);
		assertThat(response).isEqualTo(StepOneServlet.NON);
	}
}
