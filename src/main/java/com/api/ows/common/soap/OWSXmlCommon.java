package com.api.ows.common.soap;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

//import javax.xml.soap.*;
import jakarta.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * @Class OWSXmlCommon
 * @Description : Header등 공통 구조 Xml builder 및 Response 구조 변환 Return 공통 클래스 
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
@AllArgsConstructor
@Slf4j
public class OWSXmlCommon {
	private Document doc;
	private List<Element> elements;
	
	/**
	* @Description : 공통적 구조인 Header Build Method 
	* @param  None
	* @return Document
	* @author 서민재
	*/
	public Document getSoapXml() throws Exception{
		doc.setXmlStandalone(true); //standalone="no" 
        
        // soap:Envelope Element 
        Element soapEnvelop = doc.createElement("soap:Envelope");
        soapEnvelop.setAttribute("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/"           );
        soapEnvelop.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance"            );
        soapEnvelop.setAttribute("xmlns:s2","http://webservices.micros.com/og/4.3/Name/"            );
        soapEnvelop.setAttribute("xmlns:s5","http://webservices.micros.com/og/4.3/Membership/"      );
        soapEnvelop.setAttribute("xmlns:s3","http://webservices.micros.com/og/4.3/HotelCommon/"     );
        soapEnvelop.setAttribute("xmlns:tns","http://tempuri.org/"                                  );
        soapEnvelop.setAttribute("xmlns:s4","http://webservices.micros.com/og/4.3/Core/"            );
        soapEnvelop.setAttribute("xmlns:s0","http://webservices.micros.com/ows/5.1/Name.wsdl"       );
        soapEnvelop.setAttribute("xmlns:s1","http://webservices.micros.com/og/4.3/Common/"          );
        soapEnvelop.setAttribute("xmlns:tm","http://microsoft.com/wsdl/mime/textMatching/"			);
        doc.appendChild(soapEnvelop);
        
        //<soap:Header>
        Element soapheader = doc.createElement("soap:Header");
        soapEnvelop.appendChild(soapheader);
        
        //<OGHeader>
        Element ogHeader = doc.createElement("OGHeader");
        ogHeader.setAttribute("transactionID", "3297907325");
        ogHeader.setAttribute("primaryLangID", "E");
        // yyyy-MM-ddTHH:mm:ss.ms-
        ogHeader.setAttribute("timeStamp", "2021-01-05T00:00:00.000000-00:00");
        ogHeader.setAttribute("xmlns", "http://webservices.micros.com/og/4.3/Core/");
        soapheader.appendChild(ogHeader);
        
        //<Origin>
        Element origin = doc.createElement("Origin");
        origin.setAttribute("entityID", "KIOSK");
        origin.setAttribute("systemType", "KIOSK");
        ogHeader.appendChild(origin);
        
        //<Destination>
        Element destination = doc.createElement("Destination");
        destination.setAttribute("entityID", "PMS");
        destination.setAttribute("systemType", "PMS");
        ogHeader.appendChild(destination);
        
        //<Authentication>
        Element authentication = doc.createElement("Authentication");
        ogHeader.appendChild(authentication);
        
        //<UserCredentials>
        Element userCredentials = doc.createElement("UserCredentials");
        authentication.appendChild(userCredentials);
        
        //<UserName>
        Element userName = doc.createElement("UserName");
        userName.appendChild(doc.createTextNode("KIOSK"));
        userCredentials.appendChild(userName);
        
        //<UserPassword>
        Element userPassword = doc.createElement("UserPassword");
        userPassword.appendChild(doc.createTextNode("$$$KIOSK$$"));
        userCredentials.appendChild(userPassword);
        
        //<soap:Body>
        Element soapBody = doc.createElement("soap:Body");
        soapEnvelop.appendChild(soapBody);
        
        // Body 
        if(this.elements.size()>=1) {
        	for(Element el : elements) {
        		soapBody.appendChild(el);
        	}
        }

		return doc;
	}
	
	/**
	* @Description : Soap 통신 설정 및 통신 Request, Response  부분
	* @param  Document, String 
	* @return SOAPMessage
	* @author 서민재
	*/
	public SOAPMessage getResponseSoap(Document doc,String actionTag) throws Exception{
		String soapUrl = "http://ec2-52-78-184-230.ap-northeast-2.compute.amazonaws.com:8080/ows_ws_51/Reservation.asmx";
		String soapAction = "http://webservices.micros.com/ows/5.1" + actionTag;
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		transformer.transform(new DOMSource(doc), new StreamResult(baos));
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		
		MessageFactory mf = MessageFactory.newInstance();
		
		SOAPMessage soapMsg = mf.createMessage(new MimeHeaders(), bais);
		
		ByteArrayOutputStream out  = new ByteArrayOutputStream();
		//Action 
		MimeHeaders headers = soapMsg.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMsg.saveChanges();
        //request 
        soapMsg.writeTo(out);
		String request = new String(out.toByteArray());
		log.info("Soap Request : {}", request);
		
		//connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        
        SOAPMessage soapResponse = soapConnection.call(soapMsg, soapUrl);
        // Print the SOAP Response
        ByteArrayOutputStream resout = new ByteArrayOutputStream();
        soapResponse.writeTo(resout);
        String response = new String(resout.toByteArray());
        log.info("Soap Response : {} ", response);
		return soapResponse;
	}
}
