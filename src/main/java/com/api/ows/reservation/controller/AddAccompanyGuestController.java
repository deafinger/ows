package com.api.ows.reservation.controller;

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
import com.api.ows.reservation.service.AddAccompanyGuestService;
import com.api.ows.reservation.vo.request.AddAccompanyGuestReqVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class AddAccompanyGuestController
 * @Description : 동반게스트 추가 (프로필 등록)
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
@Slf4j
@RestController
@RequestMapping(path = "/reservation/addAccompany")
public class AddAccompanyGuestController{
	@Autowired
	AddAccompanyGuestService service;
	
	@Autowired
	ValidationErrorsMessage mes;
	
	
	/**
	* @Description : 동반게스트 추가 요청
	* @param  AddAccompanyGuestReqVO
	* @return ResponseEntity<?>
	* @author 서민재
	*/
	@PostMapping(path = "/guest")
	public ResponseEntity<?> addAccompanyGuest(@Valid @RequestBody AddAccompanyGuestReqVO param,Errors errors) throws Exception{
		log.info("PATH :/reservation/addAccompany/guest");
		if(errors.hasErrors()) throw new BadParameterException(mes.getExceptionMessage(errors)); 
		return ResponseEntity.ok().body(service.doAddAccompanyGuest(param));
	}
}
