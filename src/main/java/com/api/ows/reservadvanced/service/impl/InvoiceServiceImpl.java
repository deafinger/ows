package com.api.ows.reservadvanced.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.common.utill.ComponetObjectMapper;
import com.api.ows.reservadvanced.model.invoice.InvoiceBody;
import com.api.ows.reservadvanced.service.InvoiceService;
import com.api.ows.reservadvanced.vo.request.InvoiceReqVO;
import com.api.ows.reservadvanced.vo.response.InvoiceResVO;
import com.api.ows.reservation.vo.response.FutureBookingSummaryResVO;
import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class InvoiceServiceImpl
 * @Description :  InvoiceRequest 통신 구현 클래스 
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 17.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 17.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService{
	
	@Override
	public Map<String, Object> doInvoice(InvoiceReqVO param) throws Exception {
		//BodyModel 만들기
		final InvoiceBody setting = new InvoiceBody(param);
		log.info("Body Map : {}",setting.getBody());
		//SOAP 통신
		Map<String,Object> soapResultMap = new OWSSoapConnection().doSoapConnection(setting.getBody(), "/ResvAdvanced.wsdl#Invoice","ResvAdvanced.asmx");
		Map<String,Object> status = U.get(soapResultMap, "InvoiceResponse.Result");
		
		log.info("status : {}",status );
		List<InvoiceResVO> voList = new ArrayList<>();
		//Vo 담기
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new AttributeNotFoundException(status.get("c:OperaErrorCode").toString());
		
		Map<String,Object> invoice = U.get(soapResultMap, "InvoiceResponse");
		voList.add(setVO(invoice));
		
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("invoice",voList );
		return result;
	}
	
	/**
	* @Description : Return VO Setting 
	* @param Object(Map<String,Object>)  
	* @return InvoiceResVO
	* @author 서민재
	*/
	private  InvoiceResVO setVO(Object info) {
		
		InvoiceResVO vo  = new InvoiceResVO();
		Map<String,Object> infoMap = (Map<String,Object>)info;
		
		// # firstName : 이름 	
		vo.setFirstName(CommonUtill.pathMapGetString(infoMap, "Invoice.r:Name.c:firstName"));
		// # lastName : 성		
		vo.setLastName(CommonUtill.pathMapGetString(infoMap, "Invoice.r:Name.c:lastName"));
		// # nameId : 고객 고유 번호	
		vo.setNameId(CommonUtill.pathMapGetString(infoMap, "Invoice.r:ProfileIDs.c:UniqueID.#text"));
		// # billNumber : 영수증 번호	
		vo.setBillNumber(CommonUtill.pathMapGetString(infoMap, "Invoice.r:BillNumber.#text"));
		
		// # billItems : 거래아이템 	
		Object billItems = CommonUtill.pathMapGetObj(infoMap, "Invoice.r:BillItems");
		if(billItems instanceof List) {
			List<Map<String,Object>> fieldList = new ArrayList<>();
			List<Map<String,Object>> itemList = (List) billItems;
			for(Map<String,Object> item : itemList) {
				Map<String,Object> re = new HashMap<>();
				re.put("vatCode", CommonUtill.pathMapGetString(item,"r:VatCode.#text"));
				re.put("amount", CommonUtill.pathMapGetString(item,"r:Amount.#text"));
				re.put("amountCurrencyCode", CommonUtill.pathMapGetString(item,"r:VatCode.-currencyCode"));
				re.put("description", CommonUtill.pathMapGetString(item,"-Description"));
				re.put("date", CommonUtill.pathMapGetString(item,"-Date"));
				re.put("quantity", CommonUtill.pathMapGetString(item,"r:Quantity"));
				re.put("supplement", CommonUtill.pathMapGetString(item,"r:Supplement"));
				fieldList.add(re);
			}
			vo.setBillItems(fieldList);
		}else {
			Map<String,Object> item = (Map)billItems;
			List<Map<String,Object>> fieldList = new ArrayList<>();
			Map<String,Object> re = new HashMap<>();
			re.put("vatCode", CommonUtill.pathMapGetString(item,"r:VatCode.#text"));
			re.put("amount", CommonUtill.pathMapGetString(item,"r:Amount.#text"));
			re.put("amountCurrencyCode", CommonUtill.pathMapGetString(item,"r:Amount.-currencyCode"));
			re.put("description", CommonUtill.pathMapGetString(item,"-Description"));
			re.put("date", CommonUtill.pathMapGetString(item,"-Date"));
			re.put("quantity", CommonUtill.pathMapGetString(item,"r:Quantity"));
			re.put("supplement", CommonUtill.pathMapGetString(item,"r:Supplement"));
			fieldList.add(re);
			vo.setBillItems(fieldList);
		}
		// # totalCharges : 총 비용			
		vo.setTotalCharges(CommonUtill.pathMapGetString(infoMap, "ExpectedCharges.TotalCharges.#text"));
		// # totalCurrencyCode : 총비용 화폐코드 
		vo.setTotalCurrencyCode(CommonUtill.pathMapGetString(infoMap, "ExpectedCharges.TotalCharges.-currencyCode"));
		// # currencyCharges : 현재 지불된 금액 
		vo.setCurrencyCharges(CommonUtill.pathMapGetString(infoMap, "ExpectedCharges.CurrentBalance.#text"));
		// # currencyCurrencyCode :	햔제 지불 금액 화폐코드	
		vo.setCurrencyCurrencyCode(CommonUtill.pathMapGetString(infoMap, "ExpectedCharges.CurrentBalance.-currencyCode"));
		// # totalRoomRateAndPackages  : 객실 이용 요금 + 패키지 비용	
		vo.setTotalRoomRateAndPackages(CommonUtill.pathMapGetString(infoMap, "ExpectedCharges.DailyCharges.-TotalRoomRateAndPackages"));
		// # taxInclusive : 세금 포함 여부	
		vo.setTaxInclusive(CommonUtill.pathMapGetString(infoMap, "ExpectedCharges.DailyCharges.-TaxInclusive"));
		// # taxesAndFees : 세금 비용	
		vo.setTaxesAndFees(CommonUtill.pathMapGetString(infoMap, "ExpectedCharges.DailyCharges.-TotalTaxesAndFees"));
		return vo;
	}
}
