package com.api.ows.reservadvanced.vo.response;

import lombok.Data;

/**
 * @Class AssignRoomAdvResVO
 * @Description : 객실할당 요청 Return Class 
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
public class AssignRoomAdvResVO {
	private String resultStatusFlag	; // # resultStatusFlag : 요청 결과
	private String roomNoAssigned	; // # roomNoAssigned : 할당된 객실번호 
}
