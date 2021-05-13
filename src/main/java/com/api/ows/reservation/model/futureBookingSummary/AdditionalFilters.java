package com.api.ows.reservation.model.futureBookingSummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdditionalFilters {
	private String xmlns;
	private String GetList;
	private String ReservationDisposition;
	private String RoomClass;
	
	private ConfirmationNumber ConfirmationNumber;
	private HotelReference HotelReference;
	private CreationDate CreationDate;
}
