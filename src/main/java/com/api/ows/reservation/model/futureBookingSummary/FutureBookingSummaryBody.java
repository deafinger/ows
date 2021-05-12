package com.api.ows.reservation.model.futureBookingSummary;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.api.ows.common.soap.CommonString;
import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class FutureBookingSummaryBody.java
 * @Description : FutureBookingSummary Request Body Model
 * @ @ 수정일 수정자 수정내용 @ --------- --------- ------------------------------- @
 * 2021. 5. 7. 서민재 최초생성
 *
 * @author 서민재
 * @since 2021. 5. 7.
 * @version 1.0
 *
 *          Copyright (주)아임게이트
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FutureBookingSummaryBody {
	private NameID NameID;
	private String xmlns; // # xmlns : 고유 Attribute
	private AdditionalFilters AdditionalFilters;
	private QueryDateRange QueryDateRange;

	@Builder
	public FutureBookingSummaryBody(FutureBookingSummaryReqVO param) {
		SimpleDateFormat fmt = new SimpleDateFormat(CommonString.TZBetween);
		SimpleDateFormat fmt2 = new SimpleDateFormat(CommonString.TBetween);
		
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		fmt2.setTimeZone(TimeZone.getTimeZone("UTC"));
		this.xmlns = "http://webservices.micros.com/ows/5.1/Reservation.wsdl";
		this.NameID = new NameID().builder().nodeValue(param.getNameId()).build();
		this.AdditionalFilters = new AdditionalFilters().builder()
									.CreationDate(new CreationDate().builder()
												.StartDate(new StartDate()
														.builder().nodeValue(fmt.format(param.getStartDate())).build())
												.EndDate(new EndDate().builder().nodeValue(fmt.format(param.getEndDate()))
														.build())
												.build()
									).build();
		this.QueryDateRange = new QueryDateRange().builder()
								.dataType(CommonString.ARRIVALDATE)
								.StartDate(new StartDate()
										.builder().nodeValue(fmt2.format(param.getStartDate())).build())
								.EndDate(new EndDate().builder().nodeValue(fmt2.format(param.getEndDate()))
										.build())
								.build();

	}
}
