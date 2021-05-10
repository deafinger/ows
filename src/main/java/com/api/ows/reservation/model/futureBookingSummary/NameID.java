package com.api.ows.reservation.model.futureBookingSummary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class NameID.java
 * @Description : 
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
public class NameID {
	private String type; 		// # type : 고유 Attribute
	private String nodeValue; 	// # nodeValue :  Profile의 고유의 값
}
