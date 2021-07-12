/**
 * 
 */
package com.Dann97.ereservation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * This class represents the Reservation Table
 * @author Dante Gonzalez
 *
 */
@Data
@Entity
@Table(name="reservation")
public class Reservation {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid2")
	private String idRes;
	private String codeRes;
	@Temporal(TemporalType.DATE)
	private Date startDateRes;
	@Temporal(TemporalType.DATE)
	private Date endDateRes;
	private int numberOfPeopleRes;
	private String descriptionRes;
	@ManyToOne
	@JoinColumn(name="idCli")
	private Client client;
}
