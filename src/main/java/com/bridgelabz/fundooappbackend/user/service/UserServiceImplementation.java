package com.bridgelabz.fundooappbackend.user.service;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.bridgelabz.fundooappbackend.user.configuration.PasswordConfiguration;
import com.bridgelabz.fundooappbackend.user.dto.ForgotPasswordDto;
import com.bridgelabz.fundooappbackend.user.dto.LoginDto;
import com.bridgelabz.fundooappbackend.user.dto.RegistrationDto;
import com.bridgelabz.fundooappbackend.user.dto.ResetPasswordDto;
import com.bridgelabz.fundooappbackend.user.exception.custom.ForgotPasswordException;
import com.bridgelabz.fundooappbackend.user.exception.custom.InputNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.RegistrationExcepton;
import com.bridgelabz.fundooappbackend.user.exception.custom.TokenException;
import com.bridgelabz.fundooappbackend.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.ValidateUserException;
import com.bridgelabz.fundooappbackend.user.message.MessageUtility;
import com.bridgelabz.fundooappbackend.user.message.Messages;
import com.bridgelabz.fundooappbackend.user.model.User;
import com.bridgelabz.fundooappbackend.user.repository.UserRepository;
import com.bridgelabz.fundooappbackend.user.response.Response;
import com.bridgelabz.fundooappbackend.user.utility.TokenUtility;
import  com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
/**********************************************************************************************************
 * @author :Pramila Mangesh Tawari 
 * Purpose :Service Implementation Class for
 *           implementing actual Flow/Logic
 *
 *********************************************************************************************************/

@Service
@PropertySource("message.properties")
public class UserServiceImplementation implements UserService {
	
	static Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	
	@Autowired
	private UserRepository repository; // create object user repo

	// JavaMailSender class
	@Autowired
	private JavaMailSender javaMailSender;

	// PasswordEncoder Object
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Token utility Object
	@Autowired
	private TokenUtility tokenutility;

	// ModelMapper for mapping from object to dto or vice versa
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordConfiguration passConfig;

