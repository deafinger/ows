package com.api.ows.reservadvanced.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.ows.common.exception.DataNotFoundException;
import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.reservadvanced.model.assignroom.AssignRoomAdvBody;
import com.api.ows.reservadvanced.model.checkin.CheckInBody;
import com.api.ows.reservadvanced.service.AssignRoomAdvService;
import com.api.ows.reservadvanced.vo.request.AssignRoomAdvReqVO;
import com.api.ows.reservadvanced.vo.request.CheckInReqVO;
import com.api.ows.reservadvanced.vo.response.AssignRoomAdvResVO;
import com.api.ows.reservadvanced.vo.response.CheckInResVO;
import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class AssignRoomAdvServiceImpl
 * @Description : 객실요청 service
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
public class AssignRoomAdvServiceImpl implements AssignRoomAdvService{
	@Override
	public Map<String, Object> doAssignRoom(AssignRoomAdvReqVO param) throws Exception {
		//BodyModel 만들기
		final AssignRoomAdvBody setting = new AssignRoomAdvBody(param);
		final Map<String,Object> soapResultMap = new OWSSoapConnection().doSoapConnection(setting.getBody(), "/ResvAdvanced.wsdl#AssignRoom","ResvAdvanced.asmx");
		final Map<String,Object> status = U.get(soapResultMap, "AssignRoomAdvResponse.Result");
		
		log.info("status : {}",status );
		
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new DataNotFoundException(status.get("c:OperaErrorCode").toString());
		Map<String,Object> assign = U.get(soapResultMap, "AssignRoomAdvResponse");
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("Result",setVO(assign) );
		return result; 
	}
	
	
	private  AssignRoomAdvResVO setVO(Object info) {
		
		AssignRoomAdvResVO vo  = new AssignRoomAdvResVO();
		Map<String,Object> infoMap = (Map<String,Object>)info;
		
		// 결과
		vo.setResultStatusFlag(CommonUtill.pathMapGetString(infoMap, "Result.-resultStatusFlag"));
		vo.setRoomNoAssigned(CommonUtill.pathMapGetString(infoMap, "RoomNoAssigned"));
		return vo;
	}
}
