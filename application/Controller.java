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
	ListView<String> filteredNames;
	Database professorDB;
	ArrayList<String> buildingList;
	ArrayList<String> departmentList;
	ArrayList<String> filteredNamesList;
	ObservableList<String> observableFilteredNamesList;
	ObservableList<String> observableDepartmentList;

	
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
		//populateDepartment();
		populateFilteredNames();
	}
	
	private void populateFilteredNames() {
		ObservableList<String> observableFilteredNamesList = FXCollections.observableArrayList();
		for (String name: filteredNamesList) {
			observableFilteredNamesList.add(name);
		}
		System.out.println(filteredNamesList);
		filteredNames.setItems(observableFilteredNamesList);

	}
	
	/*private void populatebuilding() {
		ObservableList<String> observableBuilding = FXCollections.observableArrayList();
		for (String name: buildingList) {
			observableBuilding.add(name);
			
		}
	}*/
	
	/*private void populateDepartment() {
		ObservableList<String> observableDepartmentList = FXCollections.observableArrayList();
		for (String name: departmentList) {
			observableDepartmentList.addAll(name);
			department = new ObservableList<MenuItem> getItems();
		}
		
	}
	
	public void MenuButton() {
		ObservableList<String> observableDepartmentList = FXCollections.observableArrayList();
		for (String name: departmentList) {
			observableDepartmentList.add(name);
		}
		department.getItems();
	}*/
}
