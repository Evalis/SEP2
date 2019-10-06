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

public class AddNewItemController implements Initializable {

	private Controller controller;
	private MainView view;
	private Manager man;
	

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
	void addNewItem(MouseEvent event) {
		if (nameField.getText().equals("") ||
			qunatityField.getText().equals("") || 
			priceField.getText().equals("")|| 
			inStockField.getText().equals(""))
		
		{
			msgLabel.setText("Error add new item. Please try again");
			msgLabel.setVisible(true);
		}
		
		 else 
		
		{
			String name = nameField.getText();
			int quantity = Integer.parseInt(qunatityField.getText());
			double price = Double.parseDouble(priceField.getText());
			boolean inStock = Boolean.parseBoolean(inStockField.getText());
			

			Item newItem = new Item();
			newItem.setItemName(name);
			newItem.setQuantity(quantity);
			newItem.setPrice(price);
			newItem.setInStock(inStock);
			
			int id = controller.addNewItem(newItem);
			newItem.setItemId(id);
			controller.getAllItems();
				 
				view.showConfirmation("Item has been added successfully.");
				msgLabel.setVisible(false);
				view.showManagerMain(man);
				clearFields();
				
				
				
			 }
			 
				
				msgLabel.setText("Error. Please try again, if you really want to.");
				msgLabel.setVisible(true);
			}
		

	

	@FXML
	void back(MouseEvent event) {
		view.showManagerMain(man);
	}
	
	public void setManager(Manager man)
	{
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


	
	
	

}
