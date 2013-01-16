package com.bzn.codestory13.stepone;

public enum Coins {
	foo(1, new Change(1)),
	bar(7,new Change(0,1)),
	qix(11,new Change(0,0,1)),
	baz(21,new Change(0,0,0,1));
	
	public int value;
	public Change uniqueChange;
	
	private Coins(int value,Change uniqueChange) {
		this.value = value;
		this.uniqueChange = uniqueChange;
	}
}
