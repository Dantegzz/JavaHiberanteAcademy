/**
 * 
 */
package com.Dann97.ereservation.business.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Dann97.ereservation.model.Client;
import com.Dann97.ereservation.model.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, String> {


	@Query("Select r from Reservation r where r.client =:cliente")
	public List<Reservation> findByClient(Client client);
	

	@Query("Select r from Reservation r where r.startDateRes =:startDate  and r.endDateRes =:endDate")
	public List<Reservation> find(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	public Reservation findByCodeRes(String codeReservation);
}
