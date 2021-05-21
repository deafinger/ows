package com.api.ows.reservation.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.ows.common.exception.DataNotFoundException;
import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservation.model.addaccompanyguest.AddAccompanyGuestBody;
import com.api.ows.reservation.service.AddAccompanyGuestService;
import com.api.ows.reservation.vo.request.AddAccompanyGuestReqVO;
import com.api.ows.reservation.vo.response.AddAccompanyGuestResVO;
import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddAccompanyGuestServiceImpl implements  AddAccompanyGuestService{
	@Override
	public Map<String, Object> doAddAccompanyGuest(AddAccompanyGuestReqVO param) throws Exception {
		//BodyModel 만들기
		final AddAccompanyGuestBody setting = new AddAccompanyGuestBody(param);
		final Map<String,Object> soapResultMap = new OWSSoapConnection().doSoapConnection(setting.getBody(), "/Reservation.wsdl#AddAccompanyGuest","Reservation.asmx");
		final Map<String,Object> status = U.get(soapResultMap, "AddAccompanyGuestResponse.Result");
		
		log.info("status : {}",status );
		//Vo 담기
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new DataNotFoundException(status.get("c:OperaErrorCode").toString());
		Map<String,Object> addAccompanyGuest = U.get(soapResultMap, "AddAccompanyGuestResponse");
		
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("Result",setVO(addAccompanyGuest) );
		
		return result;
	}
	
	
	private  AddAccompanyGuestResVO setVO(Object info) {
		
		AddAccompanyGuestResVO vo  = new AddAccompanyGuestResVO();
		Map<String,Object> infoMap = (Map<String,Object>)info;
		
		// 결과
		vo.setConfirmationNumber(CommonUtill.pathMapGetString(infoMap, "ConfirmationNumber.#text"));
		vo.setLegNumber(CommonUtill.pathMapGetString(infoMap, "LegNumber.#text"));
		vo.setResultStatusFlag(CommonUtill.pathMapGetString(infoMap, "Result.-resultStatusFlag"));
		return vo;
	}
}
