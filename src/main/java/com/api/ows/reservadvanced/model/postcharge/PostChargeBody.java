package com.api.ows.reservadvanced.model.postcharge;

import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservadvanced.vo.request.PostChargeReqVO;
import com.github.underscore.lodash.U;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class PostChargeBody
 * @Description : PostCharge Request의 Body Model 
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
public class PostChargeBody {
	private Map<String,Object> body;
	
	@Builder
	public PostChargeBody(PostChargeReqVO param) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		U.set(map, "PostChargeRequest", new HashMap<String,Object>());
		U.set(map, "PostChargeRequest.xmlns", CommonString.RESVADVANCED);
		U.set(map, "PostChargeRequest.Account", param.getAccount());	//계좌
		U.set(map, "PostChargeRequest.Article", param.getArticle());	//품번
		
		U.set(map, "PostChargeRequest.Posting", new HashMap<String,Object>());
		U.set(map, "PostChargeRequest.Posting.PostTime", CommonUtill.jodaDateFormat(param.getPostDateTime(), CommonString.POSTING_TIME)); //추가된시간
		U.set(map, "PostChargeRequest.Posting.PostDate", CommonUtill.jodaDateFormat(param.getPostDateTime(), CommonString.POSTING_DATE)); //추가된날짜
		// HARD_CORD 추후 변경 필요
		U.set(map, "PostChargeRequest.Posting.UserID", "KIOSK")	;
		U.set(map, "PostChargeRequest.Posting.ShortInfo", param.getShortInfo());	//짧은 설명
		U.set(map, "PostChargeRequest.Posting.LongInfo", param.getLongInfo());		//긴 설명
		U.set(map, "PostChargeRequest.Posting.Charge", param.getCharge());			// 가격
		// HARD_CORD 추후 변경 필요 
		U.set(map, "PostChargeRequest.Posting.StationID", "KIOSK");
		
		U.set(map, "PostChargeRequest.Posting.ReservationRequestBase", new HashMap<String,Object>());
		
		U.set(map, "PostChargeRequest.Posting.ReservationRequestBase.HotelReference", new HashMap<String,Object>());
		U.set(map, "PostChargeRequest.Posting.ReservationRequestBase.HotelReference.hotelCode", param.getHotelcode()); //호텔코드
		
		U.set(map, "PostChargeRequest.Posting.ReservationRequestBase.ReservationID", new HashMap<String,Object>());
		U.set(map, "PostChargeRequest.Posting.ReservationRequestBase.ReservationID.UniqueID", new HashMap<String,Object>());
		U.set(map, "PostChargeRequest.Posting.ReservationRequestBase.ReservationID.UniqueID.type", CommonString.TYPEIN);
		U.set(map, "PostChargeRequest.Posting.ReservationRequestBase.ReservationID.UniqueID.xmlns", CommonString.COMMON);
		U.set(map, "PostChargeRequest.Posting.ReservationRequestBase.ReservationID.UniqueID.source", CommonString.RESV_NAME_ID);
		U.set(map, "PostChargeRequest.Posting.ReservationRequestBase.ReservationID.UniqueID.nodeValue", param.getReservNameId()); //시스템 예약ID
		
		this.body=map;
	}
}
