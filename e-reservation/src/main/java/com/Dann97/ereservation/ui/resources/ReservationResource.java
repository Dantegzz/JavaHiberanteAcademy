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
import com.Dann97.ereservation.model.Reservation;
import com.Dann97.ereservation.business.services.ClientService;
import com.Dann97.ereservation.business.services.ReservationService;
import com.Dann97.ereservation.ui.resources.vo.ReservationVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/reservation")
@Api(tags = "reservation")
public class ReservationResource {

	private final ReservationService reservationService;
	private final ClientService clientService;
	
	public ReservationResource(ReservationService reservationService, ClientService clientService) {
		this.reservationService = reservationService;
		this.clientService = clientService;
	}
	
	@PostMapping
	@ApiOperation(value = "Create Reservation", notes = "Reservation creation service")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reservation booked successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Reservation> createReservation(@RequestBody ReservationVO reservationVO){
		Client client = clientService.findByIdentificacion(reservationVO.getClient().getIdentificationCli());
		Reservation reservation = new Reservation();
		reservation.setClient(client);
		reservation.setCodeRes(reservationVO.getCodeRes());
		reservation.setStartDateRes(reservationVO.getStartDateRes());
		reservation.setEndDateRes(reservationVO.getEndDateRes());
		reservation.setNumberOfPeopleRes(reservationVO.getNumberOfPeopleRes());
		reservation.setDescriptionRes(reservationVO.getDescriptionRes());
		
		return new ResponseEntity<>(this.reservationService.create(reservation), HttpStatus.CREATED);
	}
	
	@PutMapping("/{codeReserva}")
	@ApiOperation(value = "Update Reservation", notes = "Reservation update service")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reservation updated successfully"),
			@ApiResponse(code = 404, message = "Reservation not found") })
	public ResponseEntity<Reservation> updateReservation(@PathVariable("codeReserva") String codeReserva,
													 ReservationVO reservationVO) {

		Reservation reservation = this.reservationService.findByCodeRes(codeReserva);
		if (reservation == null) {
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
		} else {
			Client client = clientService.findByIdentificacion(reservationVO.getClient().getIdentificationCli());
			reservation.setClient(client);
			reservation.setStartDateRes(reservationVO.getStartDateRes());
			reservation.setEndDateRes(reservationVO.getEndDateRes());
			reservation.setNumberOfPeopleRes(reservationVO.getNumberOfPeopleRes());
			reservation.setDescriptionRes(reservationVO.getDescriptionRes());
		}
		return new ResponseEntity<>(this.reservationService.update(reservation), HttpStatus.OK);
	}

	@DeleteMapping("/{codigoReservation}")
	@ApiOperation(value = "Delete Reservation", notes = "Reservation deleting service")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reservation deleted successfully"),
			@ApiResponse(code = 404, message = "Reservation not found") })
	public void removeClient(@PathVariable("codeReservation") String codeReservation) {
		Reservation reservation = this.reservationService.findByCodeRes(codeReservation);
		if (reservation != null) {
			this.reservationService.delete(reservation);
		}
	}

	@GetMapping
	@ApiOperation(value = "Show reservations", notes = "Reservations listing service")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reservations: "),
			@ApiResponse(code = 404, message = "Reservation not found") })
	public ResponseEntity<List<Reservation>> findAll() {
		return ResponseEntity.ok(this.reservationService.findAll());
	}
}
