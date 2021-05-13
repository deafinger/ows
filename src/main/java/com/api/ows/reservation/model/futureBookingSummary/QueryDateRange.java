package com.api.ows.reservation.model.futureBookingSummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryDateRange {
	private String dataType;
	private String xmlns;
	
	private StartDate StartDate;
	private EndDate EndDate;
	
}
