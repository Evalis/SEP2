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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.model.Address;
import shared.model.Customer;
import shared.model.Order;

public class OrderHistoryController implements Initializable {

	private Controller controller;
	private MainView view;
	private Customer customer;
	private CustomerAccountConroller c;
	private ArrayList<Order> orders;
	
	@FXML
    private Label msgLabel;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, Integer> idOrderCol;

    @FXML
    private TableColumn<Order, Date> DateCol;

    @FXML
    private TableColumn<Order, String> delNameCol;


    @FXML
    private TableColumn<Order, Double> TotalPriceCol;
  
    @FXML
    private TableColumn<Order, Double> delFeeCol;
    
    @FXML
    private TableColumn<Order, Boolean> deiveredCol;

    @FXML
    private Button OrderDetail;
   
  
    public void populateOrderTable()
	{
		if(orders == null)
		{
			getOrderList();
			
		}
	ArrayList<Order> list = customer.getHistoryOfOrders(); 
		
		ObservableList<Order> olist = FXCollections.observableArrayList(list);
		
		idOrderCol.setCellValueFactory(
				new PropertyValueFactory<Order, Integer>("orderId"));
		DateCol.setCellValueFactory(
				new PropertyValueFactory<Order, Date>("date"));
		delNameCol.setCellValueFactory(
				new PropertyValueFactory<Order, String>("deliveryName"));
		
		TotalPriceCol.setCellValueFactory(
				new PropertyValueFactory<Order, Double>("totalPrice"));
		delFeeCol.setCellValueFactory(
				new PropertyValueFactory<Order, Double>("deliveryFee"));		
		deiveredCol.setCellValueFactory(
				new PropertyValueFactory<Order, Boolean>("isDelivered"));
		
		
		orderTable.setItems(olist);
		

	}
    @FXML
    void showOrderDetail(MouseEvent event) {
    	Order o = orderTable.getSelectionModel().getSelectedItem();
    	if(o == null)
    	{
    		view.showError("Please select an order from order table");
        	return;
    	}
    	else {
    		System.out.println(o.getDeliveryName());
    	view.showOrderInDetail(o);
    	
    	}
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
	private void getOrderList()
	{
		orders = customer.getHistoryOfOrders();
	}
	
	

}
