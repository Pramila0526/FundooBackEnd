package com.bridgelabz.fundooappbackend.user.exception.custom;

public class InputNotFoundException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	
	public  InputNotFoundException(String message)
	{
		super(message);
	}
}
