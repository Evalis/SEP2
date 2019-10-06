package client.view.manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.controller.Controller;
import client.view.MainView;
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
import shared.model.Item;
import shared.model.ItemList;
import shared.model.Manager;

public class ManagerMainViewConroller implements Initializable {
	private Controller controller;
	private LoginController loginView;
	private Manager manager;
	private ItemList items;
	private MainView view;


 
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
    private Button search;

   

    @FXML
    private Button orders;
    @FXML
    private TextField textField;
    @FXML
    private ImageView logout;
    @FXML
    private Button addItem;
    @FXML
    private Button editItem;
    @FXML
    private Button deleteItem;
  
    @FXML
    void back(MouseEvent event) {
    	view.showManagerMain(manager);
    }
    @FXML
    void addNewItem(MouseEvent event) {
    	view.showAddNewItem();
    }
   

 
    @FXML
    void deleteItem(MouseEvent event) {
    	Item i =  itemsTable.getSelectionModel().getSelectedItem();
    	if(i == null)
    	{
    		view.showError("Please select an item from item table");
        	return;
    	}
    	if(controller.deleteItem(i.getItemId()))
    	{
    		items.remove(i);
    		populateItemTable();
    		view.showConfirmation("Item has been removed!");
    		
    	}
    	
    	else
    	{
    		view.showConfirmation("Editing failed, please try again!");
    	}
    }
    @FXML
    void editItem(MouseEvent event) {
    	Item i =  itemsTable.getSelectionModel().getSelectedItem();
    	if(i == null)
    	{
    		view.showError("Please select an item from item table");
        	return;
    	}
    	view.showEditItem(i);
    	
    }
    @FXML
    void logout(MouseEvent event) {
    	controller.logoutManager();
    	view.showMainPage();
    }

    @FXML
    void goToOrderView(MouseEvent event) {
    	view.showOrderView();
    }

    @FXML
    void search(MouseEvent event) {
 
    	String word = textField.getText();
    	ArrayList<Item> list = items.searchItem(word);
		
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

	
    public void populateItemTable() {
		if (items == null) {
			getItemList();
			
		}
		ArrayList<Item> list = items.getAllItems();
		
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

	private void getItemList() {
		items = controller.getAllItems();
	}
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	

	public void setController(Controller c) {
		this.controller = c;
	}

	public void setLoginView(LoginController view) {
		this.loginView = view;
	}

	public void setCustomer(Manager man) {
		manager = man;
	}

	public void setMainView(MainView mainView) {
		view = mainView;
		
	}
}