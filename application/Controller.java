package application;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;



public class Controller {
	
	@FXML
	ChoiceBox <String> department;
	
	@FXML
	ChoiceBox <String> building;
	
	@FXML
	ListView<String> filteredNames;
	Database professorDB;
	ArrayList<String> buildingList;
	ArrayList<String> departmentList;
	ArrayList<String> filteredNamesList;
	ObservableList<String> observableFilteredNamesList;
	ObservableList<String> observableDepartmentList;
	ObservableList<String> observableBuildingList;
	
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
		//buildingList = professorDB.getBuildings();
		departmentList = professorDB.getDepartments();
		filteredNamesList = professorDB.getName();
		System.out.println(departmentList.size());
		populateLists();
		
		
	}
	
	private void populateLists() {
		//populateBuilding();
		populateDepartment();
		populateFilteredNames();
	}
	
	private void populateFilteredNames() {
		ObservableList<String> observableFilteredNamesList = FXCollections.observableArrayList();
		for (String name: filteredNamesList) {
			observableFilteredNamesList.add(name);
		}
		filteredNames.setItems(observableFilteredNamesList);

	}
	
	/*private void populateBuilding() {
		ObservableList<String> observableBuildingList = FXCollections.observableArrayList();
		for (String name: buildingList) {
			observableBuildingList.add(name);
			
		}
		building.setItems(observableBuildingList);
	}*/
	
	private void populateDepartment() {
		ObservableList<String> observableDepartmentList = FXCollections.observableArrayList();
		for (String name: departmentList) {
			observableDepartmentList.add(name);
			
		}
		department.setItems(observableDepartmentList);
		
	}

}
