package com.bzn.codestory13.stepone.jajascript;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Flight implements Comparable<Flight>{

	public String VOL;
	public int DEPART;
	public int DUREE;
	public int PRIX;
	
	public int arrivee(){
		return DEPART+DUREE;
	}
	
	public Flight(String vol, int depart, int duree, int prix) {
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

	@Override
	public int compareTo(Flight o) {
		return new CompareToBuilder()
					.append(arrivee(), o.arrivee())
					.append(DUREE, o.DUREE)
					.append(o.PRIX, PRIX)
					.append(VOL, o.VOL)
					.toComparison();
	}
	
}
