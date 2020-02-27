package com.bridgelabz.fundooappbackend.collaborate.service;
import com.bridgelabz.fundooappbackend.collaborate.dto.CollaborateDto;
import com.bridgelabz.fundooappbackend.user.response.Response;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate Service
 *
 ***********************************************************************************************************/
public interface CollaborateService 
{
	public Response Collaborate(CollaborateDto collaboratorDto, String token );
}