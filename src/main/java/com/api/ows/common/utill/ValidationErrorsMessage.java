package com.api.ows.common.utill;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Component
public class ValidationErrorsMessage {
	public Map<String, Object> getMessage(Errors errors){
		Map<String,Object> result = new HashMap<>();
		for (FieldError error : errors.getFieldErrors()) {
			result.put(error.getField(), error.getDefaultMessage());
		}
		return result;
	}
}
