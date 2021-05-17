package com.api.ows.reservadvanced.model.invoice;

import com.api.ows.common.soap.CommonString;
import com.api.ows.reservadvanced.vo.request.InvoiceReqVO;

import lombok.Builder;
import lombok.Data;

/**
 * @Class InvoiceBody
 * @Description : Soap Request Body Model Class 
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
@Builder
public class InvoiceBody {
	private String xmlns;
	private ReservationRequest ReservationRequest; 
	private ReturnFixedCharges ReturnFixedCharges;
	@Builder
	public InvoiceBody(InvoiceReqVO param) {
		this.xmlns = CommonString.RESVADVANCED;
		//hotelCode
		this.ReservationRequest = new ReservationRequest().builder()
				.HotelReference(
						new HotelReference().builder()
						.hotelCode(param.getHotelCode())
						.build()
						)
				.ReservationID(
						new ReservationID().builder()
						.UniqueID(
								new UniqueID().builder()
								.type(CommonString.TYPEEX)
								.source("RESV_NAME_ID")
								.xmlns(CommonString.COMMON)
								.nodeValue(param.getResvNameId())
								.build()
								)
						.build()
						)
				.build(); 
		
		;
		this.ReturnFixedCharges = new ReturnFixedCharges().builder().nodeValue("Y").build(); 
	}
}
