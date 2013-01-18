package com.bzn.codestory13.stepone.numbers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ArithmeticParser {

	public static final String TERM="(.*)";
	public static final String PLUS=" ";
	public static final String MULTIPLY="\\*";
	public static final String BY="/";
	public static final String NUMBER="(\\d+)";
	
	public static final String ADD = TERM+PLUS+TERM;
	public static final String TIMES = TERM+MULTIPLY+TERM;
	public static final String DIVIDE = TERM+BY+TERM;
	
	public static final String PARENTHESIS ="(.*)\\(([^()]*)\\)(.*)";
	
	
	public String calculate(String expression){
		CustomMatcher[] matchers = {new NumberMatcher(this), new ParenthesisMatcher(this),new AddMatcher(this),new MultiplyMatcher(this), new DivMatcher(this)};
		String result = "";
		int index = 0;
		while(StringUtils.isEmpty(result) && index<matchers.length){
			result = matchers[index].calculate(expression);
			index++;
		}
		return result;
	}
	
	private abstract class CustomMatcher{
		protected ArithmeticParser parser;
		protected String toCompile;
		public CustomMatcher(ArithmeticParser parser) {
			this.parser = parser;
		}
		public String calculate(String expression){
			String result = "";
			Matcher matcher = Pattern.compile(toCompile).matcher(expression);
			if(matcher.matches()){
				result = handleMatcher(matcher);
			}
			System.out.println("expression: "+expression +" -- "+result);
			return result;
		}
		
		protected String doubleToResult(Double result){
			if(Math.floor(result)==result){
				return String.valueOf(result.intValue());	
			}
			return String.valueOf(result);
		}
		
		public abstract String handleMatcher(Matcher matcher);
	}
	
	private class NumberMatcher extends CustomMatcher{
		public NumberMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = NUMBER;
		}
		
		@Override
		public String handleMatcher(Matcher matcher) {
				return matcher.group(1);
		}
	}
	private abstract class OperatorMatcher extends CustomMatcher{
		public OperatorMatcher(ArithmeticParser parser) {
			super(parser);
		}
		protected abstract Double operation(Double leftmember,Double rightmember);
		@Override
		public String handleMatcher(Matcher matcher) {
			Double lmember = Double.parseDouble(parser.calculate(matcher.group(1)));
			Double rmember = Double.parseDouble(parser.calculate(matcher.group(2)));
			Double result = operation(lmember,rmember);
			return doubleToResult(result);
		}
	}
	
	private class MultiplyMatcher extends OperatorMatcher{
		public MultiplyMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = TIMES;
		}

		@Override
		protected Double operation(Double leftmember, Double rightmember) {
			return leftmember*rightmember;
		}
	}
	private class ParenthesisMatcher extends CustomMatcher{
		public ParenthesisMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = PARENTHESIS;
		}
		@Override
		public String handleMatcher(Matcher matcher) {
			for (int i = 1; i <= matcher.groupCount(); i++) {
				System.out.println(matcher.group(i)+" -- ");
			}
			String exp = matcher.group(0);
			String grp1 = exp.substring(0,exp.indexOf('('));
			
			return parser.calculate(matcher.group(1)+parser.calculate(matcher.group(2))+matcher.group(3));
		}
	}
	private class AddMatcher extends OperatorMatcher{
		public AddMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = ADD;
		}
		@Override
		protected Double operation(Double leftmember, Double rightmember) {
			return leftmember+rightmember;
		}
	}
	private class DivMatcher extends OperatorMatcher{
		public DivMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = DIVIDE;
		}
		@Override
		protected Double operation(Double leftmember, Double rightmember) {
			return leftmember/rightmember;
		}
	}
}
