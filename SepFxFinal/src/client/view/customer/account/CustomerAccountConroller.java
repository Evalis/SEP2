package client.view.customer.account;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.controller.Controller;
import client.view.MainView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.model.Address;
import shared.model.Customer;


public class CustomerAccountConroller implements Initializable {
	private Controller controller;
	private Customer customer;
	private MainView view;
	private ArrayList<Address> address;
	

	@FXML
	private Button edit;


	@FXML
	private Button addAddress;

	@FXML
	private TableView<Address> addressTable;

	@FXML
	private TableColumn<Address, Integer> idAddress;

	@FXML
	private TableColumn<Address, String> streetCol;

	@FXML
	private TableColumn<Address, String> houseCol;

	@FXML
	private TableColumn<Address, String> postcodeCol;

	@FXML
	private TableColumn<Address, String> cityCol;

	@FXML
	private TableColumn<Address, String> countryCol;
	 @FXML
	    private Button orders;

	    @FXML
	    private TextField firstNameField;

	    @FXML
	    private TextField lastNameField;

	    @FXML
	    private PasswordField passwordField;

	    @FXML
	    private TextField phoneField;
		private Node msgLabel;



	    @FXML
	    void seeOrders(MouseEvent event) {
	    	view.showCustomerOrders();
	    }
	@FXML
	void addAddress(MouseEvent event) {
		view.showAddNewAddress();
	}

	@FXML
	void editInfo(MouseEvent event) {
		
		String fname = firstNameField.getText();
		String lname = lastNameField.getText();
		String password = passwordField.getText();
		String phoneNo = phoneField.getText();
		
		customer.setFname(fname);
		customer.setlName(lname);
		customer.setPassword(password);
		customer.setPhoneNo(phoneNo);
		
		
		if(controller.updateCustomerInformation(customer)) {
			view.showConfirmation("Information has been updated successfully.");
		}
	}
	public void populateCustomerFields() {
		
	Customer cus = controller.getCustomer(); 
	
	firstNameField.setText(cus.getFname());
	lastNameField.setText(cus.getlName());
	passwordField.setText(cus.getPassword());
	phoneField.setText(cus.getPhoneNo());
	}

	

	
	public void populateAddressTable()
	{
		if(address == null)
		{
			getAddressList();
			
		}
	ArrayList<Address> list = customer.getAddressList(); 
		
		ObservableList<Address> olist = FXCollections.observableArrayList(list);
		
		idAddress.setCellValueFactory(
				new PropertyValueFactory<Address, Integer>("addressId"));
		streetCol.setCellValueFactory(
				new PropertyValueFactory<Address, String>("street"));
		houseCol.setCellValueFactory(
				new PropertyValueFactory<Address, String>("houseNumber"));
		postcodeCol.setCellValueFactory(
				new PropertyValueFactory<Address, String>("city"));
		cityCol.setCellValueFactory(
				new PropertyValueFactory<Address, String>("postcode"));
		countryCol.setCellValueFactory(
				new PropertyValueFactory<Address, String>("country"));
		
		addressTable.setItems(olist);
		

	}

	@FXML
	void back(MouseEvent event) {
		view.showCustomerMain(customer);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setController(Controller c) {
		this.controller = c;
	}

	public void setCustomer(Customer cus) {
		customer = cus;
	}

	public void setMainView(MainView mainView) {
		view = mainView;

	}
	private void getAddressList()
	{
		address= customer.getAddressList();
	}


}
