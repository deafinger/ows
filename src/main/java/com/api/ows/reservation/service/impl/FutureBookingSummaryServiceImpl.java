package com.api.ows.reservation.service.impl;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.api.ows.common.ComponetObjectMapper;
import com.api.ows.common.OWSSoapConnection;
import com.api.ows.reservation.model.futureBookingSummary.AdditionalFilters;
import com.api.ows.reservation.model.futureBookingSummary.CreationDate;
import com.api.ows.reservation.model.futureBookingSummary.EndDate;
import com.api.ows.reservation.model.futureBookingSummary.FutureBookingSummaryBody;
import com.api.ows.reservation.model.futureBookingSummary.NameID;
import com.api.ows.reservation.model.futureBookingSummary.StartDate;
import com.api.ows.reservation.service.FutureBookingSummaryService;
import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		log.info(" PRAM : {} " , param);
		NameID nameId = new NameID();
		
		nameId.setNodeValue(param.getNameId());
		nameId.setType("INTERNAL");
		FutureBookingSummaryBody setting = new FutureBookingSummaryBody(nameId, "http://webservices.micros.com/ows/5.1/Reservation.wsdl", null);
		Map<String, Object> body = new ObjectMapper().convertValue(setting, Map.class);
		
		Map<String,Object> bodyMap = new HashMap<String,Object>();
		bodyMap.put("FutureBookingSummaryRequest", body);
		
		
		OWSSoapConnection con = new OWSSoapConnection();
		Map<String,Object> result = con.doSoapConnection(bodyMap, "/Reservation.wsdl#FutureBookingSummary");
		
		return result;
	}
	
	@Override
	public Map<String, Object> doFutureBookingSummaryRequestByDate(FutureBookingSummaryReqVO param) throws Exception {
		FutureBookingSummaryBody setting = new FutureBookingSummaryBody()
				.builder()
				.xmlns("http://webservices.micros.com/ows/5.1/Reservation.wsdl")
				.AdditionalFilters(
						new AdditionalFilters()
						.builder()
						.CreationDate(
								new CreationDate()
								.builder()
								.StartDate( 
										new StartDate()
											.builder()
											.nodeValue(param.getStartDate())
											.build()
										)
								.EndDate(new EndDate()
											.builder()
											.nodeValue(param.getEndDate())
											.build()
										)
								.build()
								
								)
						.build()
						)
				.build();
		
		
		Map<String, Object> body = mapper.getMapper().convertValue(setting, Map.class);
		
		log.info("Body : {}", body);
		
		Map<String,Object> bodyMap = new HashMap<String,Object>();
		bodyMap.put("FutureBookingSummaryRequest", body);
		
		
		OWSSoapConnection con = new OWSSoapConnection();
		Map<String,Object> result = con.doSoapConnection(bodyMap, "/Reservation.wsdl#FutureBookingSummary");
		
		return result;
	}
}
