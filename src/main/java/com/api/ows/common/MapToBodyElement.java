package com.api.ows.common;

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

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class MapToBodyElement {
	private List<Map<String,Object>> nextList;
	private Element parent;
	private Document doc;
	
	//child Json �쓣 諛쏆쓣�븣 �깮�꽦�옄
	public MapToBodyElement(Document doc, Element parent) {
		this.doc = doc;
		this.parent = parent;
	}
	
	//理쒖큹 JSon 諛쏆쓣�떆 
	public List<Element> firstMap(Map<String, Object> param) throws Exception{
		
		List<Element> result = new ArrayList<Element>();
		
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
		return result;
	}
	
	//Convert 
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
				}else if(node.getKey().equals("NodeValue")) {
					//parent Element  NodeText
					if(node.getValue().equals("")) {
						this.parent.appendChild(this.doc.createTextNode("\n"));
					}else {
						String text = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, node.getValue().toString());
						log.info(text);
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
