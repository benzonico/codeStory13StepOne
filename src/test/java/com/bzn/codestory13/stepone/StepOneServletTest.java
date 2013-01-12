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
	public void BinaryQuestionShouldReturnTheirAnswer() throws Exception {
		StepOneServlet servlet = new StepOneServlet();
		for (int i = 0; i < BinaryQuestion.values().length; i++) {
			BinaryQuestion bquestion = BinaryQuestion.values()[i];
			String response = servlet.handleQuestion(bquestion.getQuestion());
			assertThat(response).isEqualTo(bquestion.getAnswer());
		}
	}
}
