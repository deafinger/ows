package com.api.ows.reservadvanced.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ows.common.utill.ValidationErrorsMessage;
import com.api.ows.reservadvanced.service.MakePaymentService;
import com.api.ows.reservadvanced.vo.request.MakePaymentReqVO;

import lombok.extern.slf4j.Slf4j;
/**
 * @Class MakePaymentController
 * @Description : 결제내역 추가 Controller
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
@Slf4j
@RestController
@RequestMapping(path = "/reserAdvanced/make")
public class MakePaymentController {
	
	@Autowired
	ValidationErrorsMessage mes;
	
	@Autowired
	MakePaymentService service;
	
	@PostMapping(path = "/payment")
	public ResponseEntity<?> futureBookingSummaryRequest(@Valid @RequestBody MakePaymentReqVO param,Errors errors) throws Exception{
		log.info("PATH :/reserAdvanced/make/payment");
		if(errors.hasErrors())return ResponseEntity.badRequest().body(mes.getMessage(errors)); 
		return ResponseEntity.ok().body(service.doMakePayment(param));
	}

}
