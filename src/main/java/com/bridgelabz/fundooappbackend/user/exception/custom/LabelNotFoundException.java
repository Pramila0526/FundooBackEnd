package com.bridgelabz.fundooappbackend.user.exception.custom;

public class LabelNotFoundException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
	
	public  LabelNotFoundException(String message)
	{
		super(message);
	}
}
