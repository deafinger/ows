package com.api.ows.reservation.model.futureBookingSummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Class FutureBookingSummaryBody.java
 * @Description : FutureBookingSummary Request Body Model
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
@Builder
public class FutureBookingSummaryBody {
	private NameID NameID;  
	private String xmlns;  // # xmlns : 고유 Attribute
	private AdditionalFilters AdditionalFilters;
}
