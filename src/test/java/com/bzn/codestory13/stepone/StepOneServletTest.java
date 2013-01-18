package com.bzn.codestory13.stepone;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class StepOneServletTest {
	
	private StepOneServlet servlet = new StepOneServlet();

	@Test
	public void nullQuestionShouldReturnNullResponse() {
		String response = servlet.handleQuestion(null);
		assertThat(response).isNull();
	}
	
	@Test
	public void emailQuestionShouldReturnMyEmail() throws Exception {
		String response = servlet.handleQuestion(StepOneServlet.EMAIL_QUESTION);
		assertThat(response).isEqualTo(StepOneServlet.EMAIL_NPERU);
	}
	
	@Test
	public void BinaryQuestionShouldReturnTheirAnswer() throws Exception {
		for (int i = 0; i < BinaryQuestion.values().length; i++) {
			BinaryQuestion bquestion = BinaryQuestion.values()[i];
			String response = servlet.handleQuestion(bquestion.getQuestion());
			assertThat(response).isEqualTo(bquestion.getAnswer());
		}
	}
	
	@Test
	public void additionQuestionShouldReturnAnswer() throws Exception {
		String response = servlet.handleQuestion("1 1");
		assertThat(response).isEqualTo("2");
		response = servlet.handleQuestion("1 2");
		assertThat(response).isEqualTo("3");
		response = servlet.handleQuestion("156 164");
		assertThat(response).isEqualTo("320");
	}
}
