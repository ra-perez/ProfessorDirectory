package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Parser {
	
	private String fileName;
	private String line = "";
	private String next = "";
	private ArrayList<Professor> professorList;
	
	public Parser(){
		fileName = "ProfessorInfo";
		professorList = new ArrayList<Professor>();
	}
	public void read() throws FileNotFoundException{
		File f = new File(fileName);
		Scanner s = new Scanner(f);
		parsing(s);
	}
	
	private void parsing(Scanner s){
		while (s.hasNextLine()){
			line = s.nextLine();
			
			if(line.equals("  <item>")){
				Professor professor = new Professor();
				
				next = s.nextLine();//go to <pic>
				
				next = s.nextLine(); 					// go to <name>
				professor.setName(getItem(next, "name"));

				next = s.nextLine(); 					//go to <title>
				professor.setTitle(getItem(next, "title"));

				next = s.nextLine(); 					// go to <department>
				professor.setDepartment(getItem(next, "department"));

				next = s.nextLine(); 					// go to <phone>
				professor.setPhone(getItem(next, "phone"));

				next = s.nextLine(); 					// go to <email>
				professor.setEmail(getItem(next, "email"));

				next = s.nextLine(); 					// go to <loc1>
				professor.setLoc1(getItem(next, "location_line1"));

				next = s.nextLine();					 // go to <loc2>
				professor.setLoc2(getItem(next, "location_line2"));

				professorList.add(professor);
			}
		}
		
	}
	
	public String getItem(String s, String item) {	// remove <name> and </name> and so on
		String[] temp = s.split("<" + item + ">");
		if(temp[1].equals("</" + item + ">")){
			s = "";
			return s;
		}
		temp = temp[1].toString().split("</" + item + ">");
		s = temp[0].toString();
		
		return s;
	}

	
	public ArrayList<Professor> getProfessors() {return professorList;}
	
}
