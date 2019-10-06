package client.view.manager.item;

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
import shared.model.Address;
import shared.model.Customer;
import shared.model.Item;
import shared.model.Manager;

public class EditItemController implements Initializable {

	private Controller controller;
	private MainView view;
	private Manager man;
	private Item item;

	@FXML
	private TextField nameField;

	@FXML
	private TextField qunatityField;

	@FXML
	private TextField priceField;

	@FXML
	private Button AddAddress;

	@FXML
	private Label msgLabel;

	@FXML
	private TextField inStockField;

	@FXML
	void editItem(MouseEvent event) {
		
		String name = nameField.getText();
		int quantity = Integer.parseInt(qunatityField.getText());
		double price = Double.parseDouble(priceField.getText());
		boolean inStock = Boolean.parseBoolean(inStockField.getText());
		
		item.setQuantity(quantity);
		item.setPrice(price);
		item.setInStock(inStock);
		if (controller.updateItem(item)) {
			view.showConfirmation("Item has been added successfully.");
			msgLabel.setVisible(false);
			view.showManagerMain(man);
			clearFields();

		}
		else
		{

		msgLabel.setText("Error. Please try again, if you really want to.");
		msgLabel.setVisible(true);
		}
	}

	@FXML
	void back(MouseEvent event) {
		view.showManagerMain(man);
	}

	public void setManager(Manager man) {
		this.man = man;
	}

	public void setController(Controller c) {
		controller = c;
	}

	public void setMainView(MainView view) {
		this.view = view;
	}

	private void clearFields() {
		nameField.setText("");
		qunatityField.setText("");
		priceField.setText("");
		inStockField.setText("");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setItem(Item item) {
	this.item = item;
		
	}

	public void populateItemFields() {
		
		nameField.setText(item.getItemName());
		qunatityField.setText(""+item.getQuantity());
		priceField.setText(""+item.getPrice());
		inStockField.setText(""+item.getInStock());
	}

}
