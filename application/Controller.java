package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
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
	
	@FXML
	TextArea professorInfo;
	
	Database professorDB;
	ArrayList<String> departmentList;
	ArrayList<String> buildingList;
	ArrayList<String> filteredNamesList;
	ObservableList<String> observableFilteredNamesList = FXCollections.observableArrayList();
	ObservableList<String> observableDepartmentList;
	ObservableList<String> observableBuildingList;
	private String dep =  "";
	
	@FXML
	private void initialize() throws ClassNotFoundException, SQLException {
		professorDB = new Database();
		departmentList = professorDB.getDepartments();
		buildingList = professorDB.getBuildings();
		filteredNamesList = professorDB.getName();
		populateLists();
		professorInfo.setWrapText(true);
		
		department.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_val) {
				try {
					filteredNamesList = professorDB.getFilteredNamesDept(departmentList.get(new_val.intValue()));
					populateFilteredNames();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		building.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_val) {
				try {
					filteredNamesList = professorDB.getFilteredNamesDept(buildingList.get(new_val.intValue()));
					populateFilteredNames();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});

	}
	
	private void populateLists() {
		populateBuilding();
		populateDepartment();
		populateFilteredNames();
	}
		
	
	private void populateFilteredNames() {
		observableFilteredNamesList.clear();
		for (String name: filteredNamesList) {
			observableFilteredNamesList.add(name);
		}
		filteredNames.setItems(observableFilteredNamesList);
	}
	
	private void populateBuilding() {
		ObservableList<String> observableBuildingList = FXCollections.observableArrayList();
		for (String name: buildingList) {
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
	
	public void getDepartment(){
		dep = department.getSelectionModel().getSelectedItem();
	}
	
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
	
	@FXML
	public void handleMouseClick(MouseEvent arg0) throws ClassNotFoundException, SQLException {
		String info = writeText();
		professorInfo.setText(info);
	}
	
	public String writeText() throws ClassNotFoundException, SQLException {
		String name = filteredNames.getSelectionModel().getSelectedItem();
		String department = professorDB.getInfoGivenName(name, "Department").get(0);
		String title = professorDB.getInfoGivenName(name, "Title").get(0);
		String email = professorDB.getInfoGivenName(name, "Email").get(0);
		String phone = professorDB.getInfoGivenName(name, "Phone").get(0);
		String loc1 = professorDB.getInfoGivenName(name, "Loc1").get(0);
		String loc2 = professorDB.getInfoGivenName(name, "Loc2").get(0);
		
		String info = "Name: " + name + "\n" +
				"Department: " + department + "\n" +
				"Title: " + title + "\n" +
				"Email: " + email + "\n" +
				"Phone: " + phone + "\n" +
				"Location: " + loc1;
		if (!loc2.equals("") && loc2 != null) {
			info += " - " + loc2;
		}
		return info;
	}

}
