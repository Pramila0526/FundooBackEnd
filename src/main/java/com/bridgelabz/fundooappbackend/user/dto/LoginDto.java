package com.bridgelabz.fundooappbackend.user.dto;

import javax.validation.constraints.NotBlank;

/**********************************************************************************************************
 * @author 	:Pramila Tawari 
 * Purpose	:Login Credentials
 *
 *********************************************************************************************************/

public class LoginDto 
{
	//@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\. [A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "mail id format wrong")
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	public LoginDto() 
	{

	}

	public LoginDto(String email, String password) 
	{
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String toString() 
	{
		return "LoginDto [email=" + email + ", password=" + password + "]";
	}
}
