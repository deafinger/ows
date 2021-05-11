package com.api.ows.common;

import lombok.Setter;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * @Class SoapResponseJsonToMap
 * @Description : SoapResponse 의  공통 Attribute 제거 및 Map 전환
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
@Getter
@Setter
@NoArgsConstructor
public class SoapResponseJsonToMap {

	
	/**
	* @Description : 공통 Attribute 제거 
	* @param  Map<String, Object> , String 
	* @return Map<String,Object>
	* @author 서민재
	*/
	public Map<String, Object> removeCommonAttribute(Map<String, Object> source, String parentKey) {
	
		Set entry = source.entrySet();
	
		Vector<String> removeList = new Vector<String>();
		Vector<String> objectList = new Vector<String>();
	
		Iterator it = entry.iterator();
		while(it.hasNext()) {
			Map.Entry<String, Object> entryMap =(Map.Entry<String, Object>) it.next();
			if(entryMap.getKey().equals("-xmlns")
					|| entryMap.getKey().equals("-xmlns:xsi")
					|| entryMap.getKey().equals("-xmlns:soap")
					|| entryMap.getKey().equals("-xmlns:wsa")
					|| entryMap.getKey().equals("-xmlns:xsd")
					|| entryMap.getKey().equals("-xmlns:hc")
					|| entryMap.getKey().equals("-xmlns:r")
					|| entryMap.getKey().equals("-xmlns:c")
					|| entryMap.getKey().equals("-type")
					) {
				removeList.add(entryMap.getKey());
			}else if(entryMap.getValue() instanceof Map) {
				objectList.add(entryMap.getKey());
			}else if(entryMap.getValue() instanceof List) {
				source.put(entryMap.getKey(), new SoapResponseJsonToMap().doArrayList((List)entryMap.getValue()));
			}
		}
		for(int i =0; i<removeList.size(); i++) {
//			log.info("Remove Node : {}", removeList.get(i));
			source.remove(removeList.get(i));
		}
		for(int i= 0 ; i<objectList.size();i++) {
			String childKey = objectList.get(i);
			Object child = source.get(childKey);
			SoapResponseJsonToMap next = new SoapResponseJsonToMap();
			Map<String, Object> childMap = next.removeCommonAttribute((Map<String, Object>)child,childKey);
			source.put(childKey,childMap);
		}
		return source;
	}
	
	/**
	* @Description : ListType 분기 처리 Method
	* @param  List 
	* @return List
	* @author 서민재
	*/
	public List doArrayList( List source){
		List after = new ArrayList();
		for(int i = 0; i<source.size();i++) {
			if(source.get(i) instanceof Map){
				after.add(new SoapResponseJsonToMap().removeCommonAttribute((Map<String,Object>)source.get(i), "List"));
			}
		}
		if(after.size()>0) {
			return after;
		}
		return source;
	}
	
	public Map<String, Object> getBody(Map<String, Object> source){
		Set<String> keys = source.keySet();
		Map<String, Object> result = null;
		for(String key : keys) {
			Object value = source.get(key);
			if(value instanceof Map) {
				if(key.equals("soap:Body")) { 
					result = (Map<String, Object>)value;
					break;
				}else {
					result = new SoapResponseJsonToMap().getBody((Map<String, Object>)value);
				}
			}else {
				continue;
			}
		}
		
		return result; 
	}
	
	public Map<String, Object> convertMap(JSONObject source) throws JsonMappingException, JsonProcessingException{
		Map<String, Object> result = new ObjectMapper().readValue(source.toJSONString(), Map.class);
		return result;
	}
}
