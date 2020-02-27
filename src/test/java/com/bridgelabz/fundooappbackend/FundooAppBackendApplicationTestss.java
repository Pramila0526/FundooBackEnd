package com.bridgelabz.fundooappbackend;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bridgelabz.fundooappbackend.note.dto.NoteDto;
import com.bridgelabz.fundooappbackend.user.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
// @PropertySource("message.properties")
class FundooAppBackendApplicationTestss {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		//nc = new NoteController(notesServiceImplementation);
	}
	
	@Test
	public void addNoteTest() throws Exception
	{
		System.out.println("1");
	NoteDto note = new NoteDto();
	note.setTitle("Note One");
	note.setDescription("Note Desc");
	
	System.out.println("2");
	String jsonRequest = mapper.writeValueAsString(note);
		
	MvcResult result=mockMvc.perform(post("note/addnote").content(jsonRequest).
				content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
	
	String resultContent = result.getResponse().getContentAsString();
	
	Response response = mapper.readValue(resultContent,Response.class);
	
	assertEquals(true,response.getStatus());
	//Assertions.assertEquals(true,response.getStatus());
	}
}