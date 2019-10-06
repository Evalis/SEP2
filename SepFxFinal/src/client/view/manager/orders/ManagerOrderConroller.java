package client.view.manager.orders;

import java.net.URL;
import java.sql.Date;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import shared.model.Item;
import shared.model.Manager;
import shared.model.Order;

public class ManagerOrderConroller implements Initializable {
	private Controller controller;
	private MainView view;
	private Manager manager;
	private ArrayList<Order> orders;

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
	private TextField textField;

	@FXML
	private Button search;

	@FXML
	private ImageView louout;

	@FXML
	private Button processOrder;

	public void populateOrderTable() {
		if (orders == null) {
			getOrderList();

		}
		ObservableList<Order> olist = FXCollections.observableList(orders);
		idOrderCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderId"));
		DateCol.setCellValueFactory(new PropertyValueFactory<Order, Date>("date"));
		delNameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("deliveryName"));

		TotalPriceCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("totalPrice"));
		delFeeCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("deliveryFee"));
		deiveredCol.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("isDelivered"));

		orderTable.setItems(olist);

	}

	@FXML
	void changeOderStatus(MouseEvent event) {

		Order o = orderTable.getSelectionModel().getSelectedItem();
		{
			if (o == null) {
				view.showError("Please select an oder from order table");
				return;
			}
			if (controller.updateOrder(o)) {
				o.setIsDelivered(true);
				orderTable.refresh();
				view.showConfirmation("Order has been processed!");
			}
		}

	}

	@FXML
	void back(MouseEvent event) {
		view.showManagerMain(manager);
	}

	@FXML
	void logout(MouseEvent event) {
		controller.logoutManager();
		view.showMainPage();
	}

	@FXML
	void search(MouseEvent event) {
		String searchWord = textField.getText();
		ArrayList<Order> list = null;
		if (searchWord.isEmpty()) {
			list = manager.getAllOrder();
		} else {
			int orderID = 0;
			try {
				orderID = Integer.parseInt(textField.getText());
			} catch (NumberFormatException e) {
				view.showError("Please insert only numbers.");
				return;
			}

			list = manager.searchOrder(orderID);
		}
		ObservableList<Order> olist = FXCollections.observableArrayList(list);
		idOrderCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderId"));
		DateCol.setCellValueFactory(new PropertyValueFactory<Order, Date>("date"));
		delNameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("deliveryName"));

		TotalPriceCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("totalPrice"));
		delFeeCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("deliveryFee"));
		deiveredCol.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("isDelivered"));

		orderTable.setItems(olist);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setController(Controller controller) {
		this.controller = controller;

	}

	public void setMainView(MainView mainView) {
		view = mainView;
	}

	public void setManager(Manager man) {
		this.manager = man;
	}

	private void getOrderList() {
		if (manager == null) {
			manager = controller.getManager();
		}
		orders = manager.getAllOrder();
	}

}
