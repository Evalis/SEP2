package client.view.customer.wishlist;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.controller.Controller;
import client.view.MainView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import shared.model.Customer;
import shared.model.Item;
import shared.model.ItemList;

public class CustomerWishlistConroller implements Initializable {

	private Controller controller;
	private MainView view;
	private Customer customer;
	private ArrayList<Item> items;
	private ItemList itemList;
	
	@FXML
	private Button back;
	 @FXML
	 private Button remove;



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


		

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void setController(Controller controller) {
		this.controller = controller;

	}

	public void setMainView(MainView mainView) {

		view = mainView;
	}

	@FXML
	void back(MouseEvent event) {
		view.showCustomerMain(customer);
	}

    @FXML
    void removeFromWishList(MouseEvent event) {
    	Item i = itemsTable.getSelectionModel().getSelectedItem();
		if(controller.removeItemFromWishList(i.getItemId(),customer.getEmail())== true)
		{
			customer.removeItemFromWishList(i);
			view.showConfirmation("Item removed from wishlist!");
		}
		else
		{
			view.showConfirmation("Removing failed, please try again!");
		}
    }

 

	
	public void populateWishlistTable() {
		ArrayList<Item> list = customer.getWishList() ; 
		
		ObservableList<Item> olist = FXCollections.observableArrayList(list);
		
		
		
		idCol.setCellValueFactory(
				new PropertyValueFactory<Item, Integer>("itemId"));
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Item, String>("itemName"));
		qunatityCol.setCellValueFactory(
				new PropertyValueFactory<Item, Integer>("quantity"));
		priceCol.setCellValueFactory(
				new PropertyValueFactory<Item, Double>("price"));
	
		
		itemsTable.setItems(olist);
		

		
	}
	
	public void setCustomer(Customer cus)
	{
		this.customer = cus;
	}
}
