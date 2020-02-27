package com.bridgelabz.fundooappbackend.user.exception.custom;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Defining Method Exception in case of Validating the User
 *
 *********************************************************************************************************/

public class ValidateUserException  extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public ValidateUserException(String message)
	{
		super(message);
	}
}
