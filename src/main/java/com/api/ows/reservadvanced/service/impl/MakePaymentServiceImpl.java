package com.api.ows.reservadvanced.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ows.common.exception.DataNotFoundException;
import com.api.ows.common.exception.ErrorCodes;
import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservadvanced.model.invoice.InvoiceBody;
import com.api.ows.reservadvanced.model.makepayment.MakePaymentBody;
import com.api.ows.reservadvanced.service.MakePaymentService;
import com.api.ows.reservadvanced.vo.request.MakePaymentReqVO;
import com.api.ows.reservadvanced.vo.response.InvoiceResVO;
import com.api.ows.reservadvanced.vo.response.MakePaymentResVO;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.underscore.lodash.U;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class MakePaymentServiceImpl
 * @Description : 결제내역 추가 Soap Request  
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 17.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 17.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Service
@Slf4j
public class MakePaymentServiceImpl implements MakePaymentService {
	
	@Override
	public Map<String, Object> doMakePayment(MakePaymentReqVO param) throws Exception {
		//BodyModel 만들기
		final MakePaymentBody setting = new MakePaymentBody(param);
		final Map<String,Object> soapResultMap = new OWSSoapConnection().doSoapConnection(setting.getBody(), "/ResvAdvanced.wsdl#MakePayment","ResvAdvanced.asmx");
		final Map<String,Object> status = U.get(soapResultMap, "MakePaymentResponse.Result");
		
		log.info("status : {}",status );
		//Vo 담기
		
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new DataNotFoundException(status.get("c:OperaErrorCode").toString());
		
		Map<String,Object> makePayment = U.get(soapResultMap, "MakePaymentResponse");
		
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("Result",setVO(makePayment) );
		
		return result;
	}
	
	private  MakePaymentResVO setVO(Object info) {
		
		MakePaymentResVO vo  = new MakePaymentResVO();
		Map<String,Object> infoMap = (Map<String,Object>)info;
		
		// 결과
		vo.setResultStatusFlag(CommonUtill.pathMapGetString(infoMap, "Result.-resultStatusFlag"));
		vo.setResvNameId(CommonUtill.pathMapGetString(infoMap, "ReservationID.c:UniqueID.#text"));
		return vo;
	}
}
