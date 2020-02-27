package com.bridgelabz.fundooappbackend.user.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundooappbackend.note.model.Note;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :User Model Class
 *
 *********************************************************************************************************/

@Component
@Entity
@Table(name = "userDetails")
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	@Email(regexp =  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.(?:[A-Z]{2,}|com|org))+$")
	private String email;
	
	@NotNull
	// @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*+\\/|!\"£$%^&*()#[\\]@~'?><,.=-_]).{6,20}", message="Password must be between 6 and 20 characters and contain one uppercase letter, one lowercase letter, one digit and one special character.")
	private String password;
	
	@NotNull
	//@Pattern(regexp = "{10}")
	private long phoneNumber;
	
	@NotNull
	private boolean validate;
	
	@OneToMany
    private List<Note> note;
	
	private String profilePic;
	
	public User() 
	{

	}

	public User(int id, @NotNull String firstName, @NotNull String lastName, @NotNull String email,
			@NotNull String password, @NotNull long phoneNumber, @NotNull boolean validate,String profilePic)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.validate = validate;
		this.profilePic=profilePic;
	}

	public List<Note> getNote() {
		return note;
	}

	public void setNote(List<Note> note) {
		this.note = note;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public long getPhoneNumber() 
	{
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public boolean isValidate()
	{
		return validate;
	}

	public void setValidate(boolean validate) 
	{
		this.validate = validate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", validate=" + validate + ", note="
				+ note + ", profilePic=" + profilePic + "]";
	}

	
}
