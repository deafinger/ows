package com.api.ows.reservadvanced.service;

import java.util.Map;

import com.api.ows.reservadvanced.vo.request.CheckInReqVO;


/**
 * @Class CheckInService
 * @Description :  캡술화를 위한 CheckInService Interface
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 20.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 20.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
public interface CheckInService {
	
	/**
	* @Description : 체크인 요청
	* @param CheckInReqVO  
	* @return Map<String,Object>
	* @author 서민재
	*/
	Map<String, Object> doCheckIn(CheckInReqVO param) throws Exception;
}
