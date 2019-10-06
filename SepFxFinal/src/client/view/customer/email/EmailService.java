package client.view.customer.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 

 
public class EmailService {
 
	private static Properties prop;
	private static Session session;
	private static MimeMessage message;
	private static String eBody;
 
	public static void SendEmailToManager(String customerEmail, String msg) throws AddressException, MessagingException {
 
		
		prop = System.getProperties();
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		session = Session.getDefaultInstance(prop, null);
		message = new MimeMessage(session);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(customerEmail));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress("onlineshopping.via@gmail.com"));
		message.setSubject("Thank you for contacting. AVE Online shopping");
		eBody = "We have received your message" +
				"\n\""+ msg + "\"" +
				"\nOur service personnel will contact you as soon as possible" +
				"\nBest regards"
				+ "\nAVE Online shopping";
		message.setContent(eBody, "text/html");
			
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", "onlineshopping.via@gmail.com", "Online123");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	}
