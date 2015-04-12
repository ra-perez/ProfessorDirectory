package database;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.sql.*;

public class Database {
	private Statement stat;
	
	public Database() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:professors.db");
        stat = con.createStatement();
		
        //ResultSet r = stat.executeQuery("SHOW DATABASES LIKE 'Professors'");
        //if (!r.next()) {
    		initializeDatabase();
        //}
	}
	
	private void initializeDatabase() throws ClassNotFoundException {
		Parser p = new Parser();
		ArrayList<Professor> professors = new ArrayList<Professor>();
		try {
			p.read();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {        	
        	stat.executeUpdate("CREATE TABLE IF NOT EXISTS Professors (Name TEXT, Title TEXT, Department TEXT, Phone TEXT, Email TEXT, Loc1 TEXT, Loc2 TEXT)");
        	populateDatabase(professors);
        } catch (SQLException exception) {
        	exception.printStackTrace();
        	return;
        }

	}
	
	public void populateDatabase(ArrayList<Professor> professors) throws ClassNotFoundException, SQLException {
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
		
	public ArrayList<String> getBuildings1() throws ClassNotFoundException, SQLException {return getColInfo("Loc1");}
	public ArrayList<String> getBuildings2() throws ClassNotFoundException, SQLException {return getColInfo("Loc2");}
	public ArrayList<String> getDepartments() throws ClassNotFoundException, SQLException {return getColInfo("Department");}
		
	public ArrayList<String> getFilteredNames(String department) throws ClassNotFoundException, SQLException {
		String query = "SELECT Name FROM Professors WHERE Department = '" + department + "'";
		return makeQuery(query);

	}
	public ArrayList<String> getName() throws ClassNotFoundException, SQLException {
		String query = "SELECT Name FROM Professors GROUP BY Name";
		return makeQuery(query);
	}
	
	public ArrayList<String> getInfoGivenName(String name, String column) throws ClassNotFoundException, SQLException {
		String query = "SELECT " + column + " FROM Professors WHERE Name = '" + name + "'";
		return makeQuery(query);
	}
	
}
