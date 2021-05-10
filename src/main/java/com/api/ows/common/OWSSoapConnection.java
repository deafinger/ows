package com.api.ows.common;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

//import javax.xml.soap.*;
import jakarta.xml.soap.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.github.underscore.lodash.*;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class OWSSoapConnection {
	public Map<String, Object> doSoapConnection(Map<String, Object> param, String soapAction) throws Exception {
		// Body Document
		MapToBodyElement converter = new MapToBodyElement(new CreateDocument().createDoc(), null);
		// Element
		List<Element> bodyElemet = converter.firstMap(param);
		Document doc = converter.getDoc();

		// Document xml 
		OWSXmlCommon common = new OWSXmlCommon(doc, bodyElemet);
		doc = common.getSoapXml();

		SOAPMessage responseSoap = common.getResponseSoap(doc, soapAction);

		SOAPBody resBody = responseSoap.getSOAPBody();

		ByteArrayOutputStream resout = new ByteArrayOutputStream();
		responseSoap.writeTo(resout);
		String response = new String(resout.toByteArray());
		String responseJson = U.xmlToJson(response);

//				log.info("responseJson : {}", responseJson);

		JSONParser parser = new JSONParser();
		JSONObject result = (JSONObject) parser.parse(responseJson);
		// Map
		Map<String, Object> reMap = new SoapResponseJsonToMap().convertMap(result);
		// Body
		reMap = new SoapResponseJsonToMap().getBody(reMap);
		//Attribute 
		reMap = new SoapResponseJsonToMap().removeCommonAttribute(reMap, "FIRST");
//				reMap  = new ReturnJson().convertOnlevel(null, reMap);
		return reMap;
	}

}
