package database;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestParser {
	Parser p = new Parser();
	private String head = "";
	private String tail = "";
	@Test
	public void testgetContentName()  {
		head = "<name>";
		tail = "</name>";
		assertTrue(p.getContent("    <name>Susan Ablondi</name>", head, tail).equals("Susan Ablondi"));
	}
	@Test
	public void testgetContentTitle() {
		head = "<title>";
		tail = "</title>";
		assertTrue(p.getContent("    <title>Noyce Program Manager</title>", head, tail).equals("Noyce Program Manager"));
	}
	
	@Test
	public void testgetContentDepartment(){
		head = "<department>";
		tail = "</department>";
		assertTrue(p.getContent("    <department>Education</department>", head, tail).equals("Education"));
	}
	
	@Test
	public void testgetContentPhone(){
		head = "<phone>";
		tail = "</phone>";
		assertTrue(p.getContent("    <phone>(501) 450-1379</phone>", head, tail).equals("(501) 450-1379"));
	}
	
	@Test
	public void testgetContentEmail(){
		head = "<email>";
		tail = "</email>";
		assertTrue(p.getContent("    <email>ablondis@hendrix.edu</email>", head, tail).equals("ablondis@hendrix.edu"));
	}
	
	@Test
	public void testgetContentloc1(){
		head = "<location_line1>";
		tail = "</location_line1>";
		assertTrue(p.getContent("    <location_line1>Education Department</location_line1>", head, tail).equals("Education Department"));
	}
	
	@Test
	public void testgetContentloc2(){
		head = "<location_line2>";
		tail = "</location_line2>";
		assertTrue(p.getContent("    <location_line2>Mills 105</location_line2>", head, tail).equals("Mills 105"));
	}

}
