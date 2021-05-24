package com.api.ows.reservadvanced.model.checkin;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservadvanced.model.invoice.InvoiceBody;
import com.api.ows.reservadvanced.vo.request.CheckInReqVO;
import com.github.underscore.lodash.U;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class CheckInBody
 * @Description : 체크인 Request의 Body Model 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 20.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 20.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInBody {
	private Map<String,Object> body;
	@Builder
	public CheckInBody(CheckInReqVO param) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		U.set(map, "CheckInRequest", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.xmlns", CommonString.RESVADVANCED);
		U.set(map, "CheckInRequest.xmlns:c", CommonString.COMMON);
		U.set(map, "CheckInRequest.xmlns:hc", CommonString.HOTELLCOMMON);
		U.set(map, "CheckInRequest.xmlns:n", CommonString.NAME);
		U.set(map, "CheckInRequest.xmlns:r", CommonString.RESERVATION);

		U.set(map, "CheckInRequest.GetKeyTrack", "true");
		U.set(map, "CheckInRequest.Keys", "2");
		U.set(map, "CheckInRequest.PrintRegistration", "true");
		U.set(map, "CheckInRequest.KeyEncoder", "");
		U.set(map, "CheckInRequest.ApprovalCode", "");
		
		U.set(map, "CheckInRequest.ReservationRequest", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.ReservationRequest.HotelReference", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.ReservationRequest.HotelReference.hotelCode", param.getHotelCode());
		
		U.set(map, "CheckInRequest.ReservationRequest.ReservationID", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.ReservationRequest.ReservationID.c:UniqueID", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.ReservationRequest.ReservationID.c:UniqueID.type", CommonString.TYPEIN);
		U.set(map, "CheckInRequest.ReservationRequest.ReservationID.c:UniqueID.source", "RESV_NAME_ID");
		U.set(map, "CheckInRequest.ReservationRequest.ReservationID.c:UniqueID.nodeValue", param.getReservNameId());
		
		U.set(map, "CheckInRequest.CreditCardInfo", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.CreditCardInfo.CreditCard", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.CreditCardInfo.CreditCard.cardType", param.getCardType());
		
		U.set(map, "CheckInRequest.CreditCardInfo.CreditCard.c:cardHolderName", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.CreditCardInfo.CreditCard.c:cardHolderName.nodeValue", param.getCardHolderName());
		U.set(map, "CheckInRequest.CreditCardInfo.CreditCard.c:cardNumber", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.CreditCardInfo.CreditCard.c:cardNumber.nodeValue", param.getCardNumber());
		U.set(map, "CheckInRequest.CreditCardInfo.CreditCard.c:expirationDate", new HashMap<String,Object>());
		U.set(map, "CheckInRequest.CreditCardInfo.CreditCard.c:expirationDate.nodeValue", CommonUtill.jodaDateFormat(param.getExpirationDate(), CommonString.BASIC_DATE_FORM));
		
		this.body=map;
	}
}
