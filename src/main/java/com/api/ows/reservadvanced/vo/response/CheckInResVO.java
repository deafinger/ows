package com.api.ows.reservadvanced.vo.response;

import lombok.Data;

/**
 * @Class CheckInResVO
 * @Description : 체크인 Return VO
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
@Data
public class CheckInResVO {
	private	String	roomNumber		;	//	# roomNumber : 객실 번호
	private	String	roomType		;	//	# roomType : 객실 타입
	private	String	reservNameId	;	//	# reservNameId : 예약 번호

}
