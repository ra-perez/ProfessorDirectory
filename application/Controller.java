package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.Parser;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class Controller {
	
	@FXML
	Button getInfo;
	
	@FXML
	ChoiceBox <String> department;
	
	@FXML
	ChoiceBox <String> building;
	
	@FXML
	ListView<String> filteredNames;
	Database professorDB;
	ArrayList<String> buildingList1;
	ArrayList<String> buildingList2;
	ArrayList<String> departmentList;
	ArrayList<String> filteredNamesList;
	ObservableList<String> observableFilteredNamesList;
	ObservableList<String> observableDepartmentList;
	ObservableList<String> observableBuildingList;
	private String dep =  "";
	
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
		buildingList1 = professorDB.getBuildings1();
		buildingList2 = professorDB.getBuildings2();
		departmentList = professorDB.getDepartments();
		filteredNamesList = professorDB.getName();
		System.out.println(departmentList.size());
		populateLists();
		
		
	}
	
	private void populateLists() {
		populateBuilding();
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
	
	private void populateBuilding() {
		ObservableList<String> observableBuildingList = FXCollections.observableArrayList();
		for (String name: buildingList2) {
			observableBuildingList.add(name);
		}
		building.setItems(observableBuildingList);
	}
	
	private void populateDepartment() {
		ObservableList<String> observableDepartmentList = FXCollections.observableArrayList();
		for (String name: departmentList) {
			observableDepartmentList.add(name);
			
		}
		department.setItems(observableDepartmentList);
		
	}
	
	///////////////////////////////////////////////////////
	//This stores the string of the department name selected in the choice box
	public void getDepartment(){
		dep = department.getSelectionModel().getSelectedItem();
	}
	//This method just returns the stored department name
	public String getDep(){
		return dep;
	}
	//This method will call switchScreen for the pop up window, please take a look at the professorFile
	@FXML
	private void Joinpage() throws IOException{
		getDepartment();
		switchScreen("professorFile.fxml");
		//filteredNames.getSelectionModel().
	}
	
	private void switchScreen(String FXMLFile) throws IOException {
		Stage stage;
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource(FXMLFile));
		stage.setScene(new Scene(root));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(getInfo.getScene().getWindow());
		stage.show();
	}

}
