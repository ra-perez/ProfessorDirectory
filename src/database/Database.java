package database;

import java.util.ArrayList;
import java.sql.*;

public class Database {
	private ArrayList<Professor> professors;
	private ArrayList<String> buildings, departments;
	
	public Database(ArrayList<Professor> p) {
		professors = p;
		buildings = new ArrayList<String>();
		departments = new ArrayList<String>();
	}
	
	private void initializeDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:testdb");
        Statement stat = con.createStatement();

		for (Professor p: professors) {
			
		}
	}
	
	public ArrayList<String> getBuildings() {return buildings;}
	public ArrayList<String> getDepartments() {return departments;}

}
