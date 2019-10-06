package client.view.customer.bag;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.controller.Controller;
import client.view.MainView;
import client.view.login.LoginController;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.model.Address;
import shared.model.Customer;
import shared.model.Item;
import shared.model.ItemList;
import shared.model.Order;
import shared.model.OrderEntry;


public class CustomerBagConroller implements Initializable {
	private Controller controller;
	private Customer customer;
	private ArrayList<Address> address;
	private MainView view;
	private ArrayList<OrderEntry> oeList;
	private Order orderToMake;
	private Address add;

	@FXML
	private Button remove;

	@FXML
	private Button save;

	@FXML
	private TableView<OrderEntry> orderEntryTable;

	@FXML
	private TableColumn<OrderEntry, Integer> idCol;

	@FXML
	private TableColumn<OrderEntry, String> nameCol;

	@FXML
	private TableColumn<OrderEntry, Integer> qunatityCol;

	@FXML
	private TableColumn<OrderEntry, Double> priceCol;

	@FXML
	private TextField deliveyName;

	@FXML
	private TextField totalPrice;

	@FXML
	private TextField deliveryFee;

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

	public void populateAddressTable() {
		if (address == null) {
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

	public void populateOrderEntryTable() {
		if (orderToMake == null) {
			orderToMake = controller.getOrderToMake();

		}

		ArrayList<OrderEntry> list = orderToMake.getOrderEntries();

		ObservableList<OrderEntry> olist = FXCollections.observableArrayList(list);

		idCol.setCellValueFactory(
				new PropertyValueFactory<OrderEntry, Integer>("itemId"));

		nameCol.setCellValueFactory(
				new PropertyValueFactory<OrderEntry, String>("itemName"));

		qunatityCol.setCellValueFactory(
				new PropertyValueFactory<OrderEntry, Integer>("quantity"));

		priceCol.setCellValueFactory(
				new PropertyValueFactory<OrderEntry, Double>("price"));

		orderEntryTable.setItems(olist);
	}
	
	
	
	 public void calculateTotalPrice()
	{
		 if (orderToMake == null) {
				orderToMake = controller.getOrderToMake();

			}
		double tPrice = 0;
		ArrayList<OrderEntry> oeList = orderToMake.getOrderEntries();
		 for (OrderEntry oe : oeList)
		 {
			 tPrice += (oe.getQuantity()*oe.getPrice());
		 }
		 
		 totalPrice.setText(""+ (tPrice+10.00));
		 deliveryFee.setText("10.00");
	}
	 
	 
	
	

	@FXML
	void back(MouseEvent event) {
		view.showCustomerMain(customer);
	}

	@FXML
	void removeFromBag(MouseEvent event) {
		OrderEntry oe = orderEntryTable.getSelectionModel().getSelectedItem();
		orderToMake.getOrderEntries().remove(oe);
		view.showConfirmation("Item has been removed");
		populateOrderEntryTable();
		
		
	}

	@FXML
    void saveOrder(MouseEvent event) 
	{
		if(Bindings.isEmpty(orderEntryTable.getItems()).get())
		{
			view.showError("Your bag is empty. Please add items to order.");
			return;
		}
		
        Address chosenAddress = addressTable.getSelectionModel().getSelectedItem();
        if(chosenAddress == null)
        {
        	view.showError("Please select an address from address table");
        	return;
        }
        
        if(deliveyName.getText().equals(""))
        {
        	view.showError("Please enter delivery name");
        	return;
        }
        
        orderToMake.setDeliveryAddress(chosenAddress);        
        orderToMake.setDeliveryFee(Double.parseDouble(deliveryFee.getText()));
        orderToMake.setDeliveryName(deliveyName.getText());        
        orderToMake.setTotalPrice(Double.parseDouble(totalPrice.getText()));        
        orderToMake.setIsDelivered(false);
             
		 int orderId = controller.addNewOrder(orderToMake, customer.getEmail());
		 if(orderId == -1)
		 {
			 view.showError("Could not add Order.");
		 }
		 else if(orderId == -5)
		 {
			 view.showError("Some items have limited stock. Please check items quantity in your bag");			 
		 }
		 else
		 {
			 orderToMake.setOrderId(orderId);
			 controller.getCustomer().addNewOrder(orderToMake);
			 controller.newOrderToMake();
			 orderToMake = controller.getOrderToMake();
			 view.showConfirmation("Order created with id = " + orderId);
			 populateOrderEntryTable();
			 calculateTotalPrice();		
			 clearFields();
			 view.showCustomerMain(customer);
		 }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setMainView(MainView view) {
		this.view = view;
	}

	public void setController(Controller con) {
		this.controller = con;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;

	}

	private void getAddressList() {
		address = customer.getAddressList();
	}
	public void setAddress(Address add)
	{
		this.add = add;
	}
	private void clearFields() {
		deliveryFee.setText("");
		deliveyName.setText("");
		totalPrice.setText("");
		
	}
}
