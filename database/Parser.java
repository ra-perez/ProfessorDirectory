package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Parser {
	
	//Make list of all known buildings (check the file to see how they are spelled/if there are variations)
		//EX: MCRey vs MC Reynolds
	//for (String building: buildings) 
		//if loc1.contains building || loc2.contains building
			//that buliding is that professor's building, professor.setBuilding(building)
	
	private String fileName;
	private String line = "";
	private String next = "";
	private ArrayList<Professor> professorList;
	private ArrayList<String> buildings;
	
	public Parser(){
		fileName = "ProfessorInfo";
		professorList = new ArrayList<Professor>();
		populateBuildingList();
	}
	
	public void populateBuildingList() {
		buildings = new ArrayList<String>();
		buildings.add("WAC");
		buildings.add("Mills");
		buildings.add("RANEY");
		buildings.add("Village");
		buildings.add("Fausett");
		buildings.add("Ellis");
		buildings.add("SLTC");
		buildings.add("MCREY");
		buildings.add("DWREY");
		buildings.add("TRIES");
		buildings.add("MCACX");
		buildings.add("REYNO");
		buildings.add("ART");
		buildings.add("Bailey Library");
	}
	
	public ArrayList<Professor> read() throws FileNotFoundException{
		File f = new File(fileName);
		Scanner s = new Scanner(f);
		return parsing(s);
	}
	
	private ArrayList<Professor> parsing(Scanner s){
		while (s.hasNextLine()){
			String loc1;
			String loc2;
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
				loc1 = next;
				professor.setLoc1(getItem(next, "location_line1"));

				next = s.nextLine();
				loc2 = next;
				professor.setLoc2(getItem(next, "location_line2"));

				//write for loop above here
				
				professorList.add(professor);
			}
		}
		return professorList;
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
