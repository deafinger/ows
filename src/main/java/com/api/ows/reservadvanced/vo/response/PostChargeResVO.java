package com.api.ows.reservadvanced.vo.response;

import lombok.Data;

/**
 * @Class PostChargeResVO
 * @Description : 결제항목 추가 Return VO 
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
@Data
public class PostChargeResVO {
	private	String	reservNameId 		;	//	# reservNameId  : 시스템 예약ID
	private	String	resultStatusFlag 	;	//	# resultStatusFlag  : 결과Message
}
