package com.api.ows.reservadvanced.model.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Class ReservationRequest
 * @Description : 
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
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
	private HotelReference HotelReference;
	private ReservationID ReservationID;
}
