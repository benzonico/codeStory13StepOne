package com.bzn.codestory13.stepone.scalaskel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Change {
	
	public Change(Integer foo) {
		if(foo !=null && foo!=0){
			this.foo = foo;
		}
	}
	
	public Change(Integer foo, Integer bar) {
		this(foo);
		if(bar != null && bar!=0){
			this.bar = bar;
		}
	}

	public Change(Integer foo, Integer bar, Integer qix) {
		this(foo,bar);
		if(qix!=null && qix!=0){
			this.qix = qix;
		}
	}

	public Change(Integer foo, Integer bar, Integer qix, Integer baz) {
		this(foo, bar, qix);
		if(baz != null && baz!=0){
			this.baz= baz;
		}
	}

	private Integer foo = null;
	private Integer bar = null;
	private Integer qix = null;
	private Integer baz = null;
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	public Change addChange(Change change){
		int newFoo = computeSum(change.foo,foo);
		int newBar = computeSum(change.bar,bar);
		int newQix = computeSum(change.qix,qix);
		int newBaz = computeSum(change.baz,baz);
		return new Change(newFoo,newBar,newQix,newBaz);
	}
	private int computeSum(Integer int1, Integer int2) {
		int result = 0;
		if(int1!=null){
			result = int1.intValue();
		}
		if(int2!=null){
			result+=int2.intValue();
		}
		return result;
	}

	@Override
	public String toString() {
		return "{\"foo\":"+foo+"\"bar\":"+bar+"\"qix\":"+qix+"\"baz\":"+baz+"}";
	}
}
