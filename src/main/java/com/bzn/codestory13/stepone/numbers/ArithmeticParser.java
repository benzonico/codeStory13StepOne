package com.bzn.codestory13.stepone.numbers;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ArithmeticParser {

	public static final String TERM = "(.*)";
	public static final String PLUS = " ";
	public static final String MULTIPLY = "\\*";
	public static final String BY = "/";
	public static final String NUMBER = "(\\d+(\\.\\d+)?)";

	public static final String ADD = TERM + PLUS + TERM;
	public static final String TIMES = TERM + MULTIPLY + TERM;
	public static final String DIVIDE = TERM + BY + TERM;

	public static final String PARENTHESIS = "(.*)\\(([^()]*)\\)(.*)";

	public String calculate(String expression) {
		CustomMatcher[] matchers = { new NumberMatcher(this),
				new ParenthesisMatcher(this), new AddMatcher(this),
				new MultiplyMatcher(this), new DivMatcher(this) };
		String result = "";
		int index = 0;
		while (StringUtils.isEmpty(result) && index < matchers.length) {
			result = matchers[index].calculate(expression);
			index++;
		}
		return result;
	}

	private abstract class CustomMatcher {
		protected ArithmeticParser parser;
		protected String toCompile;

		public CustomMatcher(ArithmeticParser parser) {
			this.parser = parser;
		}

		public String calculate(String expression) {
			String result = "";
			Matcher matcher = Pattern.compile(toCompile).matcher(expression);
			if (matcher.matches()) {
				result = handleMatcher(matcher);
			}
			System.out.println("expression: " + expression + " -- " + result);
			return result;
		}

		protected String groDessimalToResult(BigDecimal result) {
			try{
				return result.toBigIntegerExact().toString();
			 }catch (ArithmeticException e) {
				 e.printStackTrace();
			}
			return result.toString();
		}

		public abstract String handleMatcher(Matcher matcher);
	}

	private class NumberMatcher extends CustomMatcher {
		public NumberMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = NUMBER;
		}

		@Override
		public String calculate(String expression) {
			String result ="";
			try{
				result = new BigDecimal(expression).toString();
			}catch (NumberFormatException e) {
			}
			return result;
		}

		@Override
		public String handleMatcher(Matcher matcher) {
			return matcher.group(1);
		}
	}

	private abstract class OperatorMatcher extends CustomMatcher {
		public OperatorMatcher(ArithmeticParser parser) {
			super(parser);
		}

		protected abstract BigDecimal operation(BigDecimal leftmember,
				BigDecimal rightmember);

		@Override
		public String handleMatcher(Matcher matcher) {
			BigDecimal lmember = new BigDecimal(parser.calculate(matcher
					.group(1)));
			BigDecimal rmember = new BigDecimal(parser.calculate(matcher
					.group(2)));
			BigDecimal result = operation(lmember, rmember);
			return groDessimalToResult(result);
		}
	}

	private class MultiplyMatcher extends OperatorMatcher {
		public MultiplyMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = TIMES;
		}

		@Override
		protected BigDecimal operation(BigDecimal leftmember,
				BigDecimal rightmember) {
			return leftmember.multiply(rightmember);
		}
	}

	private class ParenthesisMatcher extends CustomMatcher {
		public ParenthesisMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = PARENTHESIS;
		}

		@Override
		public String handleMatcher(Matcher matcher) {
			return parser.calculate(matcher.group(1)
					+ parser.calculate(matcher.group(2)) + matcher.group(3));
		}
	}

	private class AddMatcher extends OperatorMatcher {
		public AddMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = ADD;
		}

		@Override
		protected BigDecimal operation(BigDecimal leftmember,
				BigDecimal rightmember) {
			return leftmember.add(rightmember);
		}
	}

	private class DivMatcher extends OperatorMatcher {
		public DivMatcher(ArithmeticParser parser) {
			super(parser);
			this.toCompile = DIVIDE;
		}

		@Override
		protected BigDecimal operation(BigDecimal leftmember,
				BigDecimal rightmember) {
			return leftmember.divide(rightmember);
		}
	}
}
