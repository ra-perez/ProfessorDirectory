package application;

import database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class professorFile {
	
	@FXML
	TextField departmentName;
	
	@FXML
	Button updateInfo;
	
	
	
	
	Controller control = new Controller();
	Database data;
	
	@FXML
	public void initialize() {
		
	}
	
}
