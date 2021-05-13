package com.api.ows.common.utill;

import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.github.underscore.lodash.U;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class CommonFormat
 * @Description : Date등의 Format 관련
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 13.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 13.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Slf4j
@NoArgsConstructor
@Component
public class CommonUtill {
	/**
	* @Description :  Date를 JodaTimeFormat으로 바꾸어서 반환
	* @param  java.utill.Date , String
	* @return String
	* @author 서민재
	*/
	public static String jodaDateFormat(Date date,String format) {
		DateTime to = new DateTime(date);
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		return formatter.print(to);
	}
	
	public static Object pathMapGetObj(Map map, String path) {
		try {
			Object result = U.get(map, path);
			return result;
		} catch (NullPointerException e) {
			return null;
		} 
	}
	public static String pathMapGetString(Map map, String path) {
		try {
			Object result = U.get(map, path);
			return result.toString();
		} catch (NullPointerException e) {
			return null;
		} 
	}
	

}
