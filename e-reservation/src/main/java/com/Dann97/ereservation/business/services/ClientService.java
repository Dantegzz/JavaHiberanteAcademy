/**
 * 
 */
package com.Dann97.ereservation.business.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dann97.ereservation.model.Client;
import com.Dann97.ereservation.business.repository.ClientRepository;


@Service
@Transactional(readOnly = true)
public class ClientService {
	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}


	@Transactional
	public Client create(Client client) {
		return this.clientRepository.save(client);
	}


	@Transactional
	public Client update(Client client) {
		return this.clientRepository.save(client);
	}


	@Transactional
	public void delete(Client client) {
		this.clientRepository.delete(client);
	}


	public Client findByIdentificacion(String identificationCli) {
		return this.clientRepository.findByIdentificacion(identificationCli);
	}
	

	public List<Client> findAll(){
		return this.clientRepository.findAll();
	}
	

	public List<Client> findByLastNameCli(String lastNameCli){
		return this.clientRepository.findbyLastName(lastNameCli);
	}
	
	

	public Client findByEmailAccount(String email) {
		return this.findByEmailAccount(email);
	}

}
