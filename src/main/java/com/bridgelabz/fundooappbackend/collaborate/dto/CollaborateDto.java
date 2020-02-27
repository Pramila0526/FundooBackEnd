package com.bridgelabz.fundooappbackend.collaborate.dto;
import javax.validation.constraints.NotNull;

/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate DTO 
 *
 ***********************************************************************************************************/
public class CollaborateDto
{
	
	@NotNull
	private String receiverMail;
	
	@NotNull
	private int noteID;
	

	public CollaborateDto()
	{
		
	}
			
	public CollaborateDto(@NotNull String receiverMail, @NotNull int noteID) {
		super();
		this.receiverMail = receiverMail;
		
		this.noteID = noteID;
	}

	
	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiver_mail) {
		this.receiverMail = receiver_mail;
	}

	

	public int getNoteID() {
		return noteID;
	}

	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}

	@Override
	public String toString() {
		return "CollaboratorDto [receiverMail=" + receiverMail +  ", noteID=" + noteID
				+ "]";
	}
	
	
	

}