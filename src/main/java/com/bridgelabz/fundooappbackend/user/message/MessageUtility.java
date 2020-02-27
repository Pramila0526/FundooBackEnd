package com.bridgelabz.fundooappbackend.user.message;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**********************************************************************************************************
 * @author 	:Pramila Tawari 
 * Purpose	:Models a simple mail message, including data such as the from, to, cc, subject,
 * 			 and text fields.
 *
 *********************************************************************************************************/

@Component
public class MessageUtility
{
	public static SimpleMailMessage verifyUserMail(String email,String token,String link)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("forgotbridge70@gmail.com");
		message.setTo(email);    				 	// Sending Email
		message.setSubject("Test");            	 
		message.setText("Hello "+(link+token)); 	// For Sending the token to email
		System.out.println("In Mail: "+ email);
		return message;
	}
}