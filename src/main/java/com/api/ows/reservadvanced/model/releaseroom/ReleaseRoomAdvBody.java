package com.api.ows.reservadvanced.model.releaseroom;

import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.reservadvanced.model.invoice.InvoiceBody;
import com.api.ows.reservadvanced.vo.request.InvoiceReqVO;
import com.api.ows.reservadvanced.vo.request.ReleaseRoomAdvReqVO;
import com.github.underscore.lodash.U;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class ReleaseRoomAdvBody
 * @Description : ReleaseRoomAdvRequest Body Model 
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
public class ReleaseRoomAdvBody {
	private Map<String,Object> body;
	
	@Builder
	public ReleaseRoomAdvBody(ReleaseRoomAdvReqVO param) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		U.set(map, "ReleaseRoomAdvRequest", new HashMap<String,Object>());
		U.set(map, "ReleaseRoomAdvRequest.xmlns", CommonString.RESVADVANCED);
		
		U.set(map, "ReleaseRoomAdvRequest.HotelReference", new HashMap<String,Object>());
		U.set(map, "ReleaseRoomAdvRequest.HotelReference.hotelCode", param.getHotelCode());
		
		U.set(map, "ReleaseRoomAdvRequest.ResvNameId", new HashMap<String,Object>());
		U.set(map, "ReleaseRoomAdvRequest.ResvNameId.type", CommonString.TYPEIN);
		U.set(map, "ReleaseRoomAdvRequest.ResvNameId.source", "RESV_NAME_ID");
		U.set(map, "ReleaseRoomAdvRequest.ResvNameId.nodeValue", param.getReservNameId());
		
		U.set(map, "ReleaseRoomAdvRequest.StationID", new HashMap<String,Object>());
		U.set(map, "ReleaseRoomAdvRequest.StationID.nodeValue", "KIOSK");
		
		this.body=map;
	}
}
