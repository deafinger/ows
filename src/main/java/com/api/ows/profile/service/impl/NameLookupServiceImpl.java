package com.api.ows.profile.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.profile.model.nameLookup.*;
import com.api.ows.profile.service.NameLookupService;
import com.api.ows.profile.vo.request.NameLookupReqVO;
import com.api.ows.profile.vo.response.NameLookupResVO;
import com.api.ows.reservadvanced.model.invoice.InvoiceBody;
import com.api.ows.reservadvanced.vo.response.InvoiceResVO;
import com.api.ows.reservation.vo.response.FutureBookingSummaryResVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NameLookupServiceImpl implements NameLookupService {

	@Override
	public Map<String, Object> doNameLookupRequest(NameLookupReqVO param) throws Exception {
		final NameLookupBody buildedFetchProfileBody = new NameLookupBody(param);

		log.info("bodyMap : {}", buildedFetchProfileBody);

		OWSSoapConnection con = new OWSSoapConnection();
		Map<String, Object> soapResultMap = con.doSoapConnection(buildedFetchProfileBody.getBody(), CommonString.NAME_LOOKUP_ACTION, CommonString.NAME_ASMX);
		log.info("soapResultMap : {}", soapResultMap);

		Map<String, Object> status = U.get(soapResultMap, "NameLookupResponse.Result");
		log.info("status : {}", status);

		List<NameLookupResVO> voList = new ArrayList<>();
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new AttributeNotFoundException(status.get("c:OperaErrorCode").toString());

		Object profiles = U.get(soapResultMap, "NameLookupResponse.Profiles.Profile");
		if (profiles instanceof List) {
			List<Object> profileList = (List) profiles;
			for(Object profile : profileList) 
				voList.add(_setVO(profile));

		} else {
			voList.add(_setVO(profiles));
		}

		Map<String, Object> result = new HashMap<String,Object>();
		result.put("profileList", voList );
		result.put("profileListSize", Integer.valueOf(voList.size()));
		return result;
	}
	
	private NameLookupResVO _setVO(Object profile) throws Exception {

		NameLookupResVO nameLookupResVO = new NameLookupResVO();
		Map<String,Object> profileMap = (Map<String,Object>) profile;
		nameLookupResVO.setLastName(CommonUtill.pathMapGetString(profileMap, "Customer.PersonName.lastName.#text"));
		nameLookupResVO.setFirstName(CommonUtill.pathMapGetString(profileMap, "Customer.PersonName.firstName.#text"));
		nameLookupResVO.setNameTitle(CommonUtill.pathMapGetString(profileMap, "Customer.PersonName.nameTitle.#text"));
		nameLookupResVO.setNameId(CommonUtill.pathMapGetString(profileMap, "ProfileIDs.UniqueID.#text"));
		nameLookupResVO.setEmail(CommonUtill.pathMapGetString(profileMap, "EMails.NameEmail"));
		log.info("nameLookupResVO {} ", nameLookupResVO);
		
		return nameLookupResVO;
	}
}
