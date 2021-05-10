package com.api.ows.reservation.service;

import java.util.Map;

import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;
import com.api.ows.reservation.vo.response.FutureBookingSummaryResVO;

/**
 * @Class FutureBookingSummaryService.java
 * @Description : FutureBookingSummary OWS ��� ���� service
 * @
 * @ ������      	     ������           ��������
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 6.     ������     		���ʻ���
 *
 * @author ������
 * @since 2021. 5. 6.
 * @version 1.0
 *
 *  Copyright (��)���Ӱ���Ʈ
 */
public interface FutureBookingSummaryService {
	/**
	* @param  FutureBookingSummaryReqVO
	* @return Map<String,Object> 
	* @see solom
	*/
	Map<String,Object>  doFutureBookingSummaryRequest(FutureBookingSummaryReqVO param) throws Exception;
	
	
	/**
	* @Description : StartDate와 EndDate로 목록 조회
	* @param  
	* @return Map<String,Object>
	* @author 서민재
	*/
	Map<String,Object>  doFutureBookingSummaryRequestByDate(FutureBookingSummaryReqVO param) throws Exception;
	
}
