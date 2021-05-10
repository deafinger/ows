package com.api.ows.reservation.service;

import java.util.Map;

import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;
import com.api.ows.reservation.vo.response.FutureBookingSummaryResVO;


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
