package com.api.ows.reservation.model.futureBookingSummary;

import java.util.HashMap;
import java.util.Map;


import com.api.ows.common.soap.CommonString;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;
import com.github.underscore.lodash.U;

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
	
	private Map<String,Object> body;
	
	
	@Builder
	public FutureBookingSummaryBody(FutureBookingSummaryReqVO param) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		U.set(map, "FutureBookingSummaryRequest", new HashMap<String, Object>());
		U.set(map, "FutureBookingSummaryRequest.xmlns", CommonString.RESVWSDL);
		if(param.getNameId()!=null) {
			U.set(map, "FutureBookingSummaryRequest.NameID", new HashMap<String, Object>());
			U.set(map, "FutureBookingSummaryRequest.NameID.type", CommonString.TYPEIN);
			U.set(map, "FutureBookingSummaryRequest.NameID.nodeValue", param.getNameId());
		}
		// QueryDateRange
		if(param.getEndDate()!=null && param.getStartDate()!=null) {
			U.set(map, "FutureBookingSummaryRequest.QueryDateRange", new HashMap<String, Object>());
			U.set(map, "FutureBookingSummaryRequest.QueryDateRange.dataType", CommonString.ARRIVALDATE);
			U.set(map, "FutureBookingSummaryRequest.QueryDateRange.EndDate", new HashMap<String, Object>());
			U.set(map, "FutureBookingSummaryRequest.QueryDateRange.EndDate.xmlns", CommonString.HOTELLCOMMON);
			U.set(map, "FutureBookingSummaryRequest.QueryDateRange.EndDate.nodeValue", CommonUtill.jodaDateFormat(param.getEndDate(), CommonString.TBetween));
			U.set(map, "FutureBookingSummaryRequest.QueryDateRange.StartDate", new HashMap<String, Object>());
			U.set(map, "FutureBookingSummaryRequest.QueryDateRange.StartDate.xmlns", CommonString.HOTELLCOMMON);
			U.set(map, "FutureBookingSummaryRequest.QueryDateRange.StartDate.nodeValue", CommonUtill.jodaDateFormat(param.getStartDate(), CommonString.TBetween));
		}
		
		//AdditionalFilters
		if((param.getConfirmationId()!=null) || (param.getChainCode()!=null && param.getHotelCode()!=null)) {
			U.set(map, "FutureBookingSummaryRequest.AdditionalFilters", new HashMap<String, Object>());
			U.set(map, "FutureBookingSummaryRequest.AdditionalFilters.GetList", "true");
			U.set(map, "FutureBookingSummaryRequest.AdditionalFilters.ReservationDisposition", "NONE");
			if(param.getConfirmationId()!=null) {
				U.set(map, "FutureBookingSummaryRequest.ConfirmationNumber", new HashMap<String, Object>());
				U.set(map, "FutureBookingSummaryRequest.ConfirmationNumber.xml", CommonString.RESERVATION);
				U.set(map, "FutureBookingSummaryRequest.ConfirmationNumber.type", CommonString.TYPEIN);
				U.set(map, "FutureBookingSummaryRequest.ConfirmationNumber.nodeValue", param.getConfirmationId());
			}
			if(param.getChainCode()!=null && param.getHotelCode()!=null) {
				U.set(map, "FutureBookingSummaryRequest.HotelReference", new HashMap<String, Object>());
				U.set(map, "FutureBookingSummaryRequest.HotelReference.xmlns", CommonString.RESERVATION);
				U.set(map, "FutureBookingSummaryRequest.HotelReference.chainCode", param.getChainCode());
				U.set(map, "FutureBookingSummaryRequest.HotelReference.hoteCode", param.getHotelCode());
			}
		}
		this.body=map;
	}
	
	
	
	
	
}
