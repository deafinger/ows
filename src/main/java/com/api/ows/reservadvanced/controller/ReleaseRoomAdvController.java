package com.api.ows.reservadvanced.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ows.common.exception.BadParameterException;
import com.api.ows.common.utill.ValidationErrorsMessage;
import com.api.ows.reservadvanced.service.ReleaseRoomAdvService;
import com.api.ows.reservadvanced.vo.request.ReleaseRoomAdvReqVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class ReleaseRoomAdvController
 * @Description : 객실 비할당 요청 Contorller
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
@Slf4j
@RestController
@RequestMapping(path = "/reserAdvanced/releaseRoom")
public class ReleaseRoomAdvController {
	
	@Autowired
	ValidationErrorsMessage mes;
	
	@Autowired
	ReleaseRoomAdvService service;
	
	@PostMapping(path = "/request")
	public ResponseEntity<?> releaseRoomAdvRequest(@Valid @RequestBody ReleaseRoomAdvReqVO param,Errors errors) throws Exception{
		log.info("PATH :/reserAdvanced/releaseRoom/request");
		if(errors.hasErrors()) throw new BadParameterException(mes.getExceptionMessage(errors)); 
		return ResponseEntity.ok().body(service.doReleaseRoom(param));
	}

}
