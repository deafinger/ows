package com.api.ows.common.utill;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase{
	public static final PropertyNamingStrategy DEFAULT_CASE = new CustomNamingStrategy();
	
	@Override
	public String translate(String propertyName) {
		return propertyName;
	}
}
