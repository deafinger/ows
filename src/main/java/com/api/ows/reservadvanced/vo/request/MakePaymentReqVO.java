package com.api.ows.reservadvanced.vo.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Class MakePaymentReqVO
 * @Description : 결제내역 추가 Parameter Class 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 17.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 17.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Data
public class MakePaymentReqVO {
	private	String	chainCode		;	//	# chainCode : 체인 코드
	private	String	hotelCode		;	//	# hotelCode : 호텔 코드
	private	String	resvNameId		;	//	# resvNameId : 예약 고유 번호
	private	String	charge			;	//	# charge : 결제된 금액 (PG사에 결제한 금액)
	private	String	cardType		;	//	# cardType : 카드 타입
	private	String	cardHolderName	;	//	# cardHolderName : 카드 소유자 이름
	private	String	cardNumber		;	//	# cardNumber : 카드 번호
	private	String 	shortInfo		;	//	# shortInfo : 결제에 대한 짧은 설명
	private	String	longInfo		;	//	# longInfo : 결제에 대한 설명
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyyMMdd")
	@DateTimeFormat(pattern = "yyyyMMdd")
	private	Date	expirationDate	;	//	# expirationDate : 만료 시간
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyyMMddHHmm")
	@DateTimeFormat(pattern = "yyyyMMddHHmm")
	private	Date	postDate		;	//	# postDate : 결제시간 (format : yyyyMMddHHmm)
}
