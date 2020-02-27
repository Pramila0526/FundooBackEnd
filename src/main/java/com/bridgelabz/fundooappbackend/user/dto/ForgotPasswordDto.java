package com.bridgelabz.fundooappbackend.user.dto;

import javax.validation.constraints.NotBlank;

/**********************************************************************************************************
 * @author 	:Pramila Tawari 
 * Purpose	:ForgetPasswordDto uses Email for password recovery
 *
 *********************************************************************************************************/

public class ForgotPasswordDto 
{
	@NotBlank
	private String email;
	
	
	public ForgotPasswordDto() 
	{
	}
	
	public ForgotPasswordDto(String email) 
	{		
		super();
		this.email = email;
	}
	
	public String getEmail() 
	{		
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String toString() 
	{
		return "ForgetPasswordDto [email=" + email + "]";
	}
}