package com.api.ows.profile.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ows.common.utill.ValidationErrorsMessage;
import com.api.ows.profile.service.FetchProfileService;
import com.api.ows.profile.vo.request.FetchProfileReqVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(path = "/names")
public class FetchProfileController {

	@Autowired
	FetchProfileService service;
	
	@Autowired
	ValidationErrorsMessage mes;
	
	@PostMapping(path = "/fetchprofile")
	public ResponseEntity<?> fetchProfileRequest(@Valid @RequestBody FetchProfileReqVO param, Errors errors) throws Exception {
		log.debug("PATH :/fetchProfile");
		log.debug("param ", param);

		if(errors.hasErrors()) { 
			log.debug("error ", errors);
			return ResponseEntity.badRequest().body(mes.getMessage(errors));
		}

		return ResponseEntity.ok().body(service.doFetchProfileRequest(param));
	}
}
