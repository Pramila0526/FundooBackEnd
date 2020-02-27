package com.bridgelabz.fundooappbackend.user.exception.custom;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Defining Method Exception in case of login the User
 *
 *********************************************************************************************************/
public class LoginException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	
	public  LoginException(String message)
	{
		super(message);
	}
}
