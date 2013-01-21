package com.bzn.codestory13.stepone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TextQuestion {
	EMAIL_QUESTION("Quelle est ton adresse email","nicolas.peru@gmail.com"),
	MAILING_LIST_QUESTION("Es tu abonne a la mailing list(OUI/NON)" ,"OUI"),
	HAPPY_QUESTION("Es tu heureux de participer(OUI/NON)", "OUI"),
	POST_MD_QUESTION("Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)","OUI"),
	ALWAYS_YES_QUESTION("Est ce que tu reponds toujours oui(OUI/NON)","NON"),
	PREMIER_ENONCE("As tu bien recu le premier enonce(OUI/NON)","OUI"),
	BONNE_NUIT("As tu passe une bonne nuit malgre les bugs de l etape precedente(PAS_TOP/BOF/QUELS_BUGS)","QUELS_BUGS"),
	DEUXIEME_ENONCE("As tu bien recu le second enonce(OUI/NON)","OUI"),
	NDELOOF("As tu copie le code de ndeloof(OUI/NON/JE_SUIS_NICOLAS)","NON");
	private String question;
	private String answer;
	
	
	private TextQuestion(String question,String answer) {
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
