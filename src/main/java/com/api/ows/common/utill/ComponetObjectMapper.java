package com.api.ows.common.utill;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.underscore.lodash.U;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class ComponetObjectMapper
 * @Description : ObjectMapper 생성 공통 클래스
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 11.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 11.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Slf4j
@Component
@Data
public class ComponetObjectMapper {
	private ObjectMapper mapper;
	
	public ComponetObjectMapper() {
//		this.mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
		
		this.mapper = new ObjectMapper()
				.setPropertyNamingStrategy(null);
	}
}
