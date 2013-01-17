package com.bzn.codestory13.stepone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Echoppe extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int valueToChange  =Integer.parseInt(req.getPathInfo().substring(1));
		Gson gson = new Gson();
		String json = gson.toJson(change(valueToChange));
		GoogleMail.SendForCodeStory("Change request Received "+valueToChange, "Reponse Sent :"+json);
		resp.getWriter().println(json);
	}
	
	public List<Change> change(int value){
		if(value<1 || value>100){
			throw new IllegalArgumentException(value+" should be between 1 and 100");
		}
		List<Change> list = new ArrayList<Change>();
		list.add(new Change(value));
		
		list.addAll(computeQixGivenBaz(value, 0));
		if(Coins.baz.value<=value){
			int nbBaz = 1;
			while (nbBaz*Coins.baz.value<=value) {
				int newValue=value-nbBaz*Coins.baz.value;
				list.add(new Change(newValue,0,0,nbBaz));
				list.addAll(computeQixGivenBaz(newValue, nbBaz));
				nbBaz++;
			}
		}
		return list;
	}
	
	private List<Change> computeBarGivenQixAndBaz(int value, int qix, int baz){
		List<Change> result = new ArrayList<Change>();
		if(Coins.bar.value<=value){
			int index = 1;
			while (index*Coins.bar.value<=value) {
				result.add(new Change(value-index*Coins.bar.value,index,qix,baz));
				index++;
			}
		}
		return result;
	}
	
	private List<Change> computeQixGivenBaz(int value, int baz){
		List<Change> result = new ArrayList<Change>();
		result.addAll(computeBarGivenQixAndBaz(value, 0,baz));
		if(Coins.qix.value<=value){
			
			int indexQix = 1;
			while (indexQix*Coins.qix.value<=value) {
				result.add(new Change(value-indexQix*Coins.qix.value,0,indexQix,baz));
				result.addAll(computeBarGivenQixAndBaz(value-indexQix*Coins.qix.value, indexQix,baz));
				indexQix++;
			}
		}
		return result;
	}
}
