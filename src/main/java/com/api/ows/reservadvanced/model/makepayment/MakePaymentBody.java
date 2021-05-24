package com.api.ows.reservadvanced.model.makepayment;

import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservadvanced.vo.request.MakePaymentReqVO;
import com.github.underscore.lodash.U;

import lombok.*;

/**
 * @Class MakeParymentBody
 * @Description : MakePaymentRequest를 보낼 Soap의 Body Model 
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
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentBody {

	private Map<String,Object> body;
	
	@Builder
	public MakePaymentBody(MakePaymentReqVO param) {
		
		Map<String,Object> map = new HashMap<>();
		
		U.set(map, "MakePaymentRequest", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.xmlns", CommonString.RESVADVANCED);
		
		U.set(map, "MakePaymentRequest.Posting", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.Posting.PostDate", CommonUtill.jodaDateFormat(param.getPostDate(), CommonString.BASIC_DATE_FORM));
		U.set(map, "MakePaymentRequest.Posting.ShortInfo", param.getShortInfo());
		U.set(map, "MakePaymentRequest.Posting.LongInfo", param.getLongInfo());
		U.set(map, "MakePaymentRequest.Posting.StationID", "KIOSK");
		U.set(map, "MakePaymentRequest.Posting.Charge", param.getCharge());

		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase.HotelReference", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase.HotelReference.chainCode", param.getChainCode());
		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase.HotelReference.hotelCode", param.getHotelCode());
		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase.ReservationID", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase.ReservationID.UniqueID", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase.ReservationID.UniqueID.type", CommonString.TYPEIN);
		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase.ReservationID.UniqueID.source", "RESV_NAME_ID");
		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase.ReservationID.UniqueID.xmlns", CommonString.COMMON);
		U.set(map, "MakePaymentRequest.Posting.ReservationRequestBase.ReservationID.UniqueID.nodeValue", param.getResvNameId());
		
		U.set(map, "MakePaymentRequest.CreditCardInfo", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.cardType", param.getCardType());
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.cardHolderName", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.cardHolderName.xmlns", CommonString.COMMON);
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.cardHolderName.nodeValue", param.getCardHolderName());
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.cardNumber", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.cardNumber.xmlns", CommonString.COMMON);
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.cardNumber.nodeValue", param.getCardNumber());
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.expirationDate", new HashMap<String,Object>());
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.expirationDate.xmlns", CommonString.COMMON);
		U.set(map, "MakePaymentRequest.CreditCardInfo.CreditCard.expirationDate.nodeValue", CommonUtill.jodaDateFormat(param.getExpirationDate(), CommonString.BASIC_DATE_FORM));
		this.body = map;
		
	}

}
