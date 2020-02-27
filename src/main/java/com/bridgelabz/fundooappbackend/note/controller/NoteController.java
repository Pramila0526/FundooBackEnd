package com.bridgelabz.fundooappbackend.note.controller;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooappbackend.note.dto.NoteDto;
import com.bridgelabz.fundooappbackend.note.model.Note;
import com.bridgelabz.fundooappbackend.note.service.NoteService;
import com.bridgelabz.fundooappbackend.user.message.Messages;
import com.bridgelabz.fundooappbackend.user.response.Response;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Notes Controller For Generating API's
 *
 ***********************************************************************************************************/
@RestController   
@RequestMapping("/note")
@CrossOrigin(allowedHeaders = "*" , origins = "*")
public class NoteController 
{
      @Autowired
      NoteService notesServiceImplementation;
	
	  // Testing API
      @GetMapping("/demoo")
      public String demo()
      {
    	  return "Hello User!!";
      }
      
    @GetMapping("/addnote")
  	public Response addNote(@RequestBody Note noteDto) throws Exception 
  	{
  		return new Response(Messages.OK,"Note Added Successfully!!",notesServiceImplementation.addNote(noteDto));
  	}

    // Adding New Note
    @PostMapping("/addnewnote")
  	public ResponseEntity<Response> addNewNote(@RequestBody NoteDto noteDto,@RequestHeader String token) throws Exception
  	{
  		return new ResponseEntity<Response>(notesServiceImplementation.addNewNote(noteDto,token), HttpStatus.OK); // give response for user 200
  	}
    
    // Updating a Note
    @PutMapping("/updatenote/{id}")
  	public ResponseEntity<Response> updateNote(@PathVariable int id,@RequestBody NoteDto updateNoteDto,@RequestHeader String token) throws Exception
  	{
  		return new ResponseEntity<Response>(notesServiceImplementation.updateNote(id,updateNoteDto, token), HttpStatus.OK); // give response for user 200
  	}
    
    // Delete a Note
    @DeleteMapping("/deletenote/{id}")
  	public ResponseEntity<Response> deleteNote(@PathVariable int id,@RequestHeader String token) throws Exception
  	{
  		return new ResponseEntity<Response>(notesServiceImplementation.deleteNote(id, token), HttpStatus.OK); // give response for user 200
  	}
    
    // Getting all Notes
    @GetMapping("/getallnotes")
	public ResponseEntity<Response> getAllNotes(@RequestHeader String token) 
	{
  		return new ResponseEntity<Response>(notesServiceImplementation.getAllNotes(token), HttpStatus.OK); // give response for user 200
	}
    
 // Getting all Archive Notes
    @GetMapping("/getallarchivenotes")
	public ResponseEntity<Response> getAllArchiveNotes(@RequestHeader String token) 
	{
  		return new ResponseEntity<Response>(notesServiceImplementation.findAllArchiveNotes(token), HttpStatus.OK); // give response for user 200
	}
    
 // Getting all Trah Notes
    @GetMapping("/getalltrashnotes")
	public ResponseEntity<Response> getAllTrashNotes(@RequestHeader String token) 
	{
  		return new ResponseEntity<Response>(notesServiceImplementation.findAllTrashNotes(token), HttpStatus.OK); // give response for user 200
	}
    
    // Getting all Notes
    @GetMapping("/findnotebyid/{id}")
	public ResponseEntity<Response> findUserNoteById(@Valid @PathVariable int id,@RequestHeader String token) 
	{
  		return new ResponseEntity<Response>(notesServiceImplementation.findNote(id, token), HttpStatus.OK); // give response for user 200
	}
    
    // Sorting notes By title
    @GetMapping("/sortbytitle")
	public Response sortByTitle(@RequestHeader String token) 
	{
		return new Response(Messages.OK,"Sorted Notes By Title",notesServiceImplementation.sortByTitle(token));
	}
    
    // Sorting notes By Description
    @GetMapping("/sortbydescription")
	public Response sortByDescription(@RequestHeader String token) 
	{
		return new Response(Messages.OK,"Sorted Notes By Description",notesServiceImplementation.sortByDescription(token));
	}
    
    // Sorting notes By Date
    @GetMapping("/sortbydate")
	public Response sortByDate(@RequestHeader String token) 
	{
		return new Response(Messages.OK,"Sorted Notes By Date",notesServiceImplementation.sortByDate(token));
	}
    
    // Pin and Unpin The Notes
    @PutMapping("/pinunpin/{id}")
   	public ResponseEntity<Response> pinUnpin(@Valid @PathVariable int id,@RequestHeader String token) 
   	{
     		return new ResponseEntity<Response>(notesServiceImplementation.pinAndUnpin(id, token), HttpStatus.OK); // give response for user 200
   	}
    
    // Archive the Notes
    @PutMapping("/archive/{id}")
   	public ResponseEntity<Response> archive(@Valid @PathVariable int id,@RequestHeader String token) 
   	{
     		return new ResponseEntity<Response>(notesServiceImplementation.archive(id, token), HttpStatus.OK); // give response for user 200
   	}
    
    // Trash the Notes
    @PutMapping("/trash/{id}")
   	public ResponseEntity<Response> trash(@Valid @PathVariable int id,@RequestHeader String token) 
   	{
     		return new ResponseEntity<Response>(notesServiceImplementation.trash(id, token), HttpStatus.OK); // give response for user 200
   	}
    
    @PostMapping("/addreminder")
   	public ResponseEntity<Response> addReminder(@Valid @RequestParam int id, String token, Date date) 
   	{
     		return new ResponseEntity<Response>(notesServiceImplementation.addReminder(id, token, date), HttpStatus.OK); // give response for user 200
   	}
    
    @PutMapping("/updatereminder")
   	public ResponseEntity<Response> updateReminder(@Valid @RequestParam int id, String token, Date date) 
   	{
     		return new ResponseEntity<Response>(notesServiceImplementation.updateReminder(id, token, date), HttpStatus.OK); // give response for user 200
   	}
    
    @DeleteMapping("/deletereminder")
   	public ResponseEntity<Response> deleteReminder(@Valid @RequestParam  int id, String token) 
   	{
     		return new ResponseEntity<Response>(notesServiceImplementation.deleteReminder(id, token), HttpStatus.OK); // give response for user 200
   	}
}










/*
 * //Finding a note
 * 
 * @GetMapping("/findnote/{id}") public ResponseEntity<Response>
 * findNote(@PathVariable int id, @RequestHeader String token) { return new
 * ResponseEntity<Response>(notesServiceImplementation.findNote(id,token),
 * HttpStatus.OK); }
 * 
 * // Displaying all the notes of particular user
 * 
 * @GetMapping("/showallnotes/{id}") public Response showUserNotes(@PathVariable
 * int id,@RequestHeader String token) { return new
 * Response(Messages.OK,null,notesServiceImplementation.showUserNotes(id,token))
 * ; }
 */