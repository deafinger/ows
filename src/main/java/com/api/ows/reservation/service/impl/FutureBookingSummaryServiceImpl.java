package com.api.ows.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.ComponetObjectMapper;
import com.api.ows.reservation.model.futureBookingSummary.FutureBookingSummaryBody;
import com.api.ows.reservation.service.FutureBookingSummaryService;
import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class FutureBookingSummaryServiceImpl
 * @Description : FutureBookingSummary Logic 구현
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
@Slf4j
@Service
public class FutureBookingSummaryServiceImpl implements FutureBookingSummaryService {
	
	@Autowired
	ComponetObjectMapper mapper;
	
	@Override
	public Map<String,Object> doFutureBookingSummaryRequest(FutureBookingSummaryReqVO param) throws Exception {
		return null;
	}
	
	@Override
	public Map<String, Object> doFutureBookingSummaryRequestByDate(FutureBookingSummaryReqVO param) throws Exception {
		//2021-03-22T01:00:00.000Z
		SimpleDateFormat fmt = new SimpleDateFormat(CommonString.TZBetween);
		log.info("StartDate : {}", param.getStartDate());
		
		FutureBookingSummaryBody setting = new FutureBookingSummaryBody(param);
		
		
		Map<String, Object> body = mapper.getMapper().convertValue(setting, Map.class);
		
		Map<String,Object> bodyMap = new HashMap<String,Object>();
		bodyMap.put("FutureBookingSummaryRequest", body);
		
		
		OWSSoapConnection con = new OWSSoapConnection();
		Map<String,Object> result = con.doSoapConnection(bodyMap, "/Reservation.wsdl#FutureBookingSummary");
		
		return result;
	}
}
