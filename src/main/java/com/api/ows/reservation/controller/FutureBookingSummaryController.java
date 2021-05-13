package com.api.ows.reservation.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ows.common.utill.ValidationErrorsMessage;
import com.api.ows.reservation.service.FutureBookingSummaryService;
import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;

import lombok.extern.slf4j.Slf4j;


/**
 * @Class FutureBookingSummaryController.java
 * @Description : 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 7.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 7.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Slf4j
@RestController
@RequestMapping(path = "/reservation/future")
public class FutureBookingSummaryController {
	
	@Autowired
	FutureBookingSummaryService service;
	
	@Autowired
	ValidationErrorsMessage mes;
	
	/**
	* @Description : 
	* @param  
	* @return ResponseEntity<?>
	* @author 서민재
	*/
	@PostMapping(path = "/booking")
	public ResponseEntity<?> futureBookingSummaryRequest(@Valid @RequestBody FutureBookingSummaryReqVO param,Errors errors) throws Exception{
		log.info("PATH :/reservation/future/booking");
		if(errors.hasErrors())return ResponseEntity.badRequest().body(mes.getMessage(errors)); 
		else if(param.isNull()) {
			return ResponseEntity.badRequest().body("Empty Parameter");
		}
		return ResponseEntity.ok().body(service.doFutureBookingSummaryRequest(param));
	}
	
}
