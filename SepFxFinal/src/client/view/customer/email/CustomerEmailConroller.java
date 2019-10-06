package client.view.customer.email;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.logging.impl.ServletContextCleaner;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.model.Customer;
import shared.model.Item;
import shared.model.ItemList;

public class CustomerEmailConroller implements Initializable{
	private Controller controller;
	private MainView view;
	private Customer customer;

	@FXML
    private TextArea msg;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    @FXML
    private Button send;
    
    

    @FXML
    void back(MouseEvent event) {
    	view.showCustomerMain(customer);
    }

    @FXML
    void sendEmail(MouseEvent event) {
    	if(customer == null)
    	{
    		
    		customer = controller.getCustomer();
    	}
    	String email = customer.getEmail();
    	String message = msg.getText();
    	if(message.isEmpty())
    	{
    		view.showConfirmation("The message is empty. Please write your message");
    	}
    	try {
			EmailService.SendEmailToManager(email, message);
			view.showConfirmation("Thank you, the message has been sent.");
		} catch (AddressException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
		
			e.printStackTrace();
		}
    	

    }
    
    
    
    public void setCustomer(Customer cus)
    {
    	this.customer = cus;
    }
    
    public void  setMainView(MainView view)
    {
    	this.view = view;
    }
    
    public void setController(Controller con)
    {
    	this.controller = con;
    }
	
  
	    
}
