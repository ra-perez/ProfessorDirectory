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
		        
        DatabaseMetaData meta = con.getMetaData();
        ResultSet res = meta.getTables(null, null, "Professors", 
           new String[] {"TABLE"});
        if (!res.next()) {
        	initializeProfessorDatabase();
        }
        
        res = meta.getTables(null, null, "Login", 
           new String[] {"TABLE"});
        if (!res.next()) {
        	initializeLoginDatabase();
        }
                
	}
	
	private void initializeProfessorDatabase() throws ClassNotFoundException {
		Parser p = new Parser();
		ArrayList<Professor> professors = new ArrayList<Professor>();
		try {
			professors = p.read();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
        	stat.executeUpdate("CREATE TABLE IF NOT EXISTS Professors (Name TEXT, Title TEXT, Department TEXT, Building TEXT, Phone TEXT, Email TEXT, Loc1 TEXT, Loc2 TEXT, AdditionalInfo TEXT)");
        	populateProfessorDatabase(professors);
        } catch (SQLException exception) {
        	exception.printStackTrace();
        	return;
        }
	}
	
	private void initializeLoginDatabase() throws ClassNotFoundException {
        try {
        	stat.executeUpdate("CREATE TABLE IF NOT EXISTS Login (Name TEXT, Password TEXT)");
        	populateLoginDatabase();
        } catch (SQLException exception) {
        	exception.printStackTrace();
        	return;
        }
	}
	
	public void populateProfessorDatabase(ArrayList<Professor> professors) throws ClassNotFoundException, SQLException {
        for (Professor p: professors) {
	        stat.executeUpdate("INSERT INTO Professors VALUES ('" +
	        	p.getName() + "', '" +
	            p.getTitle() + "', '" +
	       		p.getDepartment() + "', '" +
	            p.getBuilding() + "', '" +
	       		p.getPhone() + "', '" +
	       		p.getEmail() + "', '" +
	       		p.getLoc1() + "', '" +
	       		p.getLoc2() + "', '" +
	       		p.getAdditionalInfo() + "')"
	        );
		}
	}
	
	public void populateLoginDatabase() throws ClassNotFoundException, SQLException {
		ArrayList<String> names = getName();
		for (String name: names) {
			stat.executeUpdate("INSERT INTO Login VALUES ('" + name + "', 'admin')");
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
		
	public ArrayList<String> getDepartments() throws ClassNotFoundException, SQLException {return getColInfo("Department");}
		
	public ArrayList<String> getBuildings() throws ClassNotFoundException, SQLException {return getColInfo("Building");}
	
	public ArrayList<String> getFilteredNames(String department, String building) throws ClassNotFoundException, SQLException {
		boolean departmentSelected = !department.equals("") && department != null;
		boolean buildingSelected = !building.equals("") && building != null;
		if (departmentSelected && buildingSelected) {
			String query = "SELECT Name FROM Professors WHERE Department = '" + department +
					"' AND Building = '" + building + "'";
			return makeQuery(query);
		} else if (departmentSelected) {
			return getFilteredNamesDept(department);
		} else {
			return getFilteredNamesBuilding(building);
		}
	}
	
	public ArrayList<String> getFilteredNamesDept(String department) throws ClassNotFoundException, SQLException {
		String query;
		if (!department.equals("") && department != null) {
			query = "SELECT Name FROM Professors WHERE Department = '" + department + "'";
		} else {
			query = "SELECT Name FROM Professors";
		}
		return makeQuery(query);
	}
	
	public ArrayList<String> getFilteredNamesBuilding(String building) throws ClassNotFoundException, SQLException {
		String query;
		if (!building.equals("") && building != null) {
			query = "SELECT Name FROM Professors WHERE Building = '" + building + "'";
		} else {
			query = "SELECT Name FROM Professors";
		}
		return makeQuery(query);
	}
	
	public ArrayList<String> getName() throws ClassNotFoundException, SQLException {
		String query = "SELECT Name FROM Professors";
		return makeQuery(query);
	}
	
	public ArrayList<String> getInfoGivenName(String name, String column) throws ClassNotFoundException, SQLException {
		String query = "SELECT " + column + " FROM Professors WHERE Name = '" + name + "'";
		return makeQuery(query);
	}
	
	public void updateColumn(String name, String column, String update) throws ClassNotFoundException, SQLException {
		String updateCommand = "UPDATE Professors SET " + column + " = '" + update + "' WHERE Name = '" + name + "'";
		stat.executeUpdate(updateCommand);
	}
	
	public void changePassword(String name, String password) throws SQLException {
		String updateCommand = "UPDATE Login SET Password = '" + password + "' WHERE Name = '" + name + "'";
		stat.executeUpdate(updateCommand);
	}
	
	public boolean checkPassword(String name, String password) throws SQLException {
		String query = "SELECT * FROM Login WHERE Name = '" + name + "' AND Password = '" + password + "'";
		ResultSet rs = stat.executeQuery(query);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}
}
