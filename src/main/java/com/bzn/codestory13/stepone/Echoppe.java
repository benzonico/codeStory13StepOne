package com.bzn.codestory13.stepone;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Echoppe extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(change(Integer.parseInt(req.getPathInfo().substring(1))));
		resp.getWriter().println(json);
	}
	
	public Set<Change> change(int value) {
		if(value<1 || value>100){
			throw new IllegalArgumentException(value+" should be between 1 and 100");
		}
		return change(value, new HashSet<Change>());
	}
	
	public Set<Change> change(int value,Set<Change> changes) {
		if(value==0){
			return changes;
		}
		Set<Change> result = new HashSet<Change>();
		if(changes.size()==0){
			for (int i = 0; i < Coins.values().length; i++) {
				Coins coin = Coins.values()[i];
				if(value%coin.value==0){	
					Set<Change> temp = new HashSet<Change>();
					temp.add(coin.uniqueChange);
					result.addAll(change(value-coin.value,temp));
				}
			}
		}else{
			for(Change change : changes){
				if(value%Coins.baz.value==0){
					Set<Change> temp = new HashSet<Change>();
					temp.add(change.addBaz());
					result.addAll(change(value-Coins.baz.value,temp));
				}
				if(value%Coins.qix.value==0){
					Set<Change> temp = new HashSet<Change>();
					temp.add(change.addQix());
					result.addAll(change(value-Coins.qix.value,temp));
				}
				if(value%Coins.bar.value==0){
					Set<Change> temp = new HashSet<Change>();
					temp.add(change.addbar());
					result.addAll(change(value-Coins.bar.value,temp));
				}
				if(value%Coins.foo.value==0){
					Set<Change> temp = new HashSet<Change>();
					temp.add(change.addFoo());
					result.addAll(change(value-Coins.foo.value,temp));
				}
			}
		}
		return result;
	}
}
