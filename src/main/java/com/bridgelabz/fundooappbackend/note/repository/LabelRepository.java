package com.bridgelabz.fundooappbackend.note.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooappbackend.note.model.Label;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Repository to store data in the Database
 *
 *********************************************************************************************************/
@Repository
public interface LabelRepository extends JpaRepository<Label, Object> 
{
	// For finding or recovering the user account by MailId
	// User findByEmail(String email);
    Label findById(int id);
    // Note deleteById(int id);
}