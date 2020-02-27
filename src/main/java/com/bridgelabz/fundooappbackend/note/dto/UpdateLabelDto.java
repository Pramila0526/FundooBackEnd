package com.bridgelabz.fundooappbackend.note.dto;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Update Label DTO
 *
 *********************************************************************************************************/
public class UpdateLabelDto {

	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UpdateLabelDto(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public UpdateLabelDto() {
		super();
	}
	@Override
	public String toString() {
		return "UpdateLabelDto [id=" + id + ", name=" + name + "]";
	}
}
