package com.api.ows.reservadvanced.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.ows.common.exception.DataNotFoundException;
import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservadvanced.model.postcharge.PostChargeBody;
import com.api.ows.reservadvanced.service.PostChargeService;
import com.api.ows.reservadvanced.vo.request.PostChargeReqVO;
import com.api.ows.reservadvanced.vo.response.MakePaymentResVO;
import com.api.ows.reservadvanced.vo.response.PostChargeResVO;
import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class PostChargeServiceImpl
 * @Description : 결제항목 추가 요청 Service
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
@Service
@Slf4j
public class PostChargeServiceImpl implements PostChargeService{
	@Override
	public Map<String, Object> doPostCharge(PostChargeReqVO param) throws Exception {
		//BodyModel 만들기
		final PostChargeBody setting = new PostChargeBody(param);
		final Map<String,Object> soapResultMap = new OWSSoapConnection().doSoapConnection(setting.getBody(), "/ResvAdvanced.wsdl#PostCharge","ResvAdvanced.asmx");
		final Map<String,Object> status = U.get(soapResultMap, "PostChargeResponse.Result");
		
		log.info("status : {}",status );
		//Vo 담기
		
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new DataNotFoundException(status.get("c:OperaErrorCode").toString());
		
		Map<String,Object> postCharge = U.get(soapResultMap, "PostChargeResponse");
		
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("postCharge",setVO(postCharge) );
		
		return result;
	}
	
	private  PostChargeResVO setVO(Object info) {
		
		PostChargeResVO vo  = new PostChargeResVO();
		Map<String,Object> infoMap = (Map<String,Object>)info;
		
		// 결과
		vo.setResultStatusFlag(CommonUtill.pathMapGetString(infoMap, "Result.-resultStatusFlag"));
		vo.setReservNameId(CommonUtill.pathMapGetString(infoMap, "ReservationID.c:UniqueID.#text"));
		return vo;
	}
}