	@Autowired
	private Environment environment;
//	static Logger logger = Logger.getLogger(UserServiceImplementation.class);

/**
 * @return User Registration Mathod :- After the User Registration, OTP/ Token
 *         in been sent to Users Mail For Verification Using JMS(Java Mail
 *         Sender).
 *
 *********************************************************************************************************/
	public Response register(RegistrationDto regdto) 
	{
		User user = mapper.map(regdto, User.class); // Mapping new User data into the user Class
		
		System.out.println(user.getFirstName());
		
		if (repository.findAll().stream().anyMatch(i -> i.getEmail().equals(regdto.getEmail()))) // check user already
		{
			logger.info("Registration Completed");
			throw new RegistrationExcepton(Messages.EMAIL_ALREADY_REGISTERED);
		}  
			user.setPassword(passwordEncoder.encode(regdto.getPassword()));
			System.out.println(user);
			user = repository.save(user); // Storing Users Data in Database
      			
			if (user == null) 
			{
				logger.info("Null Content");
				throw new RegistrationExcepton(Messages.ENTER_EMAIL);
			}
			String token = tokenutility.createToken(user.getEmail());
			System.out.println(token);
		
			javaMailSender
					.send(MessageUtility.verifyUserMail(regdto.getEmail(), token, Messages.REGISTRATION_MAIL_TEXT));
				
			logger.info("Token Sent");
			return new Response(Integer.parseInt(environment.getProperty("status.ok.code") ),environment.getProperty("status.success.registration"),environment.getProperty("success.status"));
		}
	
/**
 * @return Validating the User :- It Checks if the Token is from valid mail Id
 *         or Not. If token is valid, it sets the validation as 1.
 *
 ******************************************************************************************************/
	public Response validateUser(String token) 
	{
		String email = tokenutility.getUserToken(token); // get user id from user token.
		
		if (email.isEmpty()) 
		{
			throw new TokenException(Messages.INVALID_EMAIL);
		}

		User user = repository.findByEmail(email);
		
		if (user != null)
		{ // if userid is found validate should be true
			user.setValidate(true);
			repository.save(user);
			return new Response(Integer.parseInt(environment.getProperty("status.ok.code") ),
					environment.getProperty("status.success.emailverified"),environment.getProperty("success.status"));
		}
		else 
		{
			return new Response(Integer.parseInt(environment.getProperty("status.badrequest.code") ),
					environment.getProperty("status.success.emailnotverified"),environment.getProperty("failure.status"));
		}
	}

/**
 * @return User Login Method :- Login the Authenticated User
 *
 *******************************************************************************************************/
	public Response loginUser(LoginDto logindto) 
	{
		if(logindto.getEmail().isEmpty()) {
			throw new InputNotFoundException(Messages.ENTER_EMAIL);
		}
		
		User user = repository.findByEmail(logindto.getEmail()); // find email present or not
		System.out.println(user);
		if (user == null)
		{
			logger.info("Null Content");
			return new Response(Integer.parseInt(environment.getProperty("status.badrequest.code") ),environment.getProperty("status.success.usernotfound"),environment.getProperty("failure.status"));
		}
		
		String token = tokenutility.createToken(user.getEmail());

		if (!user.isValidate()) 
		{
			new ValidateUserException(Messages.LINK_NOT_ACTIVE);
		} 
		else
		{
			if (user.getEmail().equals(logindto.getEmail())
					&& passConfig.encoder().matches(logindto.getPassword(), user.getPassword())) 
			{ // encode the user
				logger.info("username password Matched");
				return new Response(Integer.parseInt(environment.getProperty("status.ok.code") ),environment.getProperty("status.success.login"),token,user);
			}
		}
		return new Response(Integer.parseInt(environment.getProperty("status.badrequest.code") ),environment.getProperty("status.success.loginunsuccess"),environment.getProperty("failure.status"));
	}
	
/**
 * @return Forgot Passwrod Method :- In Case if Password is not remebering then
 *         we can recover it by sending token to the email id.
 *
 ********************************************************************************************************/
	public Response forgotPassword(ForgotPasswordDto forgetPasswordDto) 
	{
		User user = repository.findByEmail(forgetPasswordDto.getEmail()); // find by user email id

		System.out.println(user);
		
		if (user == null)
		{ 
			logger.info("Null Content");
			throw new ForgotPasswordException(Messages.USER_NOT_EXISTING);
		} 
		else
		{
			String token = tokenutility.createToken(user.getEmail());
			System.out.println(token);
			logger.info("Token Generated");
			javaMailSender
					.send(MessageUtility.verifyUserMail(forgetPasswordDto.getEmail(), token, Messages.VERIFY_MAIL)); // send
			logger.info("Token Sent " +token);																									// email
		}
		return new Response(Integer.parseInt(environment.getProperty("status.ok.code") ),environment.getProperty("status.success.tokensent"),environment.getProperty("success.status"));
	}

/**
 * @return Set Password Method :- Changing the Password
 *
 ********************************************************************************************************/
	public Response setPassword(ResetPasswordDto setPasswordDto, String token) 
	{
		String useremail = tokenutility.getUserToken(token);

		User updateUser = repository.findByEmail(useremail);
		
		if (setPasswordDto.getPassword().equals(setPasswordDto.getConfirmpassword())) 
		{ // check password or cfmpassword
			logger.info("Username Password Matched");
			updateUser.setPassword(passwordEncoder.encode(setPasswordDto.getPassword())); // new password encode it

			//updateUserByEmail(updateUser, useremail);
			
			repository.save(updateUser);
			return new Response(Integer.parseInt(environment.getProperty("status.ok.code") ),environment.getProperty("status.success.passwordchanged"),environment.getProperty("success.status"));

			} 
		else
		{
			return new Response(Integer.parseInt(environment.getProperty("status.badrequest.code") ),environment.getProperty("status.success.passwordnotmatched"),environment.getProperty("failure.status"));
		}
	}

/**
 * @return Find User :- Particular user's data by the token
 *
 ********************************************************************************************************/
	public Response findUser(@Valid int id,String token) 
	{
		String email = tokenutility.getUserToken(token);
		
		if (email.isEmpty())
		{
//			logger.info("Email Doesn't Exists");
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		
		User user = repository.findByEmail(email);
		
		if (user == null)
		{ 
			logger.info("Null Content");
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}
		
		return new Response(Integer.parseInt(environment.getProperty("status.ok.code") ),environment.getProperty("status.success.userfound"),user);
	}

/**
 * @return Show All Users Method :- Showing the users's List
 *
 ********************************************************************************************************/
	public List<User> showAllUsers(String token) 
	{
		System.out.println("check");
		logger.info("All USers");
        String email = tokenutility.getUserToken(token);
		
		if (email.isEmpty())
		{
			logger.info("Email Doesn't Exists");
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		
		User user = repository.findByEmail(email);
		
		if (user == null)
		{ 
			logger.info("Null Content");
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}
		return repository.findAll(); // show all user details in JPA
	}
	
	public Response uploadPic(String token, MultipartFile file)
	{
       String email = tokenutility.getUserToken(token);
		
		User user = repository.findByEmail(email);
		
		if (user == null)
		{ 
			logger.info("Null Content");
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}
		
		if(file.isEmpty()){
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		
		File uploadFile=new File(file.getOriginalFilename());
		
		try {
			BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(uploadFile));
			try {
				outputStream.write(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      		try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dj4c1rfvv", "api_key", "777917398816736",
			  "api_secret", "HSNkwKrnRRYnICEbgLh5JRMEr_A"));
		
	Map<?,?> uploadProfile;
	
	try {
		uploadProfile = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());
	} catch (IOException e) {
		throw new TokenException(Messages.INVALID_TOKEN);
	}
		user.setProfilePic(uploadProfile.get("secure_url").toString());
		repository.save(user);

		return new Response(Integer.parseInt(environment.getProperty("status.ok.code") ),environment.getProperty("status.success.uploadedpic"),environment.getProperty("success.status"));
	 
		
	}
	
	
/**
 * @return Update User Method :- Updating the user account by new Information
 *
 ********************************************************************************************************/
	public String updateUserByEmail(User user, String email) 
	{
		User updateUser = repository.findByEmail(email);
		updateUser = user;
		repository.save(updateUser);
		logger.info("Upadated User");
		return Messages.USER_UPDATE_SUCCESSFULLY;
	}

}
