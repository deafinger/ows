package com.api.ows.reservation.model.futureBookingSummary;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class FutureBookingSummaryBody.java
 * @Description : FutureBookingSummary Request Body NODE Model
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
@Slf4j
public class FutureBookingSummaryBody {
	
	private String xmlns; // # xmlns : 고유 Attribute
	private NameID NameID;
	private AdditionalFilters AdditionalFilters;
	private QueryDateRange QueryDateRange;
	
	@Builder
	public FutureBookingSummaryBody(FutureBookingSummaryReqVO param) {
		// xmlns
		this.xmlns = CommonString.RESVWSDL;
		// NameID
		this.NameID = (param.getNameId()==null)?null:new NameID().builder().type(CommonString.TYPEIN).nodeValue(param.getNameId()).build();
		
		EndDate ed = (param.getEndDate()==null)?null:new EndDate(CommonUtill.jodaDateFormat(param.getEndDate(), CommonString.TBetween),CommonString.HOTELLCOMMON);
		StartDate sd = (param.getStartDate()==null)?null:new StartDate(CommonUtill.jodaDateFormat(param.getStartDate(), CommonString.TBetween),CommonString.HOTELLCOMMON);
		// QueryDateRange
		if(ed != null || sd !=null) {
			this.QueryDateRange = new QueryDateRange().builder()
					.dataType(CommonString.ARRIVALDATE)
					.EndDate(ed)
					.StartDate(sd)
					.build()
					;
		}
		
		//AdditionalFilters 
		ConfirmationNumber cfn = (param.getConfirmationId()==null)?null
									:new ConfirmationNumber(CommonString.RESERVATION,CommonString.TYPEIN ,param.getConfirmationId());
		HotelReference hrf = (param.getChainCode()==null ||param.getHotelCode()==null) ? null
									:new HotelReference(CommonString.RESERVATION,  param.getHotelCode(),param.getChainCode());
		boolean nothing = (cfn != null || hrf!=null);
		if(nothing) {
			this.AdditionalFilters = new AdditionalFilters().builder()
					.GetList("true")
					.ReservationDisposition("NONE")
					.ConfirmationNumber(cfn)
					.HotelReference(hrf)
					.build();
		}
		
	}
	
}
