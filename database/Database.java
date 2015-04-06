package database;

import java.util.ArrayList;
import java.sql.*;

public class Database {
	private ArrayList<Professor> professors;
	private Statement stat;
	
	public Database(ArrayList<Professor> p) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:professors.db");
        stat = con.createStatement();
		professors = p;
		
		initializeDatabase();
	}
	
	private void initializeDatabase() throws ClassNotFoundException {
        try {
        	stat.executeUpdate("CREATE TABLE Professors (Name STRING, Title STRING, Department STRING, Phone STRING, Email STRING, Loc1 STRING, Loc2 STRING)");
        	populateDatabase();
        } catch (SQLException exception) {
        	return;
        }

	}
	
	public void populateDatabase() throws ClassNotFoundException, SQLException {
        stat.executeUpdate("INSERT INTO Professors VALUE ('Nikola Tesla', 'Professor of Science', 'Sciencey Science', '(900) 817 - 9234', 'tesla@hendrix.edu', 'The Moon', 'Literally The Moon')");
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
	
	//public ArrayList<String> getBuildings() throws ClassNotFoundException, SQLException {return getColInfo("Building");}
	public ArrayList<String> getDepartments() throws ClassNotFoundException, SQLException {return getColInfo("Department");}
	
	//Below commented out due to issues with parsing Building information from file
	//Give an empty string for input variables if not filtered on that information
	//So getFilteredNames("", "") returns all Professor Names
	/*public ArrayList<String> getFilteredNames(String building, String department) throws ClassNotFoundException, SQLException {
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
	}*/
	
	public ArrayList<String> getFilteredNames(String department) throws ClassNotFoundException, SQLException {
		String query = "SELECT Name FROM Professors WHERE Department = '" + department + "'";
		return makeQuery(query);
	}
	
	public ArrayList<String> getAllNames() throws ClassNotFoundException, SQLException {
		String query = "SELECT Name FROM Professors";
		return makeQuery(query);
	}
}
