package com.api.ows.common;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class GetObjectChoise {

	// first param
	public JSONObject firstParam(Map<String, Object> param, String getKey) throws ParseException {
		JSONParser parse = new JSONParser();

		JSONObject json = new JSONObject(param);
		String jsonString = json.toJSONString();
		json = (JSONObject) parse.parse(jsonString);
		List<JSONObject> nextList = new ArrayList<JSONObject>();
		JSONObject result = new JSONObject();
		Set<String> keys = json.keySet();
		log.info("JSON Build Start");
		for (String key : keys) {
			Object value = json.get(key);
			if (key.equals(getKey)) {
				JSONObject next = new JSONObject();
				next.put(getKey, value);
				nextList.add(next);
			} else if (value instanceof JSONObject) {
				List<JSONObject> next = new GetObjectChoise().second((JSONObject) value, getKey);
				if (next != null) {
					for (JSONObject obj : next) {
						nextList.add(obj);
					}
				}
			} else if (value instanceof List) {
				List<JSONObject> next = new GetObjectChoise().listObject((List) value, getKey);
				if (next != null) {
					for (JSONObject obj : next) {
						nextList.add(obj);
					}
				}
			}
		}
		if (nextList.size() > 0)
			result.put("result", nextList);
		return result;
	}
	// findNode
	public JSONObject findNode(JSONObject param, String getKey) throws ParseException {
		List<JSONObject> nextList = new ArrayList<JSONObject>();
		JSONObject result = new JSONObject();
		Set<String> keys = param.keySet();
		log.info("JSON Build Start");
		for (String key : keys) {
			Object value = param.get(key);
			if (key.equals(getKey)) {
				JSONObject next = new JSONObject();
				next.put(getKey, value);
				nextList.add(next);
			} else if (value instanceof JSONObject) {
				List<JSONObject> next = new GetObjectChoise().second((JSONObject) value, getKey);
				if (next != null) {
					for (JSONObject obj : next) {
						nextList.add(obj);
					}
				}
			} else if (value instanceof List) {
				List<JSONObject> next = new GetObjectChoise().listObject((List) value, getKey);
				if (next != null) {
					for (JSONObject obj : next) {
						nextList.add(obj);
					}
				}
			}
		}
		if (nextList.size() > 0)
			result.put("result", nextList);
		return result;
	}
	
	// next
	public List<JSONObject> second(JSONObject param, String getKey) throws ParseException {
		Set<String> keys = param.keySet();
		List<JSONObject> nextList = new ArrayList<JSONObject>();
		for (String key : keys) {
			Object value = param.get(key);
			if (key.equals(getKey)) {
				JSONObject next = new JSONObject();
				next.put(getKey, value);
				nextList.add(next);
				break;
			} else if (value instanceof JSONObject) {
				List<JSONObject> next = new GetObjectChoise().second((JSONObject) param.get(key), getKey);
				if (next != null) {
					for (JSONObject obj : next) {
						nextList.add(obj);
					}
				}
			} else if (value instanceof List) {
				List<JSONObject> next = new GetObjectChoise().listObject((List) value, getKey);
				if (next != null) {
					for (JSONObject obj : next) {
						nextList.add(obj);
					}
				}
			}
		}
		if (nextList.size() > 0)
			return nextList;
		else
			return null;
	}

	// List
	public List<JSONObject> listObject(List paramList, String getKey) throws ParseException {

		List<JSONObject> nextList = new ArrayList<JSONObject>();
		for (int i = 0; i < paramList.size(); i++) {
			Object obj = paramList.get(i);
			if (obj instanceof JSONObject) {
				List<JSONObject> next = new GetObjectChoise().second((JSONObject) obj, getKey);
				if (next != null) {
					for (JSONObject obj1 : next) {
						nextList.add(obj1);
					}
				}
			} else if (obj instanceof List) {
				List<JSONObject> next = new GetObjectChoise().listObject((List) obj, getKey);
				if (next != null) {
					for (JSONObject obj1 : next) {
						nextList.add(obj1);
					}
				}
			} else {
				log.info("List Value Type : {}", obj.getClass());
			}
		}
		if (nextList.size() > 0)
			return nextList;
		else
			return null;
	}
}
