package com.api.ows.reservadvanced.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.ows.common.exception.DataNotFoundException;
import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservadvanced.model.checkin.CheckInBody;
import com.api.ows.reservadvanced.model.makepayment.MakePaymentBody;
import com.api.ows.reservadvanced.service.CheckInService;
import com.api.ows.reservadvanced.vo.request.CheckInReqVO;
import com.api.ows.reservadvanced.vo.response.CheckInResVO;
import com.api.ows.reservadvanced.vo.response.MakePaymentResVO;
import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CheckInServiceImpl implements CheckInService{
	
	@Override
	public Map<String, Object> doCheckIn(CheckInReqVO param) throws Exception {
		//BodyModel 만들기
		final CheckInBody setting = new CheckInBody(param);
		final Map<String,Object> soapResultMap = new OWSSoapConnection().doSoapConnection(setting.getBody(), "/ResvAdvanced.wsdl#CheckIn","ResvAdvanced.asmx");
		final Map<String,Object> status = U.get(soapResultMap, "CheckInResponse.Result");
		
		log.info("status : {}",status );
		//Vo 담기
//		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new DataNotFoundException(status.get("c:OperaErrorCode").toString());
//		Map<String,Object> checkIn = U.get(soapResultMap, "CheckInResponse");
		
//		Map<String, Object> result = new HashMap<String,Object>();
//		result.put("Result",setVO(checkIn) );
		
		return soapResultMap;
	}
	
	
	private  CheckInResVO setVO(Object info) {
		
		CheckInResVO vo  = new CheckInResVO();
		Map<String,Object> infoMap = (Map<String,Object>)info;
		
		// 결과
		//CommonUtill.pathMapGetString(infoMap, "Result.-resultStatusFlag")
		vo.setReservNameId(null);
		vo.setRoomNumber(null);
		vo.setRoomType(null);
		return vo;
	}
}
