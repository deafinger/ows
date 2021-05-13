package com.api.ows.reservation.vo.request;



import java.lang.reflect.Field;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


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
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FutureBookingSummaryReqVO {
	private	String	nameId	;	//	# nameId : 고객 아이디,
	
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyyMMdd")
	@DateTimeFormat(pattern = "yyyyMMdd")
	private	Date startDate	;	//	# startDate : 에약 시작 날짜, 데이터 형식은: yyyyMMdd
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyyMMdd")
	@DateTimeFormat(pattern = "yyyyMMdd")
	private	Date  endDate	;	//	# endDate : 예약 만료 날짜, 데이터 형식은: yyyyMMdd
	
	private	String	confirmationId	;	//	# confirmationId : 고객, 스태프가 확인 가능한 예약 번호
	private	String	hotelCode	;	//	# hotelCode : 호텔 코드
	private	String	chainCode	;	//	# chainCode : 호텔 체인 코드
	
    public boolean isNull() throws IllegalArgumentException, IllegalAccessException {
        Field fields[] = this.getClass().getDeclaredFields();
        for (Field f : fields) {
        	Object obj = f.get(this);
           if(obj!=null) {
        	   return false;
           }
        }
        return true;

    }
}
