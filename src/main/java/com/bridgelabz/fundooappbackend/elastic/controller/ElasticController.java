package com.bridgelabz.fundooappbackend.elastic.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooappbackend.elastic.service.ElasticService;
import com.bridgelabz.fundooappbackend.user.message.Messages;
import com.bridgelabz.fundooappbackend.user.response.Response;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Elastic Controller for Generating API's
 *
 ***********************************************************************************************************/

@RestController   
@RequestMapping("/elastic")
public class ElasticController {
	
	 @Autowired
     ElasticService elasticService;
	 
	 // For testing the API
	 @GetMapping("/demolabel")
     public String demo()
     {
   	  return "Hello Elastic User!!";
     }	
	 
	@GetMapping("/searchbytitle/{title}")
	public Response searchByTitle(@PathVariable String title) throws Exception 
	{
		return new Response(Messages.OK,"Search Notes By Title",elasticService.searchByTitle(title));
	}
	
	@GetMapping("/searchbyid/{id}")
	public Response searchById(@PathVariable int id) throws Exception 
	{
		return new Response(Messages.OK,"Search Notes By Id",elasticService.searchById(String.valueOf(id)));
	}
	
	@GetMapping("/searchbyword/{word}")
	public Response searchByWord(@PathVariable String word) throws Exception 
	{
		return new Response(Messages.OK,"Search Notes By Word",elasticService.searchByWord(word));
	}
}







/*
 * @GetMapping("/autocomplete/{prefixString}") public Response
 * autoComplete(@PathVariable String prefixString) throws Exception { return new
 * Response(Messages.OK,"Search Notes By Auto Complete",elasticService.
 * autocomplete(prefixString)); }
 */