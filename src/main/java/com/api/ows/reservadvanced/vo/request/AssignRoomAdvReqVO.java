package com.api.ows.reservadvanced.vo.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Class AssignRoomAdvReqVO
 * @Description : 객실할당 요청 Request Parameter  
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
public class AssignRoomAdvReqVO {
	@NotNull(message = "입력해야합니다.")
	private	String	hotelCode			;	//	# hotelCode : 호텔 코드
	@NotNull(message = "입력해야합니다.")
	private	String	reservNameId		;	//	# reservNameId : 예약 아이디
	@NotNull(message = "입력해야합니다.")
	private	String	roomNumber			;	//	# roomNumber : 객실번호
}
