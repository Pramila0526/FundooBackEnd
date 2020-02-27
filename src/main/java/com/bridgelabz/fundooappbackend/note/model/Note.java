package com.bridgelabz.fundooappbackend.note.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundooappbackend.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Note Model
 *
 ***********************************************************************************************************/
@Component
@Entity
@Table(name = "detailsNote")
@JsonIgnoreProperties(value = { "user" })
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	private String title;

	@NotNull
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToMany
	private List<Label> labels = new ArrayList<Label>();
	
	@Temporal(TemporalType.TIMESTAMP)
 	@CreationTimestamp
	private Date noteRegistrationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date noteUpdatedDate;
	
	private boolean pin;
	
	private boolean archieve;

	private boolean trash;
	
	private Date reminder;

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public Date getNoteRegistrationDate() {
		return noteRegistrationDate;
	}

	public void setNoteRegistrationDate(Date noteRegistrationDate) {
		this.noteRegistrationDate = noteRegistrationDate;
	}

	public Date getNoteUpdatedDate() {
		return noteUpdatedDate;
	}

	public void setNoteUpdatedDate(Date noteUpdatedDate) {
		this.noteUpdatedDate = noteUpdatedDate;
	}

	public boolean isPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}

	public boolean isArchieve() {
		return archieve;
	}

	public void setArchieve(boolean archieve) {
		this.archieve = archieve;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public Note(int id, @NotNull String title, @NotNull String description, User user, List<Label> labels,
			Date noteRegistrationDate, Date noteUpdatedDate, boolean pin, boolean archieve, boolean trash, Date reminder) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.user = user;
		this.labels = labels;
		this.noteRegistrationDate = noteRegistrationDate;
		this.noteUpdatedDate = noteUpdatedDate;
		this.pin = pin;
		this.archieve = archieve;
		this.trash = trash;
		this.reminder=reminder;
	}

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", description=" + description + ", user=" + user + ", labels="
				+ labels + ", noteRegistrationDate=" + noteRegistrationDate + ", noteUpdatedDate=" + noteUpdatedDate
				+ ", pin=" + pin + ", archieve=" + archieve + ", trash=" + trash + ", reminder=" + reminder + "]";
	}

	
}
