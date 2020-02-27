package com.bridgelabz.fundooappbackend.user.controller;
import javax.validation.Valid;

//import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundooappbackend.user.dto.ForgotPasswordDto;
import com.bridgelabz.fundooappbackend.user.dto.LoginDto;
import com.bridgelabz.fundooappbackend.user.dto.RegistrationDto;
import com.bridgelabz.fundooappbackend.user.dto.ResetPasswordDto;
import com.bridgelabz.fundooappbackend.user.message.Messages;
import com.bridgelabz.fundooappbackend.user.response.Response;
import com.bridgelabz.fundooappbackend.user.service.UserServiceImplementation;

/**********************************************************************************************************
 * @author 	:Pramila Tawari 
 * Purpose	:The controller invokes a business class to process business-related tasks, 
 * 			 and then redirects the client to a logical view name, which is resolved by 
 * 			 Spring’s dispatcher servlet in order to render results or output. That completes 
 * 			 a round trip of a typical request-response cycle.
 *
 *********************************************************************************************************/

/**
 * @return admin94
 * RestController is the combination of @controller and @ResponseBody
 * 
 * 	The Job of the @Controller is to create a map of Model Object and find a view but
 *  @RestController simply return the object 
 *  and object data is directly written into http response as JSON or XML
 */
@RestController 
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "*" , origins = "*")
public class UserController 
{
      @Autowired
      UserServiceImplementation userServiceImp;
	
      @GetMapping("/demo")
      public String demo() 
      {
    	  return "Hello User!!";
      }
      
    // API for registering the user into the database. 
	@PostMapping("/register")
	public ResponseEntity<Response> Register(@Valid @RequestBody RegistrationDto regDto)
	{
		return new ResponseEntity<Response>(userServiceImp.register(regDto), HttpStatus.OK); // give response for user 200
	}
	
	// API for validating the user into the database. 
	@PostMapping("/validate")
	public ResponseEntity<Response> validate(@Valid @RequestParam String token) 
	{
		return new ResponseEntity<Response>(userServiceImp.validateUser(token), HttpStatus.OK);
	}
	
	// API for Login the user from Database.
	@PutMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDto logindto)
	{
		return new ResponseEntity<Response>(userServiceImp.loginUser(logindto), HttpStatus.OK);
	}
	
	// API for recovering in case of forgotton password 
	@PostMapping("/forgotpassword")
	public ResponseEntity<Response> forgotPassword(@RequestBody  ForgotPasswordDto forgetPasswordDto) 
	{

		return new ResponseEntity<Response>(userServiceImp.forgotPassword(forgetPasswordDto), HttpStatus.OK);

	}
	
	// API for setting new Password
	@PutMapping("/setpassword")
	public ResponseEntity<Response> setNewPassword(@RequestHeader String token,
			@RequestBody ResetPasswordDto setpassworddto) 
	{
		return new ResponseEntity<Response>(userServiceImp.setPassword(setpassworddto, token), HttpStatus.OK);
	}
	
	// API for finding a particular user
	@GetMapping("/finduser/{id}")
	public ResponseEntity<Response> findUser(@Valid @PathVariable int id,@RequestHeader String token) 
	{
		return new ResponseEntity<Response>(userServiceImp.findUser(id,token), HttpStatus.OK);
	}
	
	// API to show all Users
	@GetMapping("/showallusers")
	public Response showAllUsers(@RequestParam String token) 
	{
		return new Response(Messages.OK,null,userServiceImp.showAllUsers(token));
	}
	
	@PostMapping("/uploadprofilepic")
	public ResponseEntity<Response>  uploadProfilePic(@RequestHeader String token, @RequestHeader MultipartFile file) 
	{
		return new ResponseEntity<Response>(userServiceImp.uploadPic(token, file), HttpStatus.OK);
	}
}