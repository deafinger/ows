package com.api.ows.reservation.vo.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @Class AddAccompanyGuestReqVO
 * @Description : 동반게스트 추가 (Profile등록)
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
public class AddAccompanyGuestReqVO {
	@NotNull(message = "입력이 필요합니다.")
	@NotBlank(message = "빈값이 올수 없습니다.")
	private	String	chainCode         	;	//	# chainCode          : 호텔Chain코드
	@NotBlank(message = "빈값이 올수 없습니다.")
	@NotNull(message = "입력이 필요합니다.")
	private	String	hotelCode         	;	//	# hotelCode          : 호텔코드
	@NotBlank(message = "빈값이 올수 없습니다.")
	@NotNull(message = "입력이 필요합니다.")
	private	String	confirmationNumber	;	//	# confirmationNumber : 고객이보는 예약ID
	@NotBlank(message = "빈값이 올수 없습니다.")
	@NotNull(message = "입력이 필요합니다.")
	private	String	legNumber         	;	//	# legNumber          : 동반 투숙객 Sequence Number(1~3)
	@NotBlank(message = "빈값이 올수 없습니다.")
	@NotNull(message = "입력이 필요합니다.")
	private String 	nameId				; 	//	# nameId          	: 고객의 Profile ID
}
