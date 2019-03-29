package com.teamup.project.logic;

import java.util.Properties; 

import org.springframework.stereotype.Controller;

import com.teamup.project.entities.UserEntity;

@Controller
public class SendMailController {

	// ----------------------------------------- Sends a mail with user details ----------------------------------------------

	public void sendMail (UserEntity user) {

//		final String username = "teamappservice@gmail.com"; // A scape-goat gmail account 
//		final String password = "srduicukzh1";
//
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
//
//		Session session = Session.getInstance(props,
//				new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		});
//
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("teamappservice@gmail.com"));
//			message.setRecipients(Message.RecipientType.TO,
//					InternetAddress.parse(user.getEmail()));// "The user's email"
//			message.setSubject("Welcome to TeamUp!");
//			message.setText("Hello, " + user.getNickname() + "! Your account has been created succesfully, your password is " + user.getPassword());
//
//			Transport.send(message);
//
//			System.out.println("An email was sent to " + user.getEmail());
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
	}
}
