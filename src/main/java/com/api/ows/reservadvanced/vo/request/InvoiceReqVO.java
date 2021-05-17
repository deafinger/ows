package com.api.ows.reservadvanced.vo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.api.ows.reservation.vo.request.FetchBookingReqVO;

import lombok.Data;

/**
 * @Class InvoiceReqVO
 * @Description : 결제내역 조회 Paramemter VO 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 17.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 17.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Data
public class InvoiceReqVO {
	@NotNull(message = "필수 입력값 입니다.")
	@NotBlank(message = "값을 입력해야 합니다.")
	private String resvNameId;
	@NotNull(message = "필수 입력값 입니다.")
	@NotBlank(message = "값을 입력해야 합니다.")
	private String hotelCode;
}
