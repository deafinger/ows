package com.api.ows.reservation.model.fetchBooking;


import com.api.ows.common.soap.CommonString;
import com.api.ows.reservation.vo.request.FetchBookingReqVO;

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
	private String xmlns; // # xmlns : 고유 Attribute
	private ConfirmationNumber ConfirmationNumber;
	
	@Builder
	public FetchBookingBody(FetchBookingReqVO param) {
		this.xmlns = CommonString.RESVWSDL;
		
		this.ConfirmationNumber = (param.getConfirmationId() == null)? null
				:new ConfirmationNumber().builder()
				.nodeValue(param.getConfirmationId())
				.type(CommonString.TYPEIN)
				.build();
	}
}

