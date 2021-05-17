package com.api.ows.reservadvanced.model.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class UniqueID
 * @Description : Soap Request Body Node Class 
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniqueID {
	private String type;
	private String source;
	private String nodeValue;
	private String xmlns;
}
