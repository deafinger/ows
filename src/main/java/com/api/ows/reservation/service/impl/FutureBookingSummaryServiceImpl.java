package com.api.ows.reservation.service.impl;

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
import com.api.ows.reservation.model.futureBookingSummary.FutureBookingSummaryBody;
import com.api.ows.reservation.service.FutureBookingSummaryService;
import com.api.ows.reservation.vo.request.FutureBookingSummaryReqVO;
import com.api.ows.reservation.vo.response.FutureBookingSummaryResVO;
import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;

/**
 * @Class FutureBookingSummaryServiceImpl
 * @Description : FutureBookingSummary Logic 구현
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
@Service
public class FutureBookingSummaryServiceImpl implements FutureBookingSummaryService {
	
	@Autowired
	ComponetObjectMapper mapper;
	
	@Override
	public Map<String, Object> doFutureBookingSummaryRequest(FutureBookingSummaryReqVO param) throws Exception {
		//BodyModel 만들기
		final FutureBookingSummaryBody setting = new FutureBookingSummaryBody(param);
	
		//SOAP 통신
		final Map<String,Object> soapResultMap = new OWSSoapConnection().doSoapConnection(setting.getBody(), "/Reservation.wsdl#FutureBookingSummary","Reservation.asmx");
		final Map<String,Object> status = U.get(soapResultMap, "FutureBookingSummaryResponse.Result");
		
		log.info("status : {}",status );
		List<FutureBookingSummaryResVO> voList = new ArrayList<>();
		//Vo 담기
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new AttributeNotFoundException(status.get("c:OperaErrorCode").toString());
		 // soap result 코드 확인
			
		Object reservations = U.get(soapResultMap, "FutureBookingSummaryResponse.HotelReservations.r:HotelReservation");
		if(reservations instanceof List) {
			List<Object> infos = (List)reservations;
			for(Object info  : infos) {
				voList.add(setVO(info));
			}
		}else voList.add(setVO(reservations));
		
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("reservationList",voList );
		result.put("reservationListSize", Integer.valueOf(voList.size()));
		return result;
	}
	
	
	/**
	* @Description : FutureBookingSummaryResponse의 값을 VO에 담아서 Return
	* @param  Object(Map)
	* @return FutureBookingSummaryResVO
	* @author 서민재
	*/
	private  FutureBookingSummaryResVO setVO(Object info) {
		
		FutureBookingSummaryResVO vo  = new FutureBookingSummaryResVO();
		Map<String,Object> infoMap = (Map<String,Object>)info;
		
		//nameId
		vo.setNameId(CommonUtill.pathMapGetString(infoMap, "r:ResGuests.r:ResGuest.r:Profiles.Profile.ProfileIDs.c:UniqueID.#text")) ;
		//RPH
		vo.setRph(CommonUtill.pathMapGetString(infoMap, "r:ResGuests.r:ResGuest.-resGuestRPH")); 					
		//firstName
		vo.setFirstName(CommonUtill.pathMapGetString(infoMap,"r:ResGuests.r:ResGuest.r:Profiles.Profile.Customer.PersonName.c:firstName")) ;
		//lastName
		vo.setLastName(CommonUtill.pathMapGetString(infoMap,"r:ResGuests.r:ResGuest.r:Profiles.Profile.Customer.PersonName.c:lastName")) ;
		//phone
		vo.setPhone(CommonUtill.pathMapGetString(infoMap,"r:ResGuests.r:ResGuest.r:Profiles.Profile.Phones.NamePhone.c:PhoneNumber")) ;
		//email
		vo.setEmail(CommonUtill.pathMapGetString(infoMap,"r:ResGuests.r:ResGuest.r:Profiles.Profile.EMails.NameEmail")) ;
		List<Object> idList = (List<Object>) CommonUtill.pathMapGetObj(infoMap, "r:UniqueIDList.c:UniqueID");
		for(Object id : idList) {
			Map<String,Object > idMap =(Map<String,Object >) id;
			try {
				//reservNameId
				if(idMap.get("-source").equals("RESVID")) vo.setReservNameId(idMap.get("#text").toString()) ;
				//legNumber
				else if(idMap.get("-source").equals("LEGNUMBER")) vo.setLegNumber(idMap.get("#text").toString())  ;
			} catch (NullPointerException e) {
				//confirmationId	
				vo.setConfirmationId(idMap.get("#text").toString());
			}
			
		}
		//hotelReference 		
		vo.setHotelReference(CommonUtill.pathMapGetString(infoMap,"r:RoomStays.hc:RoomStay.hc:HotelReference")) ;
		//roomClass				
		vo.setRoomClass(CommonUtill.pathMapGetString(infoMap,"r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.-roomClass")) ;
		//roomTypeCode
		vo.setRoomTypeCode(CommonUtill.pathMapGetString(infoMap,"r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.-roomTypeCode")) ;
		//roomTypeDescription				
		vo.setRoomTypeDescription(CommonUtill.pathMapGetString(infoMap,"r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.hc:RoomTypeDescription.hc:Text")) ;
		//roomNumber
		vo.setRoomNumber(CommonUtill.pathMapGetString(infoMap,"r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.hc:RoomNumber")) ;
		//ratePlanCode			
		vo.setRatePlanCode(CommonUtill.pathMapGetString(infoMap,"r:RoomStays.hc:RoomStay.hc:RatePlans.hc:RatePlan.-ratePlanCode")) ;
		//ratePlanDescription
		vo.setRatePlanDescription(CommonUtill.pathMapGetString(infoMap,"r:RoomStays.hc:RoomStay.hc:RatePlans.hc:RatePlan.hc:RatePlanDescription.hc:Text")) ;
		//startDate 			
		vo.setStartDate(CommonUtill.pathMapGetString(infoMap,"r:RoomStays.hc:RoomStay.hc:TimeSpan.hc:StartDate")) ;	
		//endDate
		vo.setEndDate(CommonUtill.pathMapGetString(infoMap,"r:RoomStays.hc:RoomStay.hc:TimeSpan.hc:EndDate")) ;
		//reservationStatus		
		vo.setReservationStatus(CommonUtill.pathMapGetString(infoMap,"-reservationStatus")) ;
		//adult
		//	r:RoomStays.hc:RoomStay.hc:GuestCounts.hc:GuestCount
		List<Object> counts = (List<Object>)CommonUtill.pathMapGetObj(infoMap,"r:RoomStays.hc:RoomStay.hc:GuestCounts.hc:GuestCount");
		for(Object count : counts) {
			Map<String,Object> countMap =(Map<String,Object>) count;
			// Adult&Child count
			if(countMap.get("-ageQualifyingCode").equals(CommonString.ADULT)) vo.setAdult(countMap.get("-count").toString()) ;
			else if(countMap.get("-ageQualifyingCode").equals(CommonString.CHILD)) vo.setChild(countMap.get("-count").toString()) ;
		}
		
		return vo;
	}
}
