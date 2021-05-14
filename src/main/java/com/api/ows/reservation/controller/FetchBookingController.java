package com.api.ows.reservation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ows.common.utill.ValidationErrorsMessage;
import com.api.ows.reservation.service.FetchBookingService;
import com.api.ows.reservation.vo.request.FetchBookingReqVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class FetchBookingController
 * @Description : 예약 상세 조회 Controller 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 14.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 14.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Slf4j
@RestController
@RequestMapping(path = "/reservation/fetch")
public class FetchBookingController {
	
	
	@Autowired
	FetchBookingService service;
	
	@Autowired
	ValidationErrorsMessage mes;
	
	/**
	* @Description : 예약상세 조회 Controller
	* @param  FetchBookingReqVO
	* @return ResponseEntity<?>
	* @author 서민재
	*/
	@PostMapping(path = "/booking")
	public ResponseEntity<?> fetchBookingRequest(@Valid @RequestBody FetchBookingReqVO param,Errors errors) throws Exception{
		log.info("PATH :/reservation/fetch/booking");
		if(errors.hasErrors())return ResponseEntity.badRequest().body(mes.getMessage(errors)); 
		return ResponseEntity.ok().body(service.doFetchBookingRequest(param));
	}
}
