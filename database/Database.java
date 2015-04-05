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
        	stat.executeUpdate("DROP TABLE IF EXISTS Professors");
        	
        	stat.executeUpdate("CREATE TABLE IF NOT EXISTS Professors (Name TEXT, Title TEXT, Department TEXT, Phone TEXT, Email TEXT, Loc1 TEXT, Loc2 TEXT)");
        	populateDatabase();
        } catch (SQLException exception) {
        	exception.printStackTrace();
        	return;
        }

	}
	
	public void populateDatabase() throws ClassNotFoundException, SQLException {
        //stat.executeUpdate("INSERT INTO Professors VALUES ('Nikola Tesla', 'Professor of Science', 'Sciencey Science', '(900) 817 - 9234', 'tesla@hendrix.edu', 'The Moon', 'Literally The Moon')");
        for (Professor p: professors) {
	        stat.executeUpdate("INSERT INTO Professors VALUES ('" +
	        	p.getName() + "', '" +
	            p.getTitle() + "', '" +
	       		p.getDepartment() + "', '" +
	       		p.getPhone() + "', '" +
	       		p.getEmail() + "', '" +
	       		p.getLoc1() + "', '" +
	       		p.getLoc2() + "')"
	        );
		}
	}
	
	private ArrayList<String> makeQuery(String query) throws ClassNotFoundException, SQLException {
        ResultSet rs = stat.executeQuery(query);
        ArrayList<String> toReturn = new ArrayList<String>();
        while (rs.next()) {
        	toReturn.add(rs.getString(1));
        }
        return toReturn;
	}
	
	private ArrayList<String> getColInfo(String column) throws ClassNotFoundException, SQLException {
        return makeQuery("SELECT " + column + " FROM Professors GROUP BY " + column);
	}
	
	//private ArrayList<String> getColInfo2(String column) throws ClassNotFoundException, SQLException {
      //  return makeQuery("SELECT " + column + " FROM Professors WHERE Name = 'Susan Ablondi'");
	//}
	
	public ArrayList<String> getBuildings1() throws ClassNotFoundException, SQLException {return getColInfo("Loc1");}
	public ArrayList<String> getBuildings2() throws ClassNotFoundException, SQLException {return getColInfo("Loc2");}
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
	public ArrayList<String> getName() throws ClassNotFoundException, SQLException {
		String query = "SELECT Name FROM Professors GROUP BY Name";
		return makeQuery(query);
	}
}
