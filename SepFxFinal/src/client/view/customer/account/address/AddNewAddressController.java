package client.view.customer.account.address;

import java.net.URL;
import java.util.ResourceBundle;

import client.controller.Controller;
import client.view.MainView;
import client.view.customer.account.CustomerAccountConroller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import shared.model.Address;
import shared.model.Customer;

public class AddNewAddressController implements Initializable {

	private Controller controller;
	private MainView view;
	private Customer customer;
	private CustomerAccountConroller c;
	@FXML
	private TextField streetField;

	@FXML
	private TextField houseNoField;

	@FXML
	private TextField cityField;

	@FXML
	private TextField countryField;

	@FXML
	private Button AddAddress;

	@FXML
	private Label msgLabel;

	@FXML
	private TextField postcodeField;

	@FXML
	void addNewAddress(MouseEvent event) {
		if (streetField.getText().equals("") ||
			houseNoField.getText().equals("") || 
			cityField.getText().equals("")|| 
			postcodeField.getText().equals("") || 
			countryField.getText().equals("")) {
			
			msgLabel.setText("Error add new address. Please try again, if you really want to.");
			msgLabel.setVisible(true);
		} else {
			String street = streetField.getText();
			String houseNo = houseNoField.getText();
			String city = cityField.getText();
			String postcode = postcodeField.getText();
			String country = streetField.getText();

			Address newAddress = new Address();
			newAddress.setStreet(street);
			newAddress.setHouseNumber(houseNo);
			newAddress.setCity(city);
			newAddress.setPostcode(postcode);
			newAddress.setCountry(country);
			int id = controller.addNewAddress(newAddress, customer.getEmail());
			newAddress.setAddressId(id);
			
			controller.getCustomer().addAddress(newAddress);
				 
				view.showConfirmation("Adderess has been added successfully.");
				msgLabel.setVisible(false);
				view.showAccount();
				clearFields();
				
				
				
			 }
			 
				
				msgLabel.setText("Error. Please try again, if you really want to.");
				msgLabel.setVisible(true);
			}
		

	

	@FXML
	void back(MouseEvent event) {
		view.showAccount();
	}

	public void setController(Controller c) {
		controller = c;
	}

	public void setMainView(MainView view) {
		this.view = view;
	}

	private void clearFields() {
		streetField.setText("");
		houseNoField.setText("");
		cityField.setText("");
		postcodeField.setText("");
		countryField.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setCustomer(Customer customer) {
		this.customer = customer;

	}
	
	public void setCustomerAccountConroller(CustomerAccountConroller c)
	{
		this.c = c;
	}
	
	

}
