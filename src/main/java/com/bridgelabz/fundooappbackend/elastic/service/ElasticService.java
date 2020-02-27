package com.bridgelabz.fundooappbackend.elastic.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundooappbackend.note.model.Note;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Elastic Service Interface
 *
 ***********************************************************************************************************/
@Service
public interface ElasticService {

	public String createNote(Note note) throws Exception;
	public String updateNote(Note note) throws Exception;
	public Note searchById(String noteId) throws Exception;
	public String deleteNote(int noteId) throws Exception; 
	public List<Note> searchByTitle(String title) throws Exception;
	public List<Note> searchByWord(String word) throws Exception;
	//public List<Note> autocomplete(String prefixString);
   }
