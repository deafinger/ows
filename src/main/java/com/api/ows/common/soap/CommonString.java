package com.api.ows.common.soap;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Class CommonString
 * @Description : SOAP 통신용 공통 String  정의
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 12.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 12.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
public final class CommonString {
	/* ################################ DATEFORMAT START #################################### */
	public static final String TZBetween = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String TBetween = "yyyy-MM-dd'T'HH:mm:ss.SSS'00'ZZ";
	public static final String BASICDATEFORM = "yyyy-MM-dd-HH:mm";
	public static final String POSTING_DATE = "yyyy-MM-dd";
	public static final String POSTING_TIME= "HH:mm:ssZZ";
	/* ################################ DATEFORMAT END #################################### */
	
	/* ################################ TYPE START #################################### */
	public static final String ARRIVALDATE = "ARRIVAL_DATE";
	public static final String TYPEIN = "INTERNAL";
	public static final String TYPEEX = "EXTERNAL";
	/* ################################ TYPE END #################################### */
	
	/* ################################ XMLNS START #################################### */
	public static final String RESVWSDL		= "http://webservices.micros.com/ows/5.1/Reservation.wsdl" ;
	public static final String NAMEWSDL		= "http://webservices.micros.com/ows/5.1/Name.wsdl"        ;
	public static final String HOTELLCOMMON = "http://webservices.micros.com/og/4.3/HotelCommon/"      ;
	public static final String RESVADVANCED = "http://webservices.micros.com/og/4.3/ResvAdvanced/"     ;
	public static final String COMMON 		= "http://webservices.micros.com/og/4.3/Common/"           ;
	public static final String RESERVATION 	= "http://webservices.micros.com/og/4.3/Reservation/"      ;
	public static final String NAME 		= "http://webservices.micros.com/og/4.3/Name/"             ;
	public static final String MEMBERSHIP 	= "http://webservices.micros.com/og/4.3/Membership/"       ;
	public static final String CORE 		= "http://webservices.micros.com/og/4.3/Core/"             ;
	public static final String HOUSE 		= "http://webservices.micros.com/og/4.3/HouseKeeping/"     ;
	/* ################################ XMLNS END #################################### */
	
	//Result status 

	public static final String SUCESS 		= "SUCCESS";
	public static final String FAIL 		= "FAIL";
	public static final String ADULT 		= "ADULT";
	public static final String CHILD 		= "CHILD";

	// WSDL type
	public static final String RESERVATION_WSDL  = "Reservation.asmx";
	public static final String RESVADVANCED_WSDL = "ResvAdvanced.asmx";
	public static final String NAME_ASMX         = "Name.asmx";

	// action
	public static final String NAME_LOOKUP_ACTION   = "/Name.wsdl#NameLookup";
	public static final String FETCH_PROFILE_ACTION = "/Name.wsdl#FetchProfile";

}
