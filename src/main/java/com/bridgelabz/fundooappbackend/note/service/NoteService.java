package com.bridgelabz.fundooappbackend.note.service;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.bridgelabz.fundooappbackend.note.dto.NoteDto;
import com.bridgelabz.fundooappbackend.note.model.Note;
import com.bridgelabz.fundooappbackend.user.response.Response;

/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Note Service Interface
 *
 ***********************************************************************************************************/
@Service
public interface NoteService {
	public Response addNewNote(NoteDto noteDto,String token) throws Exception;
	public Response updateNote(@Valid int id,NoteDto updateNoteDto, String token)throws Exception; 
	public Response deleteNote(int id, String token) throws Exception; 
	public Response getAllNotes(@RequestHeader String token);
	public List<Note> sortByDescription(String token);
	public Response findNote(int id, String token);
	public List<Note> sortByTitle(String token);
	public List<Note> sortByDate(String token);
	public Response pinAndUnpin(@Valid int id,String token);
	public Response archive(@Valid int id,String token);
	public Response trash(@Valid int id,String token);
	Response addReminder(@Valid int id, String token, Date date);
	Response updateReminder(@Valid int id, String token, Date date);
	Response deleteReminder(@Valid int id, String token);
	public Response addNote(Note noteDto) throws Exception;
	public Response findAllArchiveNotes(String token);
	public Response findAllTrashNotes(String token);
//	public Response findNote(int id,String token);
//	public List<Note> showUserNotes(int id,String token);
}
