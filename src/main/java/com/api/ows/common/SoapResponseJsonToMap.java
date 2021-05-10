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


@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class SoapResponseJsonToMap {

	/**
	 * 怨듯넻 Attribute 吏��슦湲�
	 * @author �꽌誘쇱옱
	 *
	 */
	public Map<String, Object> removeCommonAttribute(Map<String, Object> source, String parentKey) {
		// Data �떞湲�
		Set entry = source.entrySet();
		//java.util.ConcurrentModificationException 愿�怨꾨줈  Vector濡� �옱洹� �닔�뻾
		Vector<String> removeList = new Vector<String>();
		Vector<String> objectList = new Vector<String>();
		//Iterator 濡� 嫄곕Ⅴ湲� �떎�뻾
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
	//ListType Data 諛섑솚
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
	//body 媛��졇�삤湲�
	public Map<String, Object> getBody(Map<String, Object> source){
		Set<String> keys = source.keySet();
		Map<String, Object> result = null;
		//Key 濡�  forEach
		for(String key : keys) {
			Object value = source.get(key);
			//Map�씤寃쎌슦
			if(value instanceof Map) {
				if(key.equals("soap:Body")) { // Map�씤�뜲 Key媛� Body �씪 寃쎌슦
					result = (Map<String, Object>)value;
					break;
				}else {// Map�씤�뜲 Key媛� Body �븘�땺 寃쎌슦
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
