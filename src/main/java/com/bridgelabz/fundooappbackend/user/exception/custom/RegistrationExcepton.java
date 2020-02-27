package com.bridgelabz.fundooappbackend.user.exception.custom;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Defining Method Exception in Registration
 *
 *********************************************************************************************************/

public class RegistrationExcepton extends RuntimeException 
{	
	private static final long serialVersionUID = 1L;
	
	public RegistrationExcepton(String message)
	{
		super(message);
	}
}