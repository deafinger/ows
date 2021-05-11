package com.api.ows.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(path = "/reservation")
public class FutureBookingSummaryController {
	
	@Autowired
	FutureBookingSummaryService service;
	
	
	/**
	* @Description : 
	* @param  
	* @return ResponseEntity<?>
	* @author 서민재
	*/
	@PostMapping(path = "/future")
	public ResponseEntity<?> futureBookingSummaryRequest(@RequestBody @Validated FutureBookingSummaryReqVO param) throws Exception{
		log.info("PATH :/reservation/future ");
		return ResponseEntity.ok().body(service.doFutureBookingSummaryRequest(param));
	}
	/**
	* @Description : 
	* @param  
	* @return ResponseEntity<?>
	* @author 서민재
	*/
	@PostMapping(path = "/futureByDate")
	public ResponseEntity<?> futureBookingSummaryRequestByDate(@RequestBody FutureBookingSummaryReqVO param) throws Exception{
		log.info("PATH :/reservation/futureByDate ");
		return ResponseEntity.ok().body(service.doFutureBookingSummaryRequestByDate(param));
	}
	
}
