package com.bzn.codestory13.stepone;

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

	public Change addFoo(){
		int newFoo = 1;
		if(foo!=null){
			newFoo+=foo;
		}
		return new Change(newFoo,bar,qix,baz);
	}
	
	public Change addbar(){
		int newBar = 1;
		if(bar!=null){
			newBar+=bar;
		}
		return new Change(foo,newBar,qix,baz);
	}
	public Change addQix(){
		int newQix = 1;
		if(qix!=null){
			newQix+=qix;
		}
		return new Change(foo,bar,newQix,baz);
	}
	public Change addBaz(){
		int newBaz = 1;
		if(baz!=null){
			newBaz+=baz;
		}
		return new Change(foo,bar,qix,newBaz);
	}
	@Override
	public String toString() {
		return "{\"foo\":"+foo+"\"bar\":"+bar+"\"qix\":"+qix+"\"baz\":"+baz+"}";
	}
}
