package com.api.ows.reservadvanced.service;

import java.util.Map;

import com.api.ows.reservadvanced.vo.request.InvoiceReqVO;

/**
 * @Class InvoiceService
 * @Description : 캡슐화를 위한 InvoiceService InterFace 
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
public interface InvoiceService {
	/**
	* @Description : InvoiceRequest Connection Method
	* @param  InvoiceReqVO 
	* @return Map<String,Object>
	* @author 서민재
	*/
	Map<String, Object> doInvoice(InvoiceReqVO param) throws Exception;
}
