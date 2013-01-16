package com.bzn.codestory13.stepone;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class EchoppeTest {

	private Echoppe echoppe = new Echoppe();
	
	
	@Test
	public void changeIntBelow0ShouldThrowIllegalArgumentException() {
		try{
			echoppe.change(0);
			fail("Exception Should Have Been Thrown");
		}catch (Exception e) {
			assertThat(e).isInstanceOf(IllegalArgumentException.class);
		}
	}  
	
	@Test
	public void changeIntOver100ShouldThrowIllegalArgumentException() {
		try{
			echoppe.change(101);
			fail("Exception Should Have Been Thrown");
		}catch (Exception e) {
			assertThat(e).isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	@Test
	public void change1To7ShouldBeXfoo() {
		for (int i = 1; i < 7; i++) {
			List<Change> expected = new ArrayList<Change>();
			expected.add(new Change(i));
			assertSizeAndContains(i, expected);
		}
	}
	
	@Test
	public void changeFrom7to10ShouldReturn1BazAndSomeFooAndXFoo() throws Exception {
		for (int i = 7; i < 11; i++) {
			List<Change> expected = new ArrayList<Change>();
			expected.add(new Change(i));
			expected.add(new Change(i-7,1));
			assertSizeAndContains(i, expected);
		}
	}
	
	@Test
	public void change11ShouldReturnAQix(){
		List<Change> expected = new ArrayList<Change>();
		expected.add(new Change(11));
		expected.add(new Change(11-Coins.bar.value,1));
		expected.add(new Change(0,0,1));
		assertSizeAndContains(11, expected);
	}
	@Test
	public void change12ShouldReturnAQix(){
		List<Change> expected = new ArrayList<Change>();
		expected.add(new Change(12));
		expected.add(new Change(12-Coins.bar.value,1));
		expected.add(new Change(1,0,1));
		assertSizeAndContains(12, expected);
	}
	
	@Test
	public void changeOf23() throws Exception {
		List<Change> expected = new ArrayList<Change>();
		expected.add(new Change(23));
		expected.add(new Change(16,1));
		expected.add(new Change(9,2));
		expected.add(new Change(2,3));
		expected.add(new Change(12,0,1));
		expected.add(new Change(5,1,1));
		expected.add(new Change(1,0,2));
		expected.add(new Change(2,0,0,1));
		assertSizeAndContains(23, expected);
		
	}

	private void assertSizeAndContains(int change,List<Change> expected) {
		assertThat(echoppe.change(change)).hasSameSizeAs(expected).containsAll(expected);
	}
	
}
