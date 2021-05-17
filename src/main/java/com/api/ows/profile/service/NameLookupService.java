package com.api.ows.profile.service;

import java.util.Map;

import com.api.ows.profile.vo.request.NameLookupReqVO;



public interface NameLookupService {
	Map<String,Object>  doNameLookupRequest(NameLookupReqVO param) throws Exception;
}
