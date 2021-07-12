/**
 * 
 */
package com.Dann97.ereservation.ui.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dann97.ereservation.model.Client;
import com.Dann97.ereservation.business.services.ClientService;
import com.Dann97.ereservation.ui.resources.vo.ClientVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/client")
@Api(tags = "client")
public class ClientResource {

	private final ClientService clientService;

	public ClientResource(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping
	@ApiOperation(value = "Create Client", notes = "client creation service")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Client created"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Client> createClient(@RequestBody ClientVO clientVo) {
		Client client = new Client();
		client.setNameCli(clientVo.getNameCli());
		client.setLastNameCli(clientVo.getLastNameCli());
		client.setDirectionCli(clientVo.getDirectionCli());
		client.setPhoneCli(clientVo.getPhoneCli());
		client.setEmailCli(clientVo.getEmailCli());
		return new ResponseEntity<>(this.clientService.create(client), HttpStatus.CREATED);
	}

	@PutMapping("/{identification}")
	@ApiOperation(value = "Update client", notes = "client update service")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Client updated successfully"),
			@ApiResponse(code = 404, message = "Client not found") })
	public ResponseEntity<Client> updateClient(@PathVariable("identification") String identification,
												ClientVO clientVo) {

		Client client = this.clientService.findByIdentificacion(identification);
		if (client == null) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		} else {
			client.setNameCli(clientVo.getNameCli());
			client.setLastNameCli(clientVo.getLastNameCli());
			client.setDirectionCli(clientVo.getDirectionCli());
			client.setPhoneCli(clientVo.getPhoneCli());
			client.setEmailCli(clientVo.getEmailCli());
		}
		return new ResponseEntity<>(this.clientService.update(client), HttpStatus.OK);
	}

	@DeleteMapping("/{identification}")
	@ApiOperation(value = "Delete Client", notes = "Client Eraser service")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Client deleted"),
			@ApiResponse(code = 404, message = "Client not found") })
	public void removeClient(@PathVariable("identification") String identification) {
		Client client = this.clientService.findByIdentificacion(identification);
		if (client != null) {
			this.clientService.delete(client);
		}
	}

	@GetMapping
	@ApiOperation(value = "list clients", notes = "Client listing service")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Clients found: "),
			@ApiResponse(code = 404, message = "clients not found") })
	public ResponseEntity<List<Client>> findAll() {
		return ResponseEntity.ok(this.clientService.findAll());
	}
}
