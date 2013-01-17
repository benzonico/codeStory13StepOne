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
			expected.add(new Change(i-Coins.bar.value,1));
			assertSizeAndContains(i, expected);
		}
	}
	
	@Test
	public void change11To13ShouldReturnAQix(){
		for (int i = 11; i < 14; i++) {
			List<Change> expected = new ArrayList<Change>();
			expected.add(new Change(i));
			expected.add(new Change(i-Coins.bar.value,1));
			expected.add(new Change(i-Coins.qix.value,0,1));
			assertSizeAndContains(i, expected);
		}
	}
	
	@Test
	public void change14to17ShouldReturnAQix(){
		for (int i = 14; i < 18; i++) {
			List<Change> expected = new ArrayList<Change>();
			expected.add(new Change(i));
			expected.add(new Change(i-Coins.bar.value,1));
			expected.add(new Change(i-2*Coins.bar.value,2));
			expected.add(new Change(i-Coins.qix.value,0,1));
			assertSizeAndContains(i, expected);
		}
	}
	
	@Test
	public void change18ShouldReturnAQixAndAbar(){
		List<Change> expected = new ArrayList<Change>();
		expected.add(new Change(18));
		expected.add(new Change(18-Coins.bar.value,1));
		expected.add(new Change(18-2*Coins.bar.value,2));
		expected.add(new Change(18-Coins.qix.value,0,1));
		expected.add(new Change(0,1,1));
		assertSizeAndContains(18, expected);
	}
	
	@Test
	public void changeOf19() throws Exception {
		List<Change> expected = new ArrayList<Change>();
		expected.add(new Change(19));
		expected.add(new Change(12,1));
		expected.add(new Change(5,2));
		expected.add(new Change(8,0,1));
		expected.add(new Change(1,1,1));
		assertSizeAndContains(19, expected);
		
	}
	@Test
	public void changeOf20() throws Exception {
		List<Change> expected = new ArrayList<Change>();
		expected.add(new Change(20));
		expected.add(new Change(13,1));
		expected.add(new Change(6,2));
		expected.add(new Change(9,0,1));
		expected.add(new Change(2,1,1));
		assertSizeAndContains(20, expected);
		
	}
	@Test
	public void changeOf21() throws Exception {
		List<Change> expected = new ArrayList<Change>();
		expected.add(new Change(21));
		expected.add(new Change(14,1));
		expected.add(new Change(7,2));
		expected.add(new Change(0,3));
		expected.add(new Change(10,0,1));
		expected.add(new Change(3,1,1));
		expected.add(new Change(0,0,0,1));
		assertSizeAndContains(21, expected);
	}
	@Test
	public void changeOf22() throws Exception {
		List<Change> expected = new ArrayList<Change>();
		expected.add(new Change(22));
		expected.add(new Change(15,1));
		expected.add(new Change(8,2));
		expected.add(new Change(1,3));
		expected.add(new Change(11,0,1));
		expected.add(new Change(4,1,1));
		expected.add(new Change(0,0,2));
		expected.add(new Change(1,0,0,1));
		assertSizeAndContains(22, expected);
	}

	
//	@Test
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
	
	@Test
	public void changeOf42(){
//		String expected = 
		List<Change> expected = new ArrayList<Change>();
		expected.add(new Change(6,2,2));
		expected.add(new Change(13,1,2));
		expected.add(new Change(20,0,2));
		expected.add(new Change(9,0,3));
		expected.add(new Change(7,2,0,1));
		expected.add(new Change(17,2,1));
		expected.add(new Change(0,6));
		expected.add(new Change(21,0,0,1));
		expected.add(new Change(14,1,0,1));
		expected.add(new Change(21,3));
		expected.add(new Change(10,3,1));
		expected.add(new Change(3,4,1));
		expected.add(new Change(7,5));
		expected.add(new Change(10,0,1,1));
		expected.add(new Change(31,0,1));
		expected.add(new Change(24,1,1));
		expected.add(new Change(14,4));
		expected.add(new Change(0,0,0,2));
		expected.add(new Change(3,1,1,1));
		expected.add(new Change(42));
		expected.add(new Change(2,1,3));
		expected.add(new Change(28,2));
		expected.add(new Change(0,3,0,1));
		expected.add(new Change(35,1));
		assertSizeAndContains(42, expected);
		
	}
	
	@Test
	public void changeOf96() throws Exception {
		echoppe.change(96);
	}
	@Test
	public void changeOf97() throws Exception {
		echoppe.change(97);
	}
	private void assertSizeAndContains(int change,List<Change> expected) {
		assertThat(echoppe.change(change)).hasSameSizeAs(expected).containsAll(expected);
	}
	
}
