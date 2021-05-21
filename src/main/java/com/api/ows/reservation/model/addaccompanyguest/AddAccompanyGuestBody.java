package com.api.ows.reservation.model.addaccompanyguest;

import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.reservation.vo.request.AddAccompanyGuestReqVO;
import com.github.underscore.lodash.U;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class AddAccompanyGuestBody
 * @Description : AddAccompanyGuest Requests Body Model
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 21.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 21.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddAccompanyGuestBody {
	private Map<String,Object> body;
	
	@Builder
	public AddAccompanyGuestBody(AddAccompanyGuestReqVO param) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		U.set(map, "AddAccompanyGuestRequest", new HashMap<String, Object>());
		U.set(map, "AddAccompanyGuestRequest.xmlns", CommonString.RESVWSDL);
		//Hotel
		U.set(map, "AddAccompanyGuestRequest.HotelReference", new HashMap<String, Object>());
		U.set(map, "AddAccompanyGuestRequest.HotelReference.chainCode", param.getChainCode());
		U.set(map, "AddAccompanyGuestRequest.HotelReference.hotelCode", param.getHotelCode());
		
		// Reservation 
		U.set(map, "AddAccompanyGuestRequest.ConfirmationNumber", new HashMap<String, Object>());
		U.set(map, "AddAccompanyGuestRequest.ConfirmationNumber.type", CommonString.TYPEIN);
		U.set(map, "AddAccompanyGuestRequest.ConfirmationNumber.nodeValue", param.getConfirmationNumber());
		
		// LegNum
		U.set(map, "AddAccompanyGuestRequest.LegNumber", new HashMap<String, Object>());
		U.set(map, "AddAccompanyGuestRequest.LegNumber.type", CommonString.TYPEIN);
		U.set(map, "AddAccompanyGuestRequest.LegNumber.nodeValue", param.getLegNumber());
		
		
		U.set(map, "AddAccompanyGuestRequest.Profile", new HashMap<String, Object>());
		U.set(map, "AddAccompanyGuestRequest.Profile.ProfileIDs", new HashMap<String, Object>());
		U.set(map, "AddAccompanyGuestRequest.Profile.ProfileIDs.xmlns", CommonString.NAME);
		U.set(map, "AddAccompanyGuestRequest.Profile.ProfileIDs.UniqueID", new HashMap<String, Object>());
		U.set(map, "AddAccompanyGuestRequest.Profile.ProfileIDs.UniqueID.xmlns", CommonString.COMMON);
		U.set(map, "AddAccompanyGuestRequest.Profile.ProfileIDs.UniqueID.type", CommonString.TYPEIN);
		U.set(map, "AddAccompanyGuestRequest.Profile.ProfileIDs.UniqueID.nodeValue", param.getNameId());
		
		
		this.body = map;
	}
}
