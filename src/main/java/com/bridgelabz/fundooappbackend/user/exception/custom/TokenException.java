package com.bridgelabz.fundooappbackend.user.exception.custom;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Defining Method Exception in Token Generation
 *
 *********************************************************************************************************/

public class TokenException  extends RuntimeException
{	
	private static final long serialVersionUID = 1L;

	public TokenException(String message)
	{
		super(message);
	}
}
