package com.api.ows.reservadvanced.service;

import java.util.Map;

import com.api.ows.reservadvanced.vo.request.AssignRoomAdvReqVO;
import com.api.ows.reservadvanced.vo.request.CheckInReqVO;

/**
 * @Class AssignRoomAdvService
 * @Description : 캡슐화를 위한 AssignRoomAdvService Interface  
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
public interface AssignRoomAdvService {
	/**
	* @Description :  객실할당 요청
	* @param  AssignRoomAdvReqVO
	* @return Map<String,Object>
	* @author 서민재
	*/
	Map<String, Object> doAssignRoom(AssignRoomAdvReqVO param) throws Exception;
}
