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
import com.api.ows.profile.service.NameLookupService;
import com.api.ows.profile.vo.request.NameLookupReqVO;


import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(path = "/names")
public class NameLookupController {

	@Autowired
	NameLookupService service;
	
	@Autowired
	ValidationErrorsMessage mes;
	
	@PostMapping(path = "/namelookup")
	public ResponseEntity<?> nameLookUpRequestByDate(@Valid @RequestBody NameLookupReqVO param, Errors errors) throws Exception {
		log.debug("PATH :/namelookup");
		log.debug("param ", param);

		if(errors.hasErrors()) { // hack : 이걸 하나의 함수로 만들자... 
			log.debug("error ", errors);
			return ResponseEntity.badRequest().body(mes.getMessage(errors));
		}

		return ResponseEntity.ok().body(service.doNameLookupRequest(param));
	}
}
