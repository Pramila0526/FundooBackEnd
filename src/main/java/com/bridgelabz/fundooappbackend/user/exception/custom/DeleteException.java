package com.bridgelabz.fundooappbackend.user.exception.custom;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Defining Method Exception in case of Deleteing the User
 *
 *********************************************************************************************************/
public class DeleteException  extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public DeleteException(String message)
	{
		super(message);
	}
}
