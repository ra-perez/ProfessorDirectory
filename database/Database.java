package database;

import java.util.ArrayList;
import java.sql.*;

public class Database {
	private ArrayList<Professor> professors;
	
	public Database(ArrayList<Professor> p) {
		professors = p;
	}
	
	private void initializeDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:professors.db");
        Statement stat = con.createStatement();

        stat.executeUpdate("DROP TABLE IF EXISTS Professors");
        stat.executeUpdate("CREATE TABLE Professors (Name STRING, Title STRING, Department STRING, Phone STRING, Email STRING, Loc1 STRING, Loc2 STRING)");
		for (Professor p: professors) {
	        stat.executeUpdate("INSERT INTO Professors VALUE (" +
	        	p.getName() + ", " +
	        	p.getTitle() + ", " +
	       		p.getDepartment() + ", " +
	       		p.getPhone() + ", " +
	       		p.getEmail() + ", " +
	       		p.getLoc1() + ", " +
	       		p.getLoc2() + ")"
	        );
		}
	}
		
	public ArrayList<String> getColInfo(String column) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:testdb");
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT " + column + " FROM Professors");
        ArrayList<String> toReturn = new ArrayList<String>();
        while (rs.next()) {
        	toReturn.add(rs.getString("Building"));
        }
        return toReturn;
	}
	
	public ArrayList<String> getBuildings() throws ClassNotFoundException, SQLException {return getColInfo("Building");}
	public ArrayList<String> getDepartments() throws ClassNotFoundException, SQLException {return getColInfo("Department");}

}
