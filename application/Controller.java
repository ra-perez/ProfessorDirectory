package application;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;


public class Controller {
	
	@FXML
	SplitMenuButton building;
	@FXML
	SplitMenuButton department;
	@FXML
	ListView<String> filteredNames;
	Database professorDB;
	ArrayList<String> buildingList;
	ArrayList<String> departmentList;
	ArrayList<String> filteredNamesList;
	
	@FXML
	private void initialize() throws ClassNotFoundException, SQLException {
		Parser p = new Parser();
		try {
			p.read();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		professorDB = new Database(p.getProfessors());
		buildingList = professorDB.getBuildings();
		departmentList = professorDB.getDepartments();
		filteredNamesList = professorDB.getFilteredNames("", "");
	}
}