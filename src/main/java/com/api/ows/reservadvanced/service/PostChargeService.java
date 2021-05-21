package com.api.ows.reservadvanced.service;

import java.util.Map;

import com.api.ows.reservadvanced.vo.request.PostChargeReqVO;

/**
 * @Class PostChargeService
 * @Description : 캡슐화를 위한 PostChargeService InterFace 
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
public interface PostChargeService {
	/**
	* @Description :  결제항목 추가 요청
	* @param  PostChargeReqVO
	* @return Map<String,Object>
	* @author 서민재
	*/
	Map<String, Object> doPostCharge(PostChargeReqVO param) throws Exception;
}
