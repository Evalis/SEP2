package client.view.customer.account.order;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.controller.Controller;
import client.view.MainView;
import client.view.customer.account.CustomerAccountConroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import shared.model.Customer;
import shared.model.Order;
import shared.model.OrderEntry;

public class OrderDetailsController implements Initializable {

	private Controller controller;
	private MainView view;
	private Customer customer;
	private CustomerAccountConroller c;
	private ArrayList<OrderEntry> oe;
	private Order order;
	

    @FXML
    private TextField orderId;

    @FXML
    private TextField deliveryName;

    @FXML
    private TextField Date;

    @FXML
    private TextField totalPrice;

    @FXML
    private TextField Streer;

    @FXML
    private TextField houseNo;

    @FXML
    private TextField City;

    @FXML
    private TextField country;

    @FXML
    private TextField postcode;

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
	void back(MouseEvent event) {
		view.showAccount();
	}

	public void setController(Controller c) {
		controller = c;
	}

	public void setMainView(MainView view) {
		this.view = view;
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	public void setOrder(Order o)
	{
		this.order = o;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;

	}
	
	public void seOrderEntry (ArrayList<OrderEntry> oe)
	{
		this.oe = oe;
	}
	
	public void setCustomerAccountConroller(CustomerAccountConroller c)
	{
		this.c = c;
	}
	public void populateOrderFields() {
		System.out.println(order.getDeliveryName());
	  

		orderId.setText(""+ order.getOrderId());
		deliveryName.setText(order.getDeliveryName());
		Date.setText(""+order.getDate());
		totalPrice.setText(""+order.getTotalPrice());
		Streer.setText(order.getDeliveryAddress().getStreet());
		houseNo.setText(order.getDeliveryAddress().getHouseNumber());
		City.setText(order.getDeliveryAddress().getCity());
		country.setText(order.getDeliveryAddress().getCountry());
		postcode.setText(order.getDeliveryAddress().getPostcode());
		}
	
	public void populateOrderEntryTable()
	{   
		
		ArrayList<OrderEntry> list = order.getOrderEntries();

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
	



}
