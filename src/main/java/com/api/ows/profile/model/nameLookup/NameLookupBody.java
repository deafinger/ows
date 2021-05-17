package com.api.ows.profile.model.nameLookup;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.profile.vo.request.NameLookupReqVO;
import com.api.ows.reservation.model.futureBookingSummary.AdditionalFilters;
import com.api.ows.reservation.model.futureBookingSummary.ConfirmationNumber;
import com.api.ows.reservation.model.futureBookingSummary.EndDate;
import com.api.ows.reservation.model.futureBookingSummary.FutureBookingSummaryBody;
import com.api.ows.reservation.model.futureBookingSummary.HotelReference;
import com.api.ows.reservation.model.futureBookingSummary.NameID;
import com.api.ows.reservation.model.futureBookingSummary.QueryDateRange;
import com.api.ows.reservation.model.futureBookingSummary.StartDate;
import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameLookupBody {
	private String xmlns;
	private NameLookupCriteria NameLookupCriteria;
	
	@Builder
	public NameLookupBody(NameLookupReqVO param) {
		this.xmlns = CommonString.NAMEWSDL;
		
		final EmailAddress emailAddress = (param.getEmailAddress() == null) ? null : 
				new EmailAddress().builder()
					.xmlns(CommonString.NAME)
					.EmailAddress(new EmailAddress().builder().nodeValue(param.getEmailAddress()).build())
					.build();
		
		final NameLookup nameLookup = ((param.getFirstName() == null) && (param.getLastName() == null)) ? null : 
				new NameLookup().builder()
					.xmlns(CommonString.NAME)
					.FirstName(new FirstName().builder().nodeValue(param.getFirstName()).build())
					.LastName(new LastName().builder().nodeValue(param.getLastName()).build())
					.build();
		
		this.NameLookupCriteria = new NameLookupCriteria().builder()
			.EmailAddress(emailAddress)
			.NameLookup(nameLookup)
			.build();
	}
	
}
