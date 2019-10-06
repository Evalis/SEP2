package client.view.login;

import java.net.URL;
import java.util.ResourceBundle;

import client.controller.Controller;
import client.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import shared.model.Customer;
import shared.model.Manager;

public class LoginController implements Initializable {
	private Controller controller;
	private MainView view;
	
	@FXML
	private TextField emailField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label msgLabel;

	@FXML
	private RadioButton choiceManager;

	@FXML
	private ToggleGroup loginChoice;

	@FXML
	private RadioButton choiceCustomer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void clickLogin(MouseEvent event) {

		if (emailField.getText().equals("") || passwordField.getText().equals(""))

		{
			msgLabel.setText("Error logging in. Please try again, if you really want to.");
			msgLabel.setVisible(true);
		} else {
			String email = emailField.getText();
			String password = passwordField.getText();

			if (choiceCustomer.isSelected()) {
				Customer cus = controller.loginCustomer(email, password);

				if (cus == null) {
					msgLabel.setText("Error logging in. Please try again, if you really want to.");
					msgLabel.setVisible(true);
				} else {

					view.showCustomerMain(cus);
					clearFields();

				}

			} else {

				if (choiceManager.isSelected()) {
					Manager man = controller.loginManager(email, password);

					if (man == null) {
						msgLabel.setText("Error logging in. Please try again, if you really want to.");
						msgLabel.setVisible(true);
					} else {

						view.showManagerMain(man);
						clearFields();
					}

				}

			}

		}

	}


	@FXML
	void back(MouseEvent event) {
		view.showMainPage();
	}

	public void setController(Controller c) {
		controller = c;
	}

	public void setMainView(MainView view) {
		this.view = view;
	}

	private void clearFields() {
		emailField.setText("");
		passwordField.setText("");
	}

	
}
