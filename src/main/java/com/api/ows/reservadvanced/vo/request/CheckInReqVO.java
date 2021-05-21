package com.api.ows.reservadvanced.vo.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

/**
 * @Class CheckInReqVO
 * @Description : 체크인 Request Parameter VO
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
public class CheckInReqVO {
	@NotNull(message = "입력해야합니다.")
	private	String	hotelCode			;	//	# hotelCode : 호텔 코드
	@NotNull(message = "입력해야합니다.")
	private	String	reservNameId		;	//	# reservNameId : 예약 아이디
	@NotNull(message = "입력해야합니다.")
	private	String	cardHolderName		;	//	# cardHolderName : 카드 소유자 번호
	@NotNull(message = "입력해야합니다.")
	private	String	cardType			;	//	# cardType : 카드 타입
	@NotNull(message = "입력해야합니다.")
	private	String	cardNumber			;	//	# cardNumber : 카드 번호
	@NotNull(message = "입력해야합니다.")
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyyMMdd")
	@DateTimeFormat(pattern = "yyyyMMdd")
	private	Date	expirationDate		;	//	# expirationDate : 카드 만료일
}
