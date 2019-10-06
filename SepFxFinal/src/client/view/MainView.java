package client.view;
	


import java.io.IOException;
import java.util.ArrayList;

import client.controller.Controller;
import client.view.customer.CustomerMainViewConroller;
import client.view.customer.account.CustomerAccountConroller;
import client.view.customer.account.address.AddNewAddressController;
import client.view.customer.account.order.OrderDetailsController;
import client.view.customer.account.order.OrderHistoryController;
import client.view.customer.bag.CustomerBagConroller;
import client.view.customer.email.CustomerEmailConroller;
import client.view.customer.register.RegisterController;
import client.view.customer.wishlist.CustomerWishlistConroller;
import client.view.login.LoginController;
import client.view.manager.ManagerMainViewConroller;
import client.view.manager.item.AddNewItemController;
import client.view.manager.item.EditItemController;
import client.view.manager.orders.ManagerOrderConroller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import shared.model.Customer;
import shared.model.Item;
import shared.model.Manager;
import shared.model.Order;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;



public class MainView extends Application {
	
	private Controller controller;
	private AnchorPane registerView, loginView,orderView, emailView, editView, bagView, orderCustomerView;
	private AnchorPane customerView, managerView, wishlistView, accountView, addressView, itemView;
	private Stage primaryStage;
	private AnchorPane orderDetailView;
	
	
	@Override
	public void start(Stage primaryStage) {
		controller = new Controller();
		this.primaryStage = primaryStage;
		primaryStage.setTitle("AVE Online Shopping");
		primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(1);
            }
        });
		showMainPage();
	}
	public void showMainPage()
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AAE Online Shopping.fxml"));
			Parent root = loader.load();
			MainViewController mainViewController = loader.getController();
			mainViewController.setMainView(this);
			mainViewController.setController(controller);
			mainViewController.populateItemTable();
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showOrderView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("manager/orders/ManagerOrder.fxml"));
			orderView =  (AnchorPane) loader.load();
			ManagerOrderConroller c = loader.getController();
			c.setController(controller);
			c.setMainView(this);
			c.setManager(controller.getManager());
			c.populateOrderTable();
			primaryStage.getScene().setRoot(orderView);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	public void showCustomerMain(Customer cus) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer/CustomerMainView.fxml"));
			customerView = (AnchorPane) loader.load();
			CustomerMainViewConroller c = loader.getController();
			c.setController(controller);
			c.setCustomer(cus);
			c.setMainView(this);
			c.populateItemTable();
			primaryStage.getScene().setRoot(customerView);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void showManagerMain(Manager man) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("manager/ManagerMainView.fxml"));
			managerView = (AnchorPane) loader.load();
			ManagerMainViewConroller c = loader.getController();
			c.setController(controller);
			c.setCustomer(man);
			c.populateItemTable();
			c.setMainView(this);
			primaryStage.getScene().setRoot(managerView);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void showAccount() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer/account/CustomerAccount.fxml"));
			accountView =  (AnchorPane) loader.load();
			CustomerAccountConroller c = loader.getController();
			c.setController(controller);
			c.setCustomer(controller.getCustomer());
			c.setMainView(this);
			c.populateAddressTable();
			c.populateCustomerFields();
			primaryStage.getScene().setRoot(accountView);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	public void showWishlist() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer/wishlist/CustomerWishlist.fxml"));
			wishlistView =  (AnchorPane) loader.load();
			CustomerWishlistConroller c = loader.getController();
			c.setController(controller);
			c.setCustomer(controller.getCustomer());
			c.setMainView(this);
			c.populateWishlistTable();
			primaryStage.getScene().setRoot(wishlistView);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void showAddNewAddress() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer/account/address/AddNewAddress.fxml"));
			addressView =  (AnchorPane) loader.load();
			AddNewAddressController c = loader.getController();
			c.setController(controller);
			c.setCustomer(controller.getCustomer());
			c.setMainView(this);
			primaryStage.getScene().setRoot(addressView);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	public void showAddNewItem() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("manager/item/AddNewItem.fxml"));
			itemView =  (AnchorPane) loader.load();
			AddNewItemController c = loader.getController();
			c.setController(controller);
			c.setMainView(this);
			primaryStage.getScene().setRoot(itemView);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	public void showEditItem(Item item) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("manager/item/EditItem.fxml"));
			editView =  (AnchorPane) loader.load();
			EditItemController c = loader.getController();
			c.setController(controller);
			c.setItem(item);
			c.populateItemFields();
			c.setMainView(this);
			primaryStage.getScene().setRoot(editView);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	public void showBag() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer/bag/CustomerBag.fxml"));
			bagView = (AnchorPane) loader.load();
			CustomerBagConroller c = loader.getController();
			c.setController(controller);
			c.setCustomer(controller.getCustomer());
			c.setMainView(this);
			c.populateAddressTable();
			c.populateOrderEntryTable();
			c.calculateTotalPrice();
			primaryStage.getScene().setRoot(bagView);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	public void showEmail() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer/email/CustomerEmail.fxml"));
			emailView = (AnchorPane) loader.load();
			CustomerEmailConroller c  = loader.getController();
			c.setController(controller);
			c.setMainView(this);
			primaryStage.getScene().setRoot(emailView);
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	public void showSignUp()
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer/register/Register.fxml"));
			registerView = (AnchorPane) loader.load();
			RegisterController c  = loader.getController();
			c.setController(controller);
			c.setMainView(this);
			primaryStage.getScene().setRoot(registerView);
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public void showCustomerOrders() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer/account/order/OrderHistory.fxml"));
			orderCustomerView =  (AnchorPane) loader.load();
			OrderHistoryController c = loader.getController();
			c.setController(controller);
			c.setCustomer(controller.getCustomer());
			c.setMainView(this);
			c.populateOrderTable();
			primaryStage.getScene().setRoot(orderCustomerView);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	public void showOrderInDetail(Order o) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("customer/account/order/OrderDetails.fxml"));
			orderDetailView =  (AnchorPane) loader.load();
			OrderDetailsController c = loader.getController();
			c.setController(controller);
			c.setCustomer(controller.getCustomer());
			c.setMainView(this);
			c.setOrder(o);
			c.populateOrderFields();
			c.populateOrderEntryTable();
			primaryStage.getScene().setRoot(orderDetailView);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}	
		
	
	
	public void showLogin()
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("login/Login.fxml"));
			loginView = (AnchorPane) loader.load();
			LoginController c  = loader.getController();
			c.setController(controller);
			c.setMainView(this);
			primaryStage.getScene().setRoot(loginView);
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void showConfirmation(String message)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
	public void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
		
	}
	}
	
	
	
	
	

	

