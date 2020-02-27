package com.bridgelabz.fundooappbackend.user.message;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Messages for identifying the Status of
 *         Implementation
 *
 *********************************************************************************************************/

public class Messages {

	public static final String EMAIL_ALREADY_REGISTERED = "Email Id is Already Registered, Please try with other Email";
	
	public static final String REGISTRATION_MAIL_TEXT = "\t Validate your email \n"
														+ "http://localhost:3000/login?token=";
	
	public static final String INVALID_EMAIL = "Invalid Username Or Empty Field*";
	
	public static final String ENTER_EMAIL = "Please Enter Email ID First*";
	
	public static final String LINK_NOT_ACTIVE = "Activate your account link that sent to your email id";
	
	public static final String USER_ADDED_SUCCESSFULLY = "User Registration Successful!!";
	
	public static final String NOT_VERFIY_EMAIL = "Please Verify Your Email";
	
	public static final String EMAIL_VERIFIED = "Email Verification Successful!!";
	
	public static final String LOGIN_SUCCESSFUL = "Succesfully Logged In";
	
	public static final String LOGIN_UNSUCCESSFUL = "Login Failed*";

	public static final String USER_UPDATE_SUCCESSFULLY = "User Successfully Updated!!";
	
	public static final String USER_NOT_EXISTING = "User Doesn't Exists or Invalid Email";
	
	public static final String PASSWORD_CHANGED_SUCCESSFULLY = "Password Successfully Changed!!";
	
	public static final String PASSWORD_NOT_CHANGED_SUCCESSFULLY = "Password Not Changed**";
	
	//public static final String MAIL_SENT = "Token has been Sent to your Mail";
	
	public static final String PASSWORD_NOT_MATCHING = "Both password should be matched, Please Try Again";
	
	public static final String INVALID_TOKEN = "Invalid Token**";
	
	public static final String VERIFY_MAIL= "\t Validate your email \n"
			+ "http://localhost:3000/resetpassword?token=";
	
	// Status Code
	public static final int OK = 200;
	
	public static final int BAD_REQUEST = 500;
	
	public static final int NOT_FOUND = 404;
	
	// For Notes
	public static final String NOTE_CREATED = "Notes created Successfully";

	public static final String NOTE_UPDATED = "Note updated Successfully";

	public static final Object NOTE_DELETED = "Note Deleted";
}