package com.api.ows.common.utill;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * @Class ValidationErrorsMessage
 * @Description : Field Validation Common Class
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 21.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 21.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Component
public class ValidationErrorsMessage {
	/**
	* @Description :  JSON 방식 Return
	* @param  Errors 
	* @return Map<String,Object>
	* @author 서민재
	*/
	public Map<String, Object> getMessage(Errors errors){
		Map<String,Object> result = new HashMap<>();
		for (FieldError error : errors.getFieldErrors()) {
			result.put(error.getField(), error.getDefaultMessage());
		}
		return result;
	}
	/**
	* @Description : message 만 Return 
	* @param  Errors
	* @return String
	* @author 서민재
	*/
	public String getExceptionMessage(Errors errors){
		String message="";
		Map<String,Object> result = new HashMap<>();
		for (FieldError error : errors.getFieldErrors()) {
			if(message.equals("")) message += error.getField()+"는 "+error.getDefaultMessage();
			else message += " / "+error.getField()+"는 "+error.getDefaultMessage();
		}
		return message;
	}
}
