package database;

import java.util.ArrayList;
import java.sql.*;

public class Database {
	private ArrayList<Professor> professors;
	
	public Database(ArrayList<Professor> p) {
		professors = p;
	}
	
	public void initializeDatabase() throws ClassNotFoundException, SQLException {
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
	
	private ArrayList<String> makeQuery(String query) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:testdb");
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(query);
        ArrayList<String> toReturn = new ArrayList<String>();
        while (rs.next()) {
        	toReturn.add(rs.getString("Building"));
        }
        return toReturn;
	}
	
	private ArrayList<String> getColInfo(String column) throws ClassNotFoundException, SQLException {
        return makeQuery("SELECT " + column + " FROM Professors");
	}
	
	public ArrayList<String> getBuildings() throws ClassNotFoundException, SQLException {return getColInfo("Building");}
	public ArrayList<String> getDepartments() throws ClassNotFoundException, SQLException {return getColInfo("Department");}
		
	//Give an empty string for input variables if not filtered on that information
	//So getFilteredNames("", "") returns all Professor Names
	public ArrayList<String> getFilteredNames(String building, String department) throws ClassNotFoundException, SQLException {
		String query = "";
		if (department.equals("")) {
			query = "SELECT Name FROM Professors WHERE Building = '" + building + "'";
		} else if (building.equals("")) {
			query = "SELECT Name FROM Professors WHERE Department = '" + department + "'";
		} else if (!department.equals("") && !building.equals("")) {
			query = "SELECT Name FROM Professors WHERE Building = '" + building + "' AND WHERE Department = '" + department + "'";
		} else {
			query = "SELECT Name FROM Professors";
		}
		return makeQuery(query);
	}

}
