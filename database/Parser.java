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
				
				next = s.nextLine();
				
				next = s.nextLine();
				professor.setName(getItem(next, "name"));

				next = s.nextLine();
				professor.setTitle(getItem(next, "title"));

				next = s.nextLine();
				professor.setDepartment(getItem(next, "department"));

				next = s.nextLine();
				professor.setPhone(getItem(next, "phone"));

				next = s.nextLine();
				professor.setEmail(getItem(next, "email"));

				next = s.nextLine();
				professor.setLoc1(getItem(next, "location_line1"));

				next = s.nextLine();
				professor.setLoc2(getItem(next, "location_line2"));

				professorList.add(professor);
			}
		}
		
	}
	
	public String getItem(String s, String item) {
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
