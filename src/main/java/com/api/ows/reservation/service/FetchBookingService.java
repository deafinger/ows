package com.api.ows.reservation.service;

import java.util.Map;

import com.api.ows.reservation.vo.request.FetchBookingReqVO;
import com.api.ows.reservation.vo.response.FetchBookingResVO;

/**
 * @Class FetchBookingService
 * @Description : 캡슐화를 위한 Interface
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
public interface FetchBookingService {
	
	/**
	* @Description : 예약상세조회
	* @param  FetchBookingReqVO
	* @return FetchBookingResVO
	* @author 서민재
	*/
	Map<String, Object>  doFetchBookingRequest(FetchBookingReqVO param) throws Exception;
}
