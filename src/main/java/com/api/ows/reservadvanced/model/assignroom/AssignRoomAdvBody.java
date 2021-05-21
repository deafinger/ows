package com.api.ows.reservadvanced.model.assignroom;

import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.reservadvanced.model.checkin.CheckInBody;
import com.api.ows.reservadvanced.vo.request.AssignRoomAdvReqVO;
import com.api.ows.reservadvanced.vo.request.CheckInReqVO;
import com.github.underscore.lodash.U;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class AssignRoomAdvBody
 * @Description : 객실요청 Request Body Model 
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
public class AssignRoomAdvBody {
	private Map<String,Object> body;
	@Builder
	public AssignRoomAdvBody(AssignRoomAdvReqVO param) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		U.set(map, "AssignRoomAdvRequest", new HashMap<String,Object>());
		U.set(map, "AssignRoomAdvRequest.xmlns", CommonString.RESVADVANCED);
		
		U.set(map, "AssignRoomAdvRequest.HotelReference", new HashMap<String,Object>());
		U.set(map, "AssignRoomAdvRequest.HotelReference.hotelCode", param.getHotelCode());
		
		
		U.set(map, "AssignRoomAdvRequest.ResvNameId", new HashMap<String,Object>());
		U.set(map, "AssignRoomAdvRequest.ResvNameId.type", CommonString.TYPEIN);
		U.set(map, "AssignRoomAdvRequest.ResvNameId.source", "RESV_NAME_ID");
		U.set(map, "AssignRoomAdvRequest.ResvNameId.nodeValue", param.getReservNameId());
		
		U.set(map, "AssignRoomAdvRequest.RoomNoRequested", new HashMap<String,Object>());
		U.set(map, "AssignRoomAdvRequest.RoomNoRequested.nodeValue", param.getRoomNumber());
		
//		U.set(map, "AssignRoomAdvRequest.StationID", new HashMap<String,Object>());
//		U.set(map, "AssignRoomAdvRequest.StationID.nodeValue", "KIOSK");
		this.body=map;
		
	}
}
