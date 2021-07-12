/**
 * 
 */
package com.Dann97.ereservation.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Dann97.ereservation.model.Client;


public interface ClientRepository extends JpaRepository<Client, String>{


	public List<Client> findbyLastName(String lastNameCli);
	

	public Client findByIdentificacion(String identificationCli);
	
	/**
	 * Definition of method to find a designated client by email
	 * @param email
	 * @return
	 */
	@Query("Select c from Cliente c where c.emailCli like %:email")
	public Client findByEmailAccount(@Param("email") String email);
	
}
