package com.bridgelabz.fundooappbackend.note.controller;
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
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooappbackend.note.dto.LabelDto;
import com.bridgelabz.fundooappbackend.note.service.LabelService;
import com.bridgelabz.fundooappbackend.user.response.Response;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Label Controller for Generating API's
 *
 ***********************************************************************************************************/
@RestController   
@RequestMapping("/label")
@CrossOrigin(allowedHeaders = "*" , origins = "*")
public class LabelController {
	
	 @Autowired
     LabelService labelServiceImplementation;
	 
	 // for testing the api
	 @GetMapping("/demolabel")
     public String demo()
     {
   	  return "Hello Label User!!";
     }	
	 
	 // Adding Label
	 @PostMapping("/addlabel")
	  	public ResponseEntity<Response> addLabel(@RequestBody LabelDto labelDto,@RequestHeader String token)
	  	{
	  		return new ResponseEntity<Response>(labelServiceImplementation.addLabel(labelDto,token), HttpStatus.OK); // give response for user 200
	  	}
	 
	 // Updateing Label
	 @PutMapping("/updatelabel/{id}")
	  	public ResponseEntity<Response> updateLabel(@Valid @PathVariable int id,@RequestBody LabelDto updateLabelDto,@RequestHeader String token)
	  	{
	  		return new ResponseEntity<Response>(labelServiceImplementation.updateLabel(id,updateLabelDto, token), HttpStatus.OK); // give response for user 200
	  	}
	 
	 // Deleting Label
	 @DeleteMapping("deletelabel/{id}")
	  	public ResponseEntity<Response> deleteLabel(@PathVariable int id,@RequestHeader String token)
	  	{
	  		return new ResponseEntity<Response>(labelServiceImplementation.deleteLabel(id, token), HttpStatus.OK); // give response for user 200
	  	}
	 
	 // Finding Label
	 @GetMapping("/findlabel/{id}")
		public ResponseEntity<Response> findLabel(@PathVariable int id, @RequestHeader String token) 
		{
			return new ResponseEntity<Response>(labelServiceImplementation.findLabel(id,token), HttpStatus.OK);
		}
	 
	 // Getting All Labels
	 @GetMapping("/getalllabels")
		public ResponseEntity<Response> getAllLabels(@RequestHeader String token) 
		{
			return new ResponseEntity<Response>(labelServiceImplementation.getAllLabels(token), HttpStatus.OK);
		}
}
