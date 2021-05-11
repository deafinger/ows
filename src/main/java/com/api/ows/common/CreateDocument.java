package com.api.ows.common;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import lombok.*;

/**
 * @Class CreateDocument
 * @Description : Document 객체 생성 공통 클래스 
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
@Getter
@Setter
@NoArgsConstructor 
public class CreateDocument {
	
	public Document createDoc() throws Exception{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		return doc;
	}
}
