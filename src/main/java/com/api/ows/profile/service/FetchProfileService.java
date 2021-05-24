package com.api.ows.profile.service;

import java.util.Map;

import com.api.ows.profile.vo.request.FetchProfileReqVO;



public interface FetchProfileService {
	Map<String,Object>  doFetchProfileRequest(FetchProfileReqVO param) throws Exception;
}
