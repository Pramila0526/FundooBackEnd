package com.bridgelabz.fundooappbackend.collaborate.utility;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class CollaborateUtility 
{

	public static SimpleMailMessage sendMail(String email1, String email2 ,String note) 
	{
		SimpleMailMessage msg = new SimpleMailMessage();
	
		msg.setFrom(email1);
		msg.setTo(email2); // send mail
		msg.setSubject("Note Sharing"); // send message for user email account
		msg.setText("this is a  note " +note); // send token for user email account
		return msg;
	}
}