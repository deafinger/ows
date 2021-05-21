package com.api.ows.profile.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.stereotype.Service;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.profile.model.fetchProfile.FetchProfileBody;
import com.api.ows.profile.model.fetchProfile.PhoneNumbers;
import com.api.ows.profile.model.fetchProfile.Emails;
import com.api.ows.profile.service.FetchProfileService;
import com.api.ows.profile.vo.request.FetchProfileReqVO;
import com.api.ows.profile.vo.response.FetchProfileResVO;
import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FetchProfileServiceImpl implements FetchProfileService {

	@Override
	public Map<String, Object> doFetchProfileRequest(FetchProfileReqVO param) throws Exception {
		final FetchProfileBody buildedFetchProfile = new FetchProfileBody(param);
		log.info("buildedFetchProfile : {}", buildedFetchProfile);

		OWSSoapConnection con = new OWSSoapConnection();
		Map<String, Object> soapResultMap = con.doSoapConnection(buildedFetchProfile.getBody(), CommonString.FETCH_PROFILE_ACTION, CommonString.NAME_ASMX);
		Map<String, Object> status = U.get(soapResultMap, "FetchProfileResponse.Result");
		
		log.info("status : {}",status );
		List<FetchProfileResVO> voList = new ArrayList<>();
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new AttributeNotFoundException(status.get("c:OperaErrorCode").toString());

		Object profiles = U.get(soapResultMap, "FetchProfileResponse.ProfileDetails");
		log.info("profile {}", profiles instanceof List);

		if (profiles instanceof List) {
			List<Object> profileList = (List) profiles;
			for(Object profile : profileList) 
				voList.add(_setVO(profile));

		} else {
			voList.add(_setVO(profiles));
		}

		Map<String, Object> result = new HashMap<String,Object>();
		result.put("ResultList", voList);
		result.put("TotalCount", Integer.valueOf(voList.size()));
		return result;
	}
	
	private FetchProfileResVO _setVO(Object profile) throws Exception {

		FetchProfileResVO fetchProfileResVO = new FetchProfileResVO();
		Map<String,Object> profileMap = (Map<String,Object>) profile;
		fetchProfileResVO.setNameID(CommonUtill.pathMapGetString(profileMap, "ProfileIDs.UniqueID.#text"));
		fetchProfileResVO.setFirstName(CommonUtill.pathMapGetString(profileMap, "Customer.PersonName.firstName.#text"));
		fetchProfileResVO.setLastName(CommonUtill.pathMapGetString(profileMap, "Customer.PersonName.lastName.#text"));
		fetchProfileResVO.setNameTitle(CommonUtill.pathMapGetString(profileMap, "Customer.PersonName.nameTitle.#text"));
		fetchProfileResVO.setAddressLine(CommonUtill.pathMapGetString(profileMap, "Addresses.NameAddress.AddressLine.#text"));
		fetchProfileResVO.setCityName(CommonUtill.pathMapGetString(profileMap, "Addresses.NameAddress.cityName.#text"));
		fetchProfileResVO.setCountryCode(CommonUtill.pathMapGetString(profileMap, "Addresses.NameAddress.countryCode.#text"));
		fetchProfileResVO.setPostalCode(CommonUtill.pathMapGetString(profileMap, "Addresses.NameAddress.postalCode.#text"));

		List phoneList = (List) U.get(profileMap, "Phones.NamePhone");
		List<PhoneNumbers> phonese = new ArrayList<PhoneNumbers>();
		for (Object phone : phoneList) 
			phonese.add(new PhoneNumbers(
					CommonUtill.pathMapGetString((Map) phone, "-phoneType"),
					CommonUtill.pathMapGetString((Map) phone, "-phoneRole"),
					CommonUtill.pathMapGetString((Map) phone, "PhoneNumber.#text")
			));
		fetchProfileResVO.setPhoneNumbers(phonese);
		
		
		List emailList = (List) U.get(profileMap, "EMails.NameEmail");
		List<Emails> emails = new ArrayList<Emails>();
		for (Object email : emailList) {
			emails.add(new Emails(CommonUtill.pathMapGetString((Map) email, "#text")));
		}
		fetchProfileResVO.setEmailAddress(emails);
		
		
		
		log.info("fetchProfileResVO {} ", fetchProfileResVO);
		
		return fetchProfileResVO;
	}
}
