package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Parser {
	//Go through information in TXT file
	//Create an ArrayList of type Professor
	//Create a new Professor object for every <item> - </item> chunk of text
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
				String head = "";
				String tail = "";
				
				next = s.nextLine();//go to <pic>
				
				next = s.nextLine(); 					// go to <name>
				head = "<name>"; tail = "</name>";
				professor.setName(getContent(next, head, tail));

				next = s.nextLine(); 					//go to <title>
				head = "<title>"; tail = "</title>";
				professor.setTitle(getContent(next, head, tail));

				next = s.nextLine(); 					// go to <department>
				head = "<department>"; tail = "</department>";
				professor.setDepartment(getContent(next, head, tail));

				next = s.nextLine(); 					// go to <phone>
				head = "<phone>"; tail = "</phone>";
				professor.setPhone(getContent(next, head, tail));

				next = s.nextLine(); 					// go to <email>
				head = "<email>"; tail = "</email>";
				professor.setEmail(getContent(next, head, tail));

				next = s.nextLine(); 					// go to <loc1>
				head = "<location_line1>"; tail = "</location_line1>";
				professor.setLoc1(getContent(next, head, tail));

				next = s.nextLine();					 // go to <loc2>
				head = "<location_line2>"; tail = "</location_line2>";
				professor.setLoc2(getContent(next, head, tail));

				professorList.add(professor);
			}
		}
		
	}
	
	public String getContent(String s, String head, String tail){	// remove <name> and </name> and so on
		String[] temp = s.split(head);
		temp = temp[1].toString().split(tail);
		s = temp[0].toString();
		return s;
	}
	
	public ArrayList<Professor> getProfessors() {return professorList;}
	
}
