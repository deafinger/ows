package com.api.ows.reservadvanced.vo.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Class PostChargeReqVO
 * @Description : 결제항목 추가 Request Parameter VO 
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
public class PostChargeReqVO {
	@NotNull(message = "입력이필요합니다.")
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyyMMddHHmmss")
	@DateTimeFormat(pattern = "yyyyMMddHHmmss")
	private	Date	postDateTime	;	//	# postDateTime : 추가된 일시(yyyyMMddHHmmss)
	
	private	String	shortInfo		;	//	# shortInfo : 짧은 설명
	@NotNull(message = "입력이필요합니다.")
	private	String	longInfo		;	//	# longInfo : 설명(결제내역 조회시 노출)
	@NotNull(message = "입력이필요합니다.")
	private	String	article			;	//	# article : 품번
	@NotNull(message = "입력이필요합니다.")
	private	String	account			;	//	# account : 계좌번호( 구현만 해놓음 ""  값으로 보낼것!)
	@NotNull(message = "입력이필요합니다.")
	private	String	charge			;	//	# charge : 가격
	@NotNull(message = "입력이필요합니다.")
	private	String	hotelcode    	;	//	# hotelcode     : 호텔코드
	@NotNull(message = "입력이필요합니다.")
	private	String	reservNameId 	;	//	# reservNameId  : 시스템 예약ID
}
