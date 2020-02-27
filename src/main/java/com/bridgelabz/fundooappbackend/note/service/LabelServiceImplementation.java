package com.bridgelabz.fundooappbackend.note.service;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooappbackend.note.dto.LabelDto;
import com.bridgelabz.fundooappbackend.note.message.Messages;
import com.bridgelabz.fundooappbackend.note.model.Label;
import com.bridgelabz.fundooappbackend.note.repository.LabelRepository;
import com.bridgelabz.fundooappbackend.note.utility.TokensUtility;
import com.bridgelabz.fundooappbackend.user.exception.custom.InputNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.TokenException;
import com.bridgelabz.fundooappbackend.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundooappbackend.user.model.User;
import com.bridgelabz.fundooappbackend.user.repository.UserRepository;
import com.bridgelabz.fundooappbackend.user.response.Response;

/**********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Label Service Implementation to perform operations On Label
 *
 *******************************************************************************************************/
@Service
@PropertySource("message.properties")
public class LabelServiceImplementation implements LabelService {
	
	@Autowired
	private ModelMapper mapper;
	 
	@Autowired
	private TokensUtility tokenUtility;
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private Environment environment;
	
/**
*     @return  Function to add a New Label
*
*************************************************************************************************/
	public Response addLabel(LabelDto labelDto,String token) 
	{
		if(labelDto.getName().isEmpty())
		{
			throw new InputNotFoundException(Messages.INPUT_NOT_FOUND);
		}
		
		Label label = mapper.map(labelDto, Label.class); // Mapping new Label data into the Label Model
		
		String userEmail = tokenUtility.getUserToken(token); 
		
		User user = repository.findByEmail(userEmail);
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}
		
		label.setUser(user);
		
		label = labelRepository.save(label); // Storing Users Data in Database
		
		return new Response(Integer.parseInt(environment.getProperty("status.ok.code")),
				environment.getProperty("status.success.labelcreated"), environment.getProperty("success.status"));	
		}
	
/**
*     @return  Function to upadte Label
*
*************************************************************************************************/
	public Response updateLabel(@Valid int id,LabelDto updateLabelDto, String token) 
	{
		if(updateLabelDto.getName().isEmpty())
		{
			throw new InputNotFoundException(Messages.INPUT_NOT_FOUND);
		}
		
		// Label label = mapper.map(updateLabelDto, Label.class);
		
		String userToken = tokenUtility.getUserToken(token);
		
		User user = repository.findByEmail(userToken);	
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}
		
		Label label = labelRepository.findById(id);
		
		if (label == null) {
			return new Response(Integer.parseInt(environment.getProperty("status.badrequest.code")),
					environment.getProperty("status.labelnotfound"), environment.getProperty("failure.status"));
		}
		
		label.setName(updateLabelDto.getName());
		label.setUser(user);
		
		labelRepository.save(label);
		
		return new Response(Integer.parseInt(environment.getProperty("status.ok.code")),
				environment.getProperty("status.success.labelupdated"), environment.getProperty("success.status"));	
	}

/**
*     @return  Function to delete Note
*
*************************************************************************************************/
	public Response deleteLabel(int id,String token) 
	{
		String userEmail = tokenUtility.getUserToken(token); 
	
		User user = repository.findByEmail(userEmail);
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}
		
		Label label = labelRepository.findById(id);
		
		if(label ==  null)
		{
			return new Response(Integer.parseInt(environment.getProperty("status.ok.code")),
					environment.getProperty("status.labelnotfound"), environment.getProperty("failure.status"));	
		}
		
		label.setUser(user);
		
		labelRepository.delete(label);
		
		return new Response(Integer.parseInt(environment.getProperty("status.ok.code")),
				environment.getProperty("status.success.labeldeleted"), environment.getProperty("success.status"));	
	}

/**
*     @return  Function to find a Label
*
*************************************************************************************************/
	public Response findLabel(int id, String token) {
		
		String userEmail = tokenUtility.getUserToken(token);
		
		if (userEmail.isEmpty())
		{
				throw new TokenException(Messages.INVALID_TOKEN);
		}
		
		User user = repository.findByEmail(userEmail);
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}
		
		Label label = labelRepository.findById(id);
		
		if(label ==  null)
		{
			return new Response(Integer.parseInt(environment.getProperty("status.ok.code")),
					environment.getProperty("status.labelnotfound"), environment.getProperty("failure.status"));	
		}
		
		label.setUser(user);
		
		return new Response(Integer.parseInt(environment.getProperty("status.ok.code")),
				environment.getProperty("status.success.labelfound"), label);	
		}
	
/**
*     @return  Function to get all labels
*
*************************************************************************************************/                                                                                                      
	public Response getAllLabels(String token)
	{
		String userEmail = tokenUtility.getUserToken(token);
		
		if (userEmail.isEmpty())
		{
				throw new TokenException(Messages.INVALID_TOKEN);
		}
		
		User user = repository.findByEmail(userEmail);
		
		if(user ==  null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_EXISTING);
		}
		
		List<Label> label = labelRepository.findAll().stream().filter(e -> e.getUser().getId() == user.getId()).collect(Collectors.toList());
		
		return new Response(Integer.parseInt(environment.getProperty("status.ok.code")),
				environment.getProperty("status.success.allabels"), label);	
	}
}

/***********************************************************************************************/