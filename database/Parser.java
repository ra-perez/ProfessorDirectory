package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Parser {
	//Go through information in TXT file
	//Create an ArrayList of type Professor
	//Create a new Professor object for every <item> - </item> chunk of text
	String filename = "ProfessorInfo";
	private String line = "";
	Professor professor = new Professor();
	
	public Parser(){
		
	}
	public void read() throws FileNotFoundException{
		File f = new File(filename);
		Scanner s = new Scanner(f);
		parsing(s);
	}
	
	private void parsing(Scanner s){
		while (s.hasNextLine()){
			line = s.nextLine();
			
			if(line.equals("  <item>")){
				String next = s.nextLine();//go to <pic>
				next = s.nextLine(); // go to <name>
				professor.setName(getName(next));
				
				next = s.nextLine(); //go to <title>
				professor.setTitle(getTitle(next));
				
				next = s.nextLine(); // go to <department>
				professor.setDepartment(getDepartment(next));
				
				next = s.nextLine(); // go to <phone>
				professor.setPhone(getPhone(next));
				
				next = s.nextLine(); // go to <email>
				professor.setEmail(getEmail(next));
				
				next = s.nextLine(); // go to <loc1>
				professor.setPhone(getLoc1(next));
				
				next = s.nextLine(); // go to <loc2>
				professor.setPhone(getLoc2(next));
					
			}
		}
		
	}
	private String getName(String s){	// remove <name> and </name>
		String[] temp = s.split("<name>");
		temp = temp[1].toString().split("</name>");
		s = temp[0].toString();
		return s;
	}
	
	private String getTitle(String s){	// remove <title> and </title>
		String[] temp = s.split("<title>");
		temp = temp[1].toString().split("</title>");
		s = temp[0].toString();
		return s;
	}
	
	private String getDepartment(String s){	// remove <department> and </department>
		String[] temp = s.split("<department>");
		temp = temp[1].toString().split("</department>");
		s = temp[0].toString();
		return s;
	}
	
	private String getPhone(String s){	// remove <phone> and </phone>
		String[] temp = s.split("<phone>");
		temp = temp[1].toString().split("</phone>");
		s = temp[0].toString();
		return s;
	}
	
	private String getEmail(String s){	// remove <email> and </email>
		String[] temp = s.split("<email>");
		temp = temp[1].toString().split("</email>");
		s = temp[0].toString();
		return s;
	}
	
	private String getLoc1(String s){	// remove <location_line1> and </location_line1>
		String[] temp = s.split("<location_line1>");
		temp = temp[1].toString().split("</location_line1>");
		s = temp[0].toString();
		return s;
	}
	
	private String getLoc2(String s){	// remove <location_line2> and </location_line2>
		String[] temp = s.split("<location_line2>");
		temp = temp[1].toString().split("</location_line2>");
		s = temp[0].toString();
		return s;
	}
	
	

}
