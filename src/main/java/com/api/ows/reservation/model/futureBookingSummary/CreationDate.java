package com.api.ows.reservation.model.futureBookingSummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreationDate {
	private StartDate StartDate;
	private EndDate EndDate;
}
