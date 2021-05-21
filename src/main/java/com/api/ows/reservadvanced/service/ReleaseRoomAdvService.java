package com.api.ows.reservadvanced.service;

import java.util.Map;

import com.api.ows.reservadvanced.vo.request.ReleaseRoomAdvReqVO;

/**
 * @Class ReleaseRoomAdvService
 * @Description : 캡슐화를 위한 ReleaseRoomAdvService InterFace
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
public interface ReleaseRoomAdvService {
	/**
	* @Description : 객실 비할당 
	* @param  ReleaseRoomAdvReqVO
	* @return Map<String,Object>
	* @author 서민재
	*/
	Map<String, Object> doReleaseRoom(ReleaseRoomAdvReqVO param) throws Exception;
}
