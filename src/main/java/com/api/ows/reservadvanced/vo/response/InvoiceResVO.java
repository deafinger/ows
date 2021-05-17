package com.api.ows.reservadvanced.vo.response;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * @Class InvoiceResVO
 * @Description : 결제내역 조회 Response VO 
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
public class InvoiceResVO {
	private	String	firstName					;	//	# firstName : 이름
	private	String	lastName					;	//	# lastName : 성
	private	String	nameId						;	//	# nameId : 고객 고유 번호
	private	String	billNumber					;	//	# billNumber : 영수증 번호
	private	List<Map<String,Object>> billItems	;	//	# billItems : 거래아이템
	private	String	totalCharges				;	//	# totalCharges : 총 비용
	private	String	currencyCharges				;	//	# currencyCharges : 현재 지불된 금액
	
	private	String	totalCurrencyCode			;	//	# totalCurrencyCode : 총비용 화폐코드 
	private	String	currencyCurrencyCode		;	//	# currencyCurrencyCode : 현재지불 화폐코드
	private	String	totalRoomRateAndPackages 	;	//	# totalRoomRateAndPackages  : 객실 이용 요금 + 패키지 비용
	private	String	taxInclusive				;	//	# taxInclusive : 세금 포함 여부
	private	String	taxesAndFees				;	//	# taxesAndFees : 세금 비용  
}
