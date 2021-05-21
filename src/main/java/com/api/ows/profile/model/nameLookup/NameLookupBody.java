package com.api.ows.profile.model.nameLookup;

import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.profile.vo.request.NameLookupReqVO;
import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;
import com.github.underscore.lodash.U;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameLookupBody {
	private Map<String, Object> body;
	
	@Builder
	public NameLookupBody(NameLookupReqVO param) {

		Map<String,Object> map = new HashMap<String,Object>();
		
		U.set(map, "NameLookupRequest", new HashMap<String,Object>());
		U.set(map, "NameLookupRequest.xmlns", CommonString.NAMEWSDL);
		U.set(map, "NameLookupRequest.xmlns:c", CommonString.COMMON);
		
		U.set(map, "NameLookupRequest.NameLookupCriteria", new HashMap<String,Object>());
		
		if (param.getEmailAddress() != null) {
			U.set(map, "NameLookupRequest.NameLookupCriteria.EmailAddress", new HashMap<String,Object>());
			U.set(map, "NameLookupRequest.NameLookupCriteria.EmailAddress.xmlns", CommonString.NAME );
			
			U.set(map, "NameLookupRequest.NameLookupCriteria.EmailAddress.EmailAddress", new HashMap<String,Object>());
			U.set(map, "NameLookupRequest.NameLookupCriteria.EmailAddress.EmailAddress.nodeValue", param.getEmailAddress());
		}
		if (param.getLastName() != null) {
			U.set(map, "NameLookupRequest.NameLookupCriteria.NameLookup", new HashMap<String,Object>());
			U.set(map, "NameLookupRequest.NameLookupCriteria.NameLookup.xmlns", CommonString.NAME );

			U.set(map, "NameLookupRequest.NameLookupCriteria.NameLookup.LastName", new HashMap<String, Object>());
			U.set(map, "NameLookupRequest.NameLookupCriteria.NameLookup.LastName.nodeValue", param.getLastName());
		}
		if (param.getFirstName() != null) {
			U.set(map, "NameLookupRequest.NameLookupCriteria.NameLookup", new HashMap<String,Object>());
			U.set(map, "NameLookupRequest.NameLookupCriteria.NameLookup.xmlns", CommonString.NAME );

			U.set(map, "NameLookupRequest.NameLookupCriteria.NameLookup.FirstName", new HashMap<String, Object>());
			U.set(map, "NameLookupRequest.NameLookupCriteria.NameLookup.FirstName.nodeValue", param.getFirstName());
		}
		
		this.body = map;
		
	}

}
