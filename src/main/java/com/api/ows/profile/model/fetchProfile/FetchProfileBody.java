package com.api.ows.profile.model.fetchProfile;

import java.util.HashMap;
import java.util.Map;

import com.api.ows.common.soap.CommonString;
import com.api.ows.profile.vo.request.FetchProfileReqVO;
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
public class FetchProfileBody {
	private Map<String, Object> body;
	
	@Builder
	public FetchProfileBody(FetchProfileReqVO param) {


		Map<String,Object> map = new HashMap<String,Object>();
		
		U.set(map, "FetchProfileRequest", new HashMap<String,Object>());
		U.set(map, "FetchProfileRequest.xmlns", CommonString.NAMEWSDL);
		
		U.set(map, "FetchProfileRequest.NameID", new HashMap<String,Object>());
		U.set(map, "FetchProfileRequest.NameID.type", CommonString.TYPEIN);
		U.set(map, "FetchProfileRequest.NameID.nodeValue", param.getNameID());
		
		this.body = map;
		
	}
	
}