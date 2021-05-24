package com.api.ows.reservation.vo.response;

import lombok.Data;

/**
 * @Class AddAccompanyGuestResVO
 * @Description : 동반게스트 추가(프로필 등록) Return
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
public class AddAccompanyGuestResVO {
	private String resultStatusFlag		;   // # resultStatusFlag : 결과
	private String confirmationNumber	;	// # confirmationNumber : 고객이 볼
	private String legNumber			;	// # legNumber : 결과
}
