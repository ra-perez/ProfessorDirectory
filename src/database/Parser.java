package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Parser {
	//Go through information in TXT file
	//Create an ArrayList of type Professor
	//Create a new Professor object for every <item> - </item> chunk of text
	String filename = "ProfessorInfo";

	public Scanner read() throws FileNotFoundException{
		File f = new File(filename);
		Scanner s = new Scanner(f);
		while(s.hasNextLine()){ 
			String next = s.nextLine();
			System.out.println(next);
		}
		return s;
		
	}
	
	public Parser(){
		
	}

}
