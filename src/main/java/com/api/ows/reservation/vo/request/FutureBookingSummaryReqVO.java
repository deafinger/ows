package com.api.ows.reservation.vo.request;



import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
@NoArgsConstructor
@AllArgsConstructor
public class FutureBookingSummaryReqVO {
	@NotNull(message = "ID는 필수입력 사항입니다.")
	private String nameId; //profile ID
	@Max(value = 1,message = "test")
	private String startDate; //profile ID
	private String endDate; //profile ID
	
}
