package client.view;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.model.ItemList;
import shared.model.Item;

public class MainViewController implements Initializable {
	private MainView view;
	private Controller controller;
	private ItemList items;

	@FXML
	private Button logIn;

	@FXML
	private Button signUp;

	@FXML
	private TextField textField;

	@FXML
	private Button search;

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
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void clickLogIn(MouseEvent event) {
		view.showLogin();
	}

	@FXML
	void clickSearch(MouseEvent event) {
		
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
	void clickSignUp(MouseEvent event) {
		view.showSignUp();
	}

	public void setMainView(MainView view) {
		this.view = view;
	}

	public void setController(Controller c) {
		controller = c;
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
	
}