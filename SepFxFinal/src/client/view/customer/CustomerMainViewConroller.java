package client.view.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.controller.Controller;
import client.view.MainView;
import client.view.customer.account.CustomerAccountConroller;
import client.view.login.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import shared.model.Customer;
import shared.model.Item;
import shared.model.ItemList;
import shared.model.Order;
import shared.model.OrderEntry;

public class CustomerMainViewConroller implements Initializable {
	private Controller controller;
	private Customer customer;
	private ItemList items;
	private MainView view;
	private Order orderToMake;

	@FXML
	private TableView<Item> itemsTable;

	@FXML
	private TableColumn<Item, Integer> idCol;

	@FXML
	private TableColumn<Item, String> nameCol;

	@FXML
	private TableColumn<Item, Integer> qunatityCol;

	@FXML
	private TableColumn<Item, Double> priceCol;



	@FXML
	private TextField textField;

	@FXML
	private Button search;

	@FXML
	private ImageView account;

	@FXML
	private ImageView wihlist;

	@FXML
	private ImageView logout;
	
    @FXML
    private TextField orderEntryQuantityField;
	
	@FXML
	private ImageView email;

	@FXML
	private ImageView bag;

	@FXML
	void addToBag(MouseEvent event) {
		if(orderToMake == null)
		{
			orderToMake = controller.getOrderToMake();
		}
		
		
      Item i = itemsTable.getSelectionModel().getSelectedItem();
      int orderEntryQuantity = 0;
      try {
    	  orderEntryQuantity = Integer.parseInt(orderEntryQuantityField.getText());
    	  clearField();
      }
      catch(NumberFormatException e)
      {
    	  view.showError("Please enter qunatity");
    	  return;
      }
      
      boolean foundItemInOrderEntry = false;
      
      ArrayList<OrderEntry> oeList = orderToMake.getOrderEntries();
      
      for (OrderEntry oe : oeList) {
		if(oe.getItemId() == i.getItemId())
		{
			foundItemInOrderEntry = true;
			int q = oe.getQuantity() + orderEntryQuantity;
			oe.setQuantity(q);
		}
      }
      
      if(!foundItemInOrderEntry)
      {
    	  OrderEntry oe = new OrderEntry();
    	  oe.setItem(i);
    	  oe.setQuantity(orderEntryQuantity);
    	  
    	  orderToMake.addOrderEntry(oe);    	  
      }
      
      view.showConfirmation("Item has been added to bag!");
	}

	private void clearField() {
		orderEntryQuantityField.setText("");
		
	}

	@FXML
	void addToWishList(MouseEvent event) {
		Item i = itemsTable.getSelectionModel().getSelectedItem();
		if(controller.addItemToWishList(
				i.getItemId(), 
				customer.getEmail())== true)
		{
			customer.addItemToWishList(i);
			view.showConfirmation("Item added successfully");
		}
		else
		{
			view.showConfirmation("Item is already added");
		}
		

	}

	@FXML
	void logout(MouseEvent event) {
		controller.logoutCustomer();
		view.showMainPage();
	}

	@FXML
	void search(MouseEvent event) {

		String word = textField.getText();
		ArrayList<Item> list = items.searchItem(word);

		ObservableList<Item> olist = FXCollections.observableArrayList(list);

		idCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("itemId"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));
		qunatityCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
		

		itemsTable.setItems(olist);
	}

	@FXML
	void showAccount(MouseEvent event) {
		view.showAccount();
	}

	@FXML
	void showBag(MouseEvent event) {
		view.showBag();
	}

	@FXML
	void showEmail(MouseEvent event) {
		view.showEmail();
	}

	@FXML
	void showWishList(MouseEvent event) {
		view.showWishlist();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void populateItemTable() {
		if (items == null) {
			getItemList();
		}
		ArrayList<Item> list = items.getAllItems();

		ObservableList<Item> olist = FXCollections.observableArrayList(list);

		idCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("itemId"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));
		qunatityCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Item, Double>("price"));
		

		itemsTable.setItems(olist);

	}

	private void getItemList() {
		items = controller.getAllItems();
	}

	public void setController(Controller c) {
		this.controller = c;
	}


	public void setMainView(MainView view) {
		this.view = view;
	}

	public void setCustomer(Customer cus) {
		customer = cus;
	}

}
