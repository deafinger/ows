package com.api.ows.common.soap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.common.base.CaseFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Class MapToBodyElement
 * @Description : Map의 값을 Soap Body Element 구조로 바꾸는 공통
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
public class MapToBodyElement {
	private List<Map<String,Object>> nextList;
	private Element parent;
	private Document doc;
	
	public MapToBodyElement(Document doc, Element parent) {
		this.doc = doc;
		this.parent = parent;
	}
	
	/**
	* @Description : 최초 호출용 Method
	* @param  Map<String, Object>
	* @return List<Element>
	* @author 서민재
	*/
	public List<Element> firstMap(Map<String, Object> param) throws Exception{
		log.info("Body Model Map : {}",param);
		List<Element> result = new ArrayList<Element>();
		try {
			Set entry = param.entrySet();
			Iterator it = entry.iterator();
			while(it.hasNext()) {
				Map.Entry<String, Object> node = (Map.Entry<String, Object>)it.next();
				Element first = this.doc.createElement(node.getKey());
				Object value = node.getValue();
				if(node.getValue() instanceof Map) {
					first = new MapToBodyElement(this.doc, first).doNext((Map<String,Object>)value);
				}
				result.add(first);
			}
		} catch (Exception e) {
			log.info("Cause : {}", e.getCause());
			log.info("Message : {}", e.getMessage());
			throw new RuntimeException("Element Parsing Error");
		}
		return result;
	}
	
	/**
	* @Description : 최초호출용 Method에서 호출하는 Method
	* @param  Map<String,Object>
	* @return Element
	* @author 서민재
	*/
	public Element doNext(Map<String,Object> child){
		// this.keyName value 
		Set entry = child.entrySet();
		Iterator it = entry.iterator();
		while(it.hasNext()) {
			Map.Entry<String, Object> node = (Map.Entry<String, Object>)it.next();
			if(node.getValue() != null) {
				if(node.getValue() instanceof Map) {
					Element childElement = doc.createElement(node.getKey());
					this.parent.appendChild(childElement);
					Map<String,Object> nextMap = (Map<String,Object>)node.getValue();
					MapToBodyElement next = new MapToBodyElement(this.doc, childElement);
					childElement = next.doNext(nextMap);
				}else if(node.getKey().equals("nodeValue")) {
					//parent Element  NodeText
					if(node.getValue().equals("")) {
						this.parent.appendChild(this.doc.createTextNode("\n"));
					}else {
						this.parent.appendChild(
								this.doc.createTextNode( 
												node.getValue().toString()
										)
								);
					}
				}else {
					//parent Attribute
					this.parent.setAttribute(node.getKey(), node.getValue().toString());
				}
			}
		}
		return this.parent;
	}
}
