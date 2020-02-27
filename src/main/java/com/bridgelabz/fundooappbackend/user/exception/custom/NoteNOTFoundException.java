package com.bridgelabz.fundooappbackend.user.exception.custom;

public class NoteNOTFoundException extends RuntimeException 
{	
	private static final long serialVersionUID = 1L;
	
	public NoteNOTFoundException(String message)
	{
		super(message);
	}
}