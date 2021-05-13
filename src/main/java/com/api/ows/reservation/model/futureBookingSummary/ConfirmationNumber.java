package com.api.ows.reservation.model.futureBookingSummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ConfirmationNumber {
	private String xmlns;
	private String type;
	private String nodeValue;
}
