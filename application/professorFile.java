package application;

import database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class professorFile {
	
	@FXML
	TextField departmentName;
	
	Controller control = new Controller();
	Database data;
	
	@FXML
	public void initialize() {
		/////so we are trying to put a string into the TextField
		/////we call method getDep() in the controller class to get the string of department name
		/////use setText to put the name in the TextField
		/////BUT, the initialize just does nothing to it. We don't know how to solve this problem
		departmentName.setText(control.getDep());
	}
	
}
