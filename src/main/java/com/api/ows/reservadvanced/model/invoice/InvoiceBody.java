package com.api.ows.reservadvanced.model.invoice;

import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.reservadvanced.vo.request.InvoiceReqVO;
import com.github.underscore.lodash.U;

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
	
	private Map<String,Object> body;
	
	@Builder
	public InvoiceBody(InvoiceReqVO param) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		U.set(map, "InvoiceRequest", new HashMap<String,Object>());
		U.set(map, "InvoiceRequest.xmlns", CommonString.RESVADVANCED);
		
		U.set(map, "InvoiceRequest.ReservationRequest", new HashMap<String,Object>());
		
		U.set(map, "InvoiceRequest.ReservationRequest.HotelReference", new HashMap<String,Object>());
		U.set(map, "InvoiceRequest.ReservationRequest.HotelReference.hotelCode", param.getHotelCode());
		
		U.set(map, "InvoiceRequest.ReservationRequest.ReservationID", new HashMap<String,Object>());
		U.set(map, "InvoiceRequest.ReservationRequest.ReservationID.UniqueID", new HashMap<String,Object>());
		U.set(map, "InvoiceRequest.ReservationRequest.ReservationID.UniqueID.type", CommonString.TYPEEX);
		U.set(map, "InvoiceRequest.ReservationRequest.ReservationID.UniqueID.source", "RESV_NAME_ID");
		U.set(map, "InvoiceRequest.ReservationRequest.ReservationID.UniqueID.xmlns", CommonString.COMMON);
		U.set(map, "InvoiceRequest.ReservationRequest.ReservationID.UniqueID.nodeValue", param.getResvNameId());
		
		U.set(map, "InvoiceRequest.ReturnFixedCharges", new HashMap<String,Object>());
		U.set(map, "InvoiceRequest.ReturnFixedCharges.nodeValue", "Y");
		this.body=map;
	}
}
