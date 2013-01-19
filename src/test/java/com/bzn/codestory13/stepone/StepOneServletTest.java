package com.bzn.codestory13.stepone;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class StepOneServletTest {
	
	private StepOneServlet servlet = new StepOneServlet();

	@Test
	public void nullQuestionShouldReturnNullResponse() {
		String response = servlet.handleQuestion(null);
		assertThat(response).isEmpty();
	}
	
	@Test
	public void emailQuestionShouldReturnMyEmail() throws Exception {
		String response = servlet.handleQuestion(StepOneServlet.EMAIL_QUESTION);
		assertThat(response).isEqualTo(StepOneServlet.EMAIL_NPERU);
	}
	
	@Test
	public void BinaryQuestionShouldReturnTheirAnswer() throws Exception {
		for (int i = 0; i < TextQuestion.values().length; i++) {
			TextQuestion bquestion = TextQuestion.values()[i];
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
	
	@Test
	public void multiplyQuestionShouldReturnAnswer() throws Exception {
		String response = servlet.handleQuestion("1*1");
		assertThat(response).isEqualTo("1");
		response = servlet.handleQuestion("1*2");
		assertThat(response).isEqualTo("2");
		response = servlet.handleQuestion("4*5");
		assertThat(response).isEqualTo("20");
	}
	
	@Test
	public void parenthesisMatching() throws Exception {
		String response = servlet.handleQuestion("(1 2)*2");
		assertThat(response).isEqualTo("6");
		response = servlet.handleQuestion("2*(1 3)");
		assertThat(response).isEqualTo("8");
	}
	@Test
	public void precedenceShouldWork() throws Exception {
		String response = servlet.handleQuestion("3 5*2");
		assertThat(response).isEqualTo("13");
		response = servlet.handleQuestion("(3 5)*2");
		assertThat(response).isEqualTo("16");
	}
	@Test
	public void multipleParenthesis() throws Exception {
		String response = servlet.handleQuestion("(3 5)*(1 2)");
		assertThat(response).isEqualTo("24");
		response = servlet.handleQuestion("(3 5)*(1 2) 5");
		assertThat(response).isEqualTo("29");
	}
	@Test
	public void divide() throws Exception {
		String response = servlet.handleQuestion("(1 2)/2");
		assertThat(response).isEqualTo("1,5");
	}
	
	@Test
	public void combineDivide() throws Exception {
		String response = servlet.handleQuestion("(1 2)/2*3");
		assertThat(response).isEqualTo("4,5");
		response = servlet.handleQuestion("(1 2)/2 5");
		assertThat(response).isEqualTo("6,5");
		response = servlet.handleQuestion("8/4");
		assertThat(response).isEqualTo("2");
	}
	
	@Test
	public void nestedParenthesis(){
		String response = servlet.handleQuestion("((1 2) 3 4 (5 6 7) (8 9 10)*3)/2*5");
		assertThat(response).isEqualTo("272,5");
		response = servlet.handleQuestion("3*((1 2) (2*2))");
		assertThat(response).isEqualTo("21");
	}
	@Test 
	public void decimalShouldBeHandled() throws Exception {
		String response = servlet.handleQuestion("1,5*4");
		assertThat(response).isEqualTo("6");
	}
	@Test
	public void bigNumbers() throws Exception {
		String response = servlet.handleQuestion("((1,1 2) 3,14 4 (5 6 7) (8 9 10)*4267387833344334647677634)/2*553344300034334349999000");
		assertThat(response).isEqualTo("31878018903828899277492024491376690701584023926880");
	}
}
	
