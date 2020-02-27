package com.bridgelabz.fundooappbackend.note.dto;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Update Note DTO
 *
 *********************************************************************************************************/
public class UpdateNoteDto {
	
	private String id;
	private String title;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public UpdateNoteDto() {
		super();
	}

	public UpdateNoteDto(String id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "UpdateNoteDto [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
}
