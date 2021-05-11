package com.api.ows.reservation.vo.request;

import lombok.Data;


/**
 * @Class FutureBookingSummaryReqVO.java
 * @Description : FutureBookingSummary Request VO 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 7.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 7.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Data
public class FutureBookingSummaryReqVO {
	private String nameId; //profile ID
	private String startDate; //profile ID
	private String endDate; //profile ID
}
