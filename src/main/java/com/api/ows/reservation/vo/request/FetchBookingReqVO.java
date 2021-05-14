package com.api.ows.reservation.vo.request;

import javax.validation.constraints.NotNull;

import lombok.Data;



/**
 * @Class FetchBookingReqVO
 * @Description : 예약상세 Request VO 
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
public class FetchBookingReqVO {
	@NotNull
	private	String	confirmationId	;	//	# confirmationId : 고객, 스태프가 확인 가능한 예약 번호
}
