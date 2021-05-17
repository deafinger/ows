package com.api.ows.reservation.vo.response;

import lombok.Data;

/**
 * @Class FetchBookingResVO
 * @Description : 예약상세 조회 ReturnVO 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 14.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 14.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Data
public class FetchBookingResVO {
	private	String	confirmationId						;	//	# confirmationId : 고객이 확인 가능한 예약 번호
	private	String	reservNameId						;	//	# reservNameId : 시스템 예약 고유 번호
	private	String	depositAmount						;	//	# depositAmount : 보증금 금액
	private	String	depositDueAmount					;	//	# depositDueAmount : 보증금 금액 due?
	private	String	depositAmountCurrencyCode			;	//	# depositAmountCurrencyCode : 보증금통화코드
	private	String	roomRatecurrencyCode				;	//	# roomRatecurrencyCode : 객실 요금 단위 코드
	private	String	roomRatebase						;	//	# roomRatebase : 객실 요금 금액
	private	String	roomTypeCode						;	//	# roomTypeCode : 객실 타입 코드
	private	String	roomTypeName						;	//	# roomTypeName : 객실 타입 이름
	private	String	roomTypeDescription					;	//	# roomTypeDescription : 객실 타입 설명
	private	String	roomTypeShortDescription			;	//	# roomTypeShortDescription : 객실 타입 짧은 설명
	private	String	roomNumber							;	//	# roomNumber : 예약에 할당된 방 번호
	private	String	adult								;	//	# adult : 어른 수
	private	String	child								;	//	# child : 아이 수
	private	String	startDate							;	//	# startDate : 예약 시작 날짜
	private	String	endDate								;	//	# endDate : 예약 종료 날짜
	private	String	cardType							;	//	# cardType : 카드 타입
	private	String	cardHolderName						;	//	# cardHolderName : 카드 소유자 이름
	private	String	cardNumber							;	//	# cardNumber : 카드 번호
	private	String	expirationDate						;	//	# expirationDate : 카드 만료 번호
	private	String	chainCode							;	//	# chainCode : 호텔 체인 코드
	private	String	hotelCode							;	//	# hotelCode : 호텔 코드
	private	String	hotelReference 						;	//	# hotelReference  : 호텔명
	private	String	totalCurrencyCode					;	//	# totalCurrencyCode : 총 이용 금액 통화 단위
	private	String	totalValue							;	//	# totalValue : 총 이용 금액
	private	String	expectedtotalRoomRateAndPackages	;	//	# expectedtotalRoomRateAndPackages : 객실 이용 금액 + 패키지 비용
	private	String	expectedTotalTaxesAndFees			;	//	# expectedTotalTaxesAndFees : 세금 포함 금액
	private	String	eTaxInclusive						;	//	# eTaxInclusive : 세금 포함 여부
	private	String	postingDate							;	//	# postingDate : 후 결제 날짜
	private	String	nameId								;	//	# nameId : 고객 고유 번호
	private	String	nameTitle							;	//	# nameTitle : 존칭
	private	String	firstName							;	//	# firstName : 이름
	private	String	lastName							;	//	# lastName : 성
	private	String	addressType							;	//	# addressType : 주소 타입
	private	String	addressLine							;	//	# addressLine : 주소
	private	String	cityName							;	//	# cityName : 도 이름
	private	String	stateProv							;	//	# stateProv : 시 이름
	private	String	countryCode							;	//	# countryCode : 국가 코드
	private	String	postalCode							;	//	# postalCode : 우편 번호
}
