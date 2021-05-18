package com.api.ows.reservation.model.fetchBooking;


import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.reservation.vo.request.FetchBookingReqVO;
import com.github.underscore.lodash.U;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class FetchBookingBody
 * @Description : FertchBookingRequest 시 Body Model
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 14.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 14.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class FetchBookingBody {
	
	private Map<String,Object> body;
	
	@Builder
	
	public FetchBookingBody(FetchBookingReqVO param) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		U.set(map, "FetchBookingRequest", new HashMap<String, Object>());
		U.set(map, "FetchBookingRequest.xmlns", CommonString.RESVWSDL);
		
		U.set(map, "FetchBookingRequest.ConfirmationNumber", new HashMap<String, Object>());
		U.set(map, "FetchBookingRequest.ConfirmationNumber.nodeValue", param.getConfirmationId());
		U.set(map, "FetchBookingRequest.ConfirmationNumber.type", CommonString.TYPEIN);
		
		this.body = map;
	}
}

