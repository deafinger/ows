package com.api.ows.reservadvanced.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.ows.common.exception.DataNotFoundException;
import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservadvanced.model.releaseroom.ReleaseRoomAdvBody;
import com.api.ows.reservadvanced.service.ReleaseRoomAdvService;
import com.api.ows.reservadvanced.vo.request.ReleaseRoomAdvReqVO;
import com.api.ows.reservadvanced.vo.response.ReleaseRoomAdvResVO;
import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class ReleaseRoomAdvServiceImpl
 * @Description : 객실 비할당 요청
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 20.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 20.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Service
@Slf4j
public class ReleaseRoomAdvServiceImpl implements ReleaseRoomAdvService{
	@Override
	public Map<String, Object> doReleaseRoom(ReleaseRoomAdvReqVO param) throws Exception {
		//BodyModel 만들기
		final ReleaseRoomAdvBody setting = new ReleaseRoomAdvBody(param);
		final Map<String,Object> soapResultMap = new OWSSoapConnection().doSoapConnection(setting.getBody(), "/ResvAdvanced.wsdl#ReleaseRoom","ResvAdvanced.asmx");
		final Map<String,Object> status = U.get(soapResultMap, "ReleaseRoomAdvResponse.Result");
		
		log.info("status : {}",status );
		//Vo 담기
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new DataNotFoundException(status.get("c:OperaErrorCode").toString());
		Map<String,Object> releaseRoom = U.get(soapResultMap, "ReleaseRoomAdvResponse");
		
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("releaseRoom",setVO(releaseRoom) );
		
		return result;
	}
	
	
	private  ReleaseRoomAdvResVO  setVO(Object info) {
		
		ReleaseRoomAdvResVO vo  = new ReleaseRoomAdvResVO();
		Map<String,Object> infoMap = (Map<String,Object>)info;
		
		// 결과
		vo.setResultStatusFlag(CommonUtill.pathMapGetString(infoMap, "Result.-resultStatusFlag"));
		return vo;
	}
}
