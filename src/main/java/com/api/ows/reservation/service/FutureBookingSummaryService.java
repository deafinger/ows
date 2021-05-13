package com.api.ows.reservation.service;

import java.util.Map;

import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;
import com.api.ows.reservation.vo.response.FutureBookingSummaryResVO;


/**
 * @Class FutureBookingSummaryService
 * @Description : FutureBookingSummary 관련 Service 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 11.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 11.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
public interface FutureBookingSummaryService {
	/**
	* @param  FutureBookingSummaryReqVO
	* @return Map<String,Object> 
	* @see solom
	*/
	Map<String,Object>  doFutureBookingSummaryRequest(FutureBookingSummaryReqVO param) throws Exception;
	
}
