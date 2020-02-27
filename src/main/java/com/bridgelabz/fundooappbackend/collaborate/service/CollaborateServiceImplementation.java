package com.bridgelabz.fundooappbackend.collaborate.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooappbackend.collaborate.dto.CollaborateDto;
import com.bridgelabz.fundooappbackend.collaborate.model.Collaborate;
import com.bridgelabz.fundooappbackend.collaborate.repository.CollaborateRepository;
import com.bridgelabz.fundooappbackend.collaborate.utility.CollaborateUtility;
import com.bridgelabz.fundooappbackend.note.message.Messages;
import com.bridgelabz.fundooappbackend.note.model.Note;
import com.bridgelabz.fundooappbackend.note.repository.NotesRepository;
import com.bridgelabz.fundooappbackend.note.utility.TokensUtility;
import com.bridgelabz.fundooappbackend.user.exception.custom.NoteNotFoundException;
import com.bridgelabz.fundooappbackend.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundooappbackend.user.response.Response;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate Implementation Class To Perform Collaboration
 *
 ***********************************************************************************************************/
@Service
@PropertySource("message.properties")
public class CollaborateServiceImplementation implements CollaborateService
{
	
	@Autowired
	private CollaborateRepository collaborateRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TokensUtility tokenUtility;

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Autowired
	private NotesRepository noteRepository;
	
	@Autowired
	private Environment environment;
	
	@Override
	public Response Collaborate(CollaborateDto collaborateDto , String token )
	{
		if(collaborateDto.getNoteID()==0 )
		{
			new NoteNotFoundException(Messages.NOTE_NOT_FOUND);
		}
		
 		Collaborate collaborator = mapper.map(collaborateDto , Collaborate.class);
		System.out.println("2");
		String useremail = tokenUtility.getUserToken(token);
		System.out.println("3");
		if(useremail == null)
		{
			throw new UserNotFoundException(Messages.USER_NOT_FOUND);
		}
		System.out.println("5");
		collaborator.setSenderMail(useremail);
		System.out.println("6");
		
		Note note = noteRepository.findById(collaborateDto.getNoteID());
		if(note== null)
		{
			throw new NoteNotFoundException(Messages.NOTE_NOT_FOUND);
		}
		
		javaMailSender.send(CollaborateUtility.sendMail(collaborator.getSenderMail(), collaborateDto.getReceiverMail(), "this is the note"+note ));
		
		collaborateRepository.save(collaborator);
		
		return new Response(Integer.parseInt(environment.getProperty("status.ok.code")),
				environment.getProperty("status.success.collaborationdone"), environment.getProperty("success.status"));		}
}