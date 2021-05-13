package com.api.ows.reservation.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class FutureBookingSummaryResVO
 * @Description : FutureBookingSummary Return용 VO 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 11.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 11.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FutureBookingSummaryResVO {
	private	String	confirmationId	;	//	# confirmationId : 고객, 스태프가 확인 가능한 예약 번호
	private	String	reservNameId	;	//	# reservNameId : 시스템 에약 고유 번호
	private	String	roomNumber	;	//	# roomNumber : 예약에 할당된 방 번호
	private	String	hotelReference 	;	//	# hotelReference  : 호텔명
	private	String	firstName	;	//	# firstName : 이름
	private	String	lastName	;	//	# lastName : 성
	private	String	ratePlanCode	;	//	# ratePlanCode : 객실 이용 플랜 코드
	private	String	ratePlanDescription	;	//	# ratePlanDescription : 객식 이용 플랜 설명
	private	String	roomTypeCode	;	//	# roomTypeCode : 룸 타입 코드
	private	String	roomTypeDescription	;	//	# roomTypeDescription : 룸 타입 설명
	private	String	reservationStatus	;	//	# reservationStatus : 예약 상태
	private	String	startDate 	;	//	# startDate  : 예약 시작 시각 (yyyy-MM-dd'T'HH:mm:ss:SS)
	private	String	endDate	;	//	# endDate : 예약 종료 시각	(yyyy-MM-dd'T'HH:mm:ss:SS)
	private	String	adult	;	//	# adult : 어른 수
	private	String	child	;	//	# child : 아이 수
	private	String	nameId	;	//	# nameId : 예약자고객 고유 번호
	private	String	phone	;	//	# phone : 휴대폰 번호
	private	String	email	;	//	# email : 이메일 번호
	private	String	rph	;	//	# rph : Guest Rph Number
	private	String	legNumber	;	//	# legNumber : OPMS Reservation Sequence
	private	String	roomClass	;	//	# roomClass	: Room Class
}
