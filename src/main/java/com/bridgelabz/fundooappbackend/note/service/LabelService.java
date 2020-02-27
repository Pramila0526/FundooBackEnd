package com.bridgelabz.fundooappbackend.note.service;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundooappbackend.note.dto.LabelDto;
import com.bridgelabz.fundooappbackend.user.response.Response;

/**********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Label Service
 *
 *******************************************************************************************************/
@Service
public interface LabelService {
	public Response addLabel(LabelDto labelDto,String token);
	public Response updateLabel(@Valid int id,LabelDto updateLabelDto, String token);
	public Response deleteLabel(int id,String token);
	public Response findLabel(int id, String token);
	public Response getAllLabels(String token);
}
