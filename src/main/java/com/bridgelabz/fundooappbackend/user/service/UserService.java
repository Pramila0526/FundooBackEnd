package com.bridgelabz.fundooappbackend.user.service;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundooappbackend.user.dto.ForgotPasswordDto;
import com.bridgelabz.fundooappbackend.user.dto.LoginDto;
import com.bridgelabz.fundooappbackend.user.dto.RegistrationDto;
import com.bridgelabz.fundooappbackend.user.dto.ResetPasswordDto;
import com.bridgelabz.fundooappbackend.user.model.User;
import com.bridgelabz.fundooappbackend.user.response.Response;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Messages for identifying the Status of Implementation
 *
 *********************************************************************************************************/

public interface UserService 
{
	public Response register(RegistrationDto regdto); // To register the new user

	public Response loginUser(LoginDto logindto); // For User Login

	public Response setPassword(ResetPasswordDto setPasswordDto, String token); // For Setting the pasword

	public Response forgotPassword(ForgotPasswordDto forgetPasswordDto);   // To recover the account
	
	public List<User> showAllUsers(String token);  // To display All the users
	
	public Response findUser(@Valid int id,String token);    // For the particular user
	
	public Response uploadPic(String token, MultipartFile file);
}