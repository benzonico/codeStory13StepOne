package com.bzn.codestory13.stepone;

public enum Coins {
	foo(1),
	bar(7),
	qix(11),
	baz(21);
	
	public int value;
	
	private Coins(int value) {
		this.value = value;
	}
}
