package com.bridgelabz.fundooappbackend.collaborate.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate Model
 *
 ***********************************************************************************************************/
@Entity
public class Collaborate implements Serializable
{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int collaboratorId;;
	@NotNull
	private String receiverMail;
	@NotNull
	private String senderMail;
	@NotNull
	private int noteID;
	
	public Collaborate()
	{
		
	}

	@Override
	public String toString() {
		return "Collaborate [collaboratorId=" + collaboratorId + ", receiverMail=" + receiverMail + ", senderMail="
				+ senderMail + ", noteID=" + noteID + "]";
	}

	public int getCollaboratorId() {
		return collaboratorId;
	}

	public void setCollaboratorId(int collaboratorId) {
		this.collaboratorId = collaboratorId;
	}

	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public int getNoteID() {
		return noteID;
	}

	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}

	public Collaborate(int collaboratorId, @NotNull String receiverMail, @NotNull String senderMail,
			@NotNull int noteID) {
		super();
		this.collaboratorId = collaboratorId;
		this.receiverMail = receiverMail;
		this.senderMail = senderMail;
		this.noteID = noteID;
	}
}