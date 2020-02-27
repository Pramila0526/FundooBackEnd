package com.bridgelabz.fundooappbackend.user.exception.custom;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Defining Method Exception in case of Validation
 *
 *********************************************************************************************************/
public class ValidationException  extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public ValidationException(String message)
	{
		super(message);
	}
}
