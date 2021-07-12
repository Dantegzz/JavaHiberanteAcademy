/**
 * 
 */
package com.Dann97.ereservation.ui.resources.vo;

import java.util.Date;

import lombok.Data;


@Data
public class ReservationVO {
	private String codeRes;
	private Date startDateRes;
	private Date endDateRes;
	private int numberOfPeopleRes;
	private String descriptionRes;
	private ClientVO client;
}
