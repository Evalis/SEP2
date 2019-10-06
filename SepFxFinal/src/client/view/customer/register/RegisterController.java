package client.view.customer.register;

import java.net.URL;
import java.util.ResourceBundle;

import client.controller.Controller;
import client.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import shared.model.Customer;

public class RegisterController implements Initializable{
	private Controller controller;
	private MainView view;
	    @FXML
	    private TextField firstNameField;

	    @FXML
	    private TextField lastNameField;

	    @FXML
	    private TextField emailField;

	    @FXML
	    private TextField phoneField;

	 
	    @FXML
	    private TextField passwordField;

	    @FXML
	    private Button signUp;
	    
	    @FXML
	    private Label msgLabel;

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	
	    	
	    }
	    @FXML
	    void clickSignUp(MouseEvent event) {
	    	
	    	if (firstNameField.getText().equals("") ||
	    	lastNameField.getText().equals("")||
	    	emailField.getText().equals("") ||
	    	passwordField.getText().equals("")||
	    	phoneField.getText().equals(""))
	    	
	    	{
	    		msgLabel.setText("Error signing up. Please try again, if you really want to.");
	    		msgLabel.setVisible(true);		
	    	}
	    	else
	    	{
	    		String firstName = firstNameField.getText();
	    		String lastName = lastNameField.getText();
	    		String email = emailField.getText();
	    		String password = passwordField.getText();
	    		String phoneNo = phoneField.getText();
	    	
	    		
	    		Customer newCustomer =  new Customer();
	    		newCustomer.setFname(firstName);
	    		newCustomer.setlName(lastName);
	    		newCustomer.setEmail(email);
	    		newCustomer.setPassword(password);
	    		newCustomer.setPhoneNo(phoneNo);
	    		if(controller.register(newCustomer))
	    		{
	    			view.showConfirmation("Account has been registered successfully.");
	    			msgLabel.setVisible(false);
	    			clearFields();
	    		}
	    		else  
	    		{
	    			msgLabel.setText("Error signing up. Please try again, if you really want to.");
		    		msgLabel.setVisible(true);	
	    		}
	    	}
	    		
	   
	    }
	    
	    @FXML
	    void back(MouseEvent event) {
	    	view.showMainPage();
	    }
	    
	    public void setController(Controller c)
	    {
	    	controller = c;
	    }
	    
	    public void setMainView(MainView view)
		{
			this.view = view;
		}
	    
	    private void clearFields()
	    {
	    	firstNameField.setText("");
    		lastNameField.setText("");
    		emailField.setText("");
    		passwordField.setText("");
    		phoneField.setText("");
	    }
}
