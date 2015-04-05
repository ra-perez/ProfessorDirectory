package database;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestParser {
	Parser p = new Parser();
	
	@Test
	public void testgetContentName()  {
		assertTrue(p.getItem("    <name>Susan Ablondi</name>", "name").equals("Susan Ablondi"));
	}
	@Test
	public void testgetContentTitle() {
		assertTrue(p.getItem("    <title>Noyce Program Manager</title>", "title").equals("Noyce Program Manager"));
	}
	
	@Test
	public void testgetContentDepartment(){
		assertTrue(p.getItem("    <department>Education</department>", "department").equals("Education"));
	}
	
	@Test
	public void testgetContentPhone(){
		assertTrue(p.getItem("    <phone>(501) 450-1379</phone>", "phone").equals("(501) 450-1379"));
	}
	
	@Test
	public void testgetContentEmail(){
		assertTrue(p.getItem("    <email>ablondis@hendrix.edu</email>", "email").equals("ablondis@hendrix.edu"));
	}
	
	@Test
	public void testgetContentloc1(){
		assertTrue(p.getItem("    <location_line1>Education Department</location_line1>", "location_line1").equals("Education Department"));
	}
	
	@Test
	public void testgetContentloc2(){
		assertTrue(p.getItem("    <location_line2>Mills 105</location_line2>", "location_line2").equals("Mills 105"));
	}

}
