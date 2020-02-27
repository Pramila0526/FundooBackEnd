package com.bridgelabz.fundooappbackend.user.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**********************************************************************************************************
 * @author 	:Pramila Tawari 
 * Purpose	:It returns encrypted user password and store it into the database 
 *
 *********************************************************************************************************/

@Configuration
public class PasswordConfiguration 
{
	/**
	 * @return 	
	 * Password encoder - Spring Security provides BCryptPasswordEncoder , and implementation of Spring's 
	 * 					PasswordEncoder interface that uses the BCrypt strong hashing function to encode 
	 * 					the password. 			
	 */
	@Bean
	public PasswordEncoder encoder() 
	{		
		return new BCryptPasswordEncoder();
	}
}