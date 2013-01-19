package com.bzn.codestory13.stepone.jajascript;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Flight {

	public String VOL;
	public long DEPART;
	public long DUREE;
	public long PRIX;
	
	
	public Flight(String vol, long depart, long duree, long prix) {
		super();
		VOL = vol;
		DEPART = depart;
		DUREE = duree;
		PRIX = prix;
	}
	
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	
}
