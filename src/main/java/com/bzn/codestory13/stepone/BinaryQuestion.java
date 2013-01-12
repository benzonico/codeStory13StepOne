package com.bzn.codestory13.stepone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BinaryQuestion {
	MAILING_LIST_QUESTION("Es tu abonne a la mailing list(OUI/NON)" ,StepOneServlet.OUI),
	HAPPY_QUESTION("Es tu heureux de participer(OUI/NON)", StepOneServlet.OUI),
	POST_MD_QUESTION("Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)",StepOneServlet.OUI),
	ALWAYS_YES_QUESTION("Est ce que tu reponds toujours oui(OUI/NON)",StepOneServlet.NON),
	PREMIER_ENONCE("As tu bien recu le premier enonce(OUI/NON)",StepOneServlet.OUI);

	private String question;
	private String answer;
	
	
	private BinaryQuestion(String question,String answer) {
		this.question = question;
		this.answer = answer;
	} 
	
	public static String Answer(String question){
		String result = null;
		for (int i = 0; i < values().length; i++) {
			Matcher matcher = Pattern.compile(Pattern.quote(values()[i].question)).matcher(question);
			if(matcher.matches()){
				result = values()[i].answer;
			}
		}
		return result;
	}
	
	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
}
