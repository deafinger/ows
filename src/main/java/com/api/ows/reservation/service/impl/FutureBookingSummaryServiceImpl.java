package com.api.ows.reservation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.BadAttributeValueExpException;

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
	public Map<String,Object> doFutureBookingSummaryRequest(FutureBookingSummaryReqVO param) throws Exception {
		return null;
	}
	
	@Override
	public Map<String, Object> doFutureBookingSummaryRequestByDate(FutureBookingSummaryReqVO param) throws Exception {
		//BodyModel 만들기
		FutureBookingSummaryBody setting = new FutureBookingSummaryBody(param);
		Map<String, Object> body = mapper.getMapper().convertValue(setting, Map.class);
	
		Map<String,Object> bodyMap = new HashMap<String,Object>();
		bodyMap.put("FutureBookingSummaryRequest", body);
		
		//SOAP 통신
		OWSSoapConnection con = new OWSSoapConnection();
		Map<String,Object> result = con.doSoapConnection(bodyMap, "/Reservation.wsdl#FutureBookingSummary");
		Map<String,Object> status = U.get(result, "FutureBookingSummaryResponse.Result");
		
		//Vo 담기
		if(status.get("-resultStatusFlag").equals(CommonString.SUCESS)) { // soap result 코드 확인
			Object reservations = U.get(result, "FutureBookingSummaryResponse.HotelReservations.r:HotelReservation");
			FutureBookingSummaryResVO vo  = new FutureBookingSummaryResVO();
			if(reservations instanceof List) {
				log.info("reservations List : {}",reservations.getClass());
				List<Object> infos = (List)reservations;
				for(Object info  : infos) {
					Map<String,Object> infoMap = (Map<String,Object>)info;
					
					//nameId
					vo.setNameId(CommonUtill.pathMapGet(infoMap, "r:ResGuests.r:ResGuest.r:Profiles.Profile.ProfileIDs.c:UniqueID.#text").toString()) ;
					//RPH
					vo.setRph(CommonUtill.pathMapGet(infoMap, "r:ResGuests.r:ResGuest.-resGuestRPH").toString()); 					
					//firstName
					vo.setFirstName(CommonUtill.pathMapGet(infoMap,"r:ResGuests.r:ResGuest.r:Profiles.Profile.Customer.PersonName.c:firstName").toString()) ;
					//lastName
					vo.setLastName(CommonUtill.pathMapGet(infoMap,"r:ResGuests.r:ResGuest.r:Profiles.Profile.Customer.PersonName.c:lastName").toString()) ;
					//phone
					vo.setPhone(CommonUtill.pathMapGet(infoMap,"r:ResGuests.r:ResGuest.r:Profiles.Profile.Phones.NamePhone.c:PhoneNumber").toString()) ;
					//email
					vo.setEmail(CommonUtill.pathMapGet(infoMap,"r:ResGuests.r:ResGuest.r:Profiles.Profile.EMails.NameEmail").toString()) ;
					List<Object> idList = (List<Object>) CommonUtill.pathMapGet(infoMap, "r:UniqueIDList.c:UniqueID");
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
					vo.setHotelReference(CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:HotelReference").toString()) ;
					//roomClass				
					vo.setRoomClass(CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.-roomClass").toString()) ;
					//roomTypeCode
					vo.setRoomTypeCode(CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.-roomTypeCode").toString()) ;
					//roomTypeDescription				
					vo.setRoomTypeDescription(CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.hc:RoomTypeDescription.hc:Text").toString()) ;
					//roomNumber
					vo.setRoomNumber(CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.hc:RoomNumber").toString()) ;
					//ratePlanCode			
					vo.setRatePlanCode(CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:RatePlans.hc:RatePlan.-ratePlanCode").toString()) ;
					//ratePlanDescription
					vo.setRatePlanDescription(CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:RatePlans.hc:RatePlan.hc:RatePlanDescription.hc:Text").toString()) ;
					//startDate 			
					vo.setStartDate(CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:TimeSpan.hc:StartDate").toString()) ;	
					//endDate
					vo.setEndDate(CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:TimeSpan.hc:EndDate").toString()) ;
					//reservationStatus		
					vo.setReservationStatus(CommonUtill.pathMapGet(infoMap,"-reservationStatus").toString()) ;
					//adult
					//	r:RoomStays.hc:RoomStay.hc:GuestCounts.hc:GuestCount
					List<Object> counts = (List<Object>)CommonUtill.pathMapGet(infoMap,"r:RoomStays.hc:RoomStay.hc:GuestCounts.hc:GuestCount");
					for(Object count : counts) {
						Map<String,Object> countMap =(Map<String,Object>) count;
						// Adult&Child count
						if(countMap.get("-ageQualifyingCode").equals(CommonString.ADULT)) vo.setAdult(countMap.get("-count").toString()) ;
						else if(countMap.get("-ageQualifyingCode").equals(CommonString.CHILD)) vo.setChild(countMap.get("-count").toString()) ;
					}

				}
			}else {
				log.info("reservations Else Type : {}",reservations.getClass());
			}
		}else {
			throw new Exception("OPMS DATA EXCEPTION", null);
		}
		
		return result;
	}
}
