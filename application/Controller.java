package application;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	ObservableList<String> observableFilteredNamesList;
	
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
		filteredNamesList = professorDB.getAllNames();
	}
	
	private void populateLists() {
		//populateBuilding();
		//populateDepartment();
		populateFilteredNames();
	}
	
	private void populateFilteredNames() {
		ObservableList<String> observableFilteredNamesList = FXCollections.observableArrayList();
		for (String name: filteredNamesList) {
			observableFilteredNamesList.add(name);
		}
		filteredNames = new ListView<String>(observableFilteredNamesList);
	}
}