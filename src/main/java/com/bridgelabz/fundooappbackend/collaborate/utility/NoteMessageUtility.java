package com.bridgelabz.fundooappbackend.collaborate.utility;
import org.springframework.mail.SimpleMailMessage;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Note Message Utility includes SimpleMailMessage, which is a inbuilt class of java which implements MailMessage
 *			 interface. Models a simple mail message, including data such as the from, to,
 *   		 cc, subject, and text fields. Consider JavaMailSender and JavaMail
 *			 MimeMessages for creating more sophisticated messages,
 *
 ***********************************************************************************************************/
public class NoteMessageUtility {
	
	public static SimpleMailMessage sendMail(String email1, String email2 ,String note)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setFrom(email1);
		msg.setTo(email2); // send mail to receivers Mail
		msg.setSubject("Note Sharing"); // send message to the user email account
		msg.setText("this is a  note " + note); // send token to the user email account
		return msg;
	}
}
