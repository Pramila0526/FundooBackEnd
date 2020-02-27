package com.bridgelabz.fundooappbackend.user.configuration;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Return Model Mapper Object
 *
 *********************************************************************************************************/

@Configuration
public class Config
{
	/**
	 * @return
	 * ModelMapper- ModelMapper consists of two separate processes: the matching process, 
	 * 				where a source and destination type’s properties are matched to each other, 
	 * 				and the mapping process where matched property values are converted from a 
	 * 				source to destination object.
	 **/

	@Bean
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}
}