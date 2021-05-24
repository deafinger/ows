package com.api.ows.reservation.service;

import java.util.Map;

import com.api.ows.reservation.vo.request.AddAccompanyGuestReqVO;

/**
 * @Class AddAccompanyGuestService
 * @Description : 캡슐화를 위한 AddAccompanyGuestService Interface  
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 21.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 21.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
public interface AddAccompanyGuestService {
	/**
	* @Description : 동반게스트 추가(프로필 등록) 요청
	* @param  AddAccompanyGuestReqVO
	* @return Map<String,Object>
	* @author 서민재
	*/
	Map<String, Object>  doAddAccompanyGuest(AddAccompanyGuestReqVO param) throws Exception;
}
