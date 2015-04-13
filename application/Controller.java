package application;

import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class Controller {
	
	@FXML
	Button editInfo;
	
	@FXML
	ChoiceBox <String> department;
	
	@FXML
	ChoiceBox <String> building;
	
	@FXML
	ListView<String> filteredNames;
	
	@FXML
	TextArea professorInfo;
	
	@FXML
	ChoiceBox<String> name;

	@FXML
	ChoiceBox<String> column;

	@FXML
	TextArea value;
	
	@FXML
	Label error;

	@FXML
	Button updateInfo;
	
	Database professorDB;
	ArrayList<String> departmentList;
	ArrayList<String> buildingList;
	ArrayList<String> filteredNamesList;
	ObservableList<String> observableFilteredNamesList = FXCollections.observableArrayList();
	ObservableList<String> observableDepartmentList;
	ObservableList<String> observableBuildingList;
	private String departmentSelected =  "";
	private String buildingSelected = "";
	String nameSelected, columnSelected;
	ArrayList<String> nameList, columnList;
	
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
					departmentSelected = departmentList.get(new_val.intValue());
					filteredNamesList = professorDB.getFilteredNames(departmentSelected, buildingSelected);
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
					buildingSelected = buildingList.get(new_val.intValue());
					filteredNamesList = professorDB.getFilteredNames(departmentSelected, buildingSelected);
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
		
		nameList = professorDB.getName();
		populateNameList();
		populateColumnList();
		name.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_val) {
				nameSelected = nameList.get(new_val.intValue());	
			}
		});

		column.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_val) {
				columnSelected = columnList.get(new_val.intValue());	
			}
		});

	}
	
	private void populateNameList() {
		ObservableList<String> observableNameList = FXCollections.observableArrayList();
		for (String name: nameList) {
			observableNameList.add(name);
		}
		name.setItems(observableNameList);
	}

	private void populateColumnList() {
		columnList = new ArrayList<String>();
		columnList.add("Title");
		columnList.add("Department");
		columnList.add("Building");
		columnList.add("Phone");
		columnList.add("Email");
		columnList.add("Loc1");
		columnList.add("Loc2");
		columnList.add("AdditionalInfo");

		ObservableList<String> observableColumnList = FXCollections.observableArrayList();
		for (String name: columnList) {
			observableColumnList.add(name);
		}
		column.setItems(observableColumnList);
	}

	@FXML
	public void saveEdit() throws ClassNotFoundException, SQLException {
		String professorName = nameSelected;
		String columnValue = columnSelected;
		String valueToUpdate = value.getText();
		if (!nameSelected.equals("") && !columnValue.equals("") && !valueToUpdate.equals("")) {
			professorDB.updateColumn(professorName, columnValue, valueToUpdate);
			value.setText("");
			error.setText("Update Complete");
		} else {
			error.setText("Must have information in every field");
		}
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
		String additionalInfo = professorDB.getInfoGivenName(name, "AdditionalInfo").get(0);
		
		String info = "Name: " + name + "\n" +
				"Department: " + department + "\n" +
				"Title: " + title + "\n" +
				"Email: " + email + "\n" +
				"Phone: " + phone + "\n" +
				"Location: " + loc1;
		if (!loc2.equals("") && loc2 != null) {
			info += " - " + loc2;
		}
		info += "\n" + additionalInfo;
		return info;
	}

}
