package com.Dann97.ereservation.business.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dann97.ereservation.model.Client;
import com.Dann97.ereservation.model.Reservation;
import com.Dann97.ereservation.business.repository.ReservationRepository;


@Service
@Transactional(readOnly = true)
public class ReservationService {

	private final ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Transactional
	public Reservation create(Reservation reservation) {
		return this.reservationRepository.save(reservation);
	}


	@Transactional
	public Reservation update(Reservation reservation) {
		return this.reservationRepository.save(reservation);
	}


	@Transactional
	public void delete(Reservation reservation) {
		this.reservationRepository.delete(reservation);
	}

	

	public List<Reservation> findAll(){
		return this.reservationRepository.findAll();
	}
	

	public List<Reservation> findByClient(Client client){
		return this.reservationRepository.findByCliente(client);
	}

	public List<Reservation> find(Date startDate, Date endDate){
		return this.reservationRepository.find(startDate, endDate);
	}

	public Reservation findByCodeRes() {
		return findByCodeRes();
	}

	public Reservation findByCodeRes(String codeReservation) {
		return this.reservationRepository.findByCodeRes(codeReservation);
	}
	
}
