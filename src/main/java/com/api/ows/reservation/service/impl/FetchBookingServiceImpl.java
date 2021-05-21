package com.api.ows.reservation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ows.common.exception.DataNotFoundException;
import com.api.ows.common.soap.CommonString;
import com.api.ows.common.soap.OWSSoapConnection;
import com.api.ows.common.utill.CommonUtill;
import com.api.ows.common.utill.ComponetObjectMapper;
import com.api.ows.reservation.model.fetchBooking.FetchBookingBody;
import com.api.ows.reservation.service.FetchBookingService;
import com.api.ows.reservation.vo.request.FetchBookingReqVO;
import com.api.ows.reservation.vo.response.FetchBookingResVO;
import com.github.underscore.lodash.U;

import lombok.extern.slf4j.Slf4j;


/**
 * @Class FetchBookingServiceImpl
 * @Description : FetchBookingService Logic구현
 * @
 * @ 수정일      	     수정자           수정내용
 * @ ---------  	 ---------   	-------------------------------
 * @ 2021. 5. 14.     서민재     		최초생성
 *
 * @author 서민재
 * @since 2021. 5. 14.
 * @version 1.0
 *
 *  Copyright (주)아임게이트
 */
@Slf4j
@Service
public class FetchBookingServiceImpl implements FetchBookingService{
	@Autowired
	ComponetObjectMapper mapper;
	
	@Override
	public Map<String,Object> doFetchBookingRequest(FetchBookingReqVO param) throws Exception {
		//BodyModel 만들기
		final FetchBookingBody setting = new FetchBookingBody(param);
		//SOAP 통신
		
		Map<String,Object> soapResultMap =  new OWSSoapConnection().doSoapConnection(setting.getBody(), "/Reservation.wsdl#FetchBooking","Reservation.asmx");
		Map<String,Object> status = U.get(soapResultMap, "FetchBookingResponse.Result");
		
		log.info("status : {}",status );

		// soap result 코드 확인
		if(status.get("-resultStatusFlag").equals(CommonString.FAIL)) throw new DataNotFoundException(status.get("c:OperaErrorCode").toString());
		
		//Vo 담기
		Object reservations = U.get(soapResultMap, "FetchBookingResponse.HotelReservation");
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("reservation",setVO(reservations));
		return result;
	}
	
	/**
	* @Description : FetchBooking Vo 값 넣기 함수
	* @param  Object (Map)
	* @return FetchBookingResVO
	* @author 서민재
	*/
	private  FetchBookingResVO setVO(Object info) {
		FetchBookingResVO vo  = new FetchBookingResVO();
		Map<String,Object> infoMap = (Map<String,Object>)info;

		List<Object> idList = (List<Object>)CommonUtill.pathMapGetObj(infoMap, "r:UniqueIDList.c:UniqueID");
		for(Object obj : idList) {
			Map<String,Object> objMap = (Map<String,Object>) obj;
			try {
				// # reservNameId : 시스템 예약 고유 번호
				if(objMap.get("-source").equals("RESVID")) vo.setReservNameId(objMap.get("#text").toString());
			} catch (NullPointerException e) {
				// # confirmationId : 고객이 확인 가능한 예약 번호
				vo.setConfirmationId(objMap.get("#text").toString());
			}
		}
		// # depositAmount : 보증금 금액 
//		log.info("Deposit : {}",CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RatePlans.hc:RatePlan.hc:DepositRequired.hc:DepositAmount.#text"));
		vo.setDepositAmount(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RatePlans.hc:RatePlan.hc:DepositRequired.hc:DepositAmount.#text"));	
		// # depositDueAmount 
		vo.setDepositDueAmount(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RatePlans.hc:RatePlan.hc:DepositRequired.hc:DepositDueAmount.#text"));
		// # depositAmount : 보증금 금액 
		vo.setDepositAmountCurrencyCode(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RatePlans.hc:RatePlan.hc:DepositRequired.hc:DepositAmount.-currencyCode"));

		
		// # roomRatecurrencyCode : 객실 요금 단위 코드	
		vo.setRoomRatecurrencyCode(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RoomRates.hc:RoomRate.hc:Rates.hc:Rate.hc:Base.-currencyCode"));	
		// # roomRatebase : 객실 요금 금액	
		vo.setRoomRatebase(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RoomRates.hc:RoomRate.hc:Rates.hc:Rate.hc:Base.#text"));	
		
		
		// # roomTypeCode : 객실 타입 코드	
		vo.setRoomTypeCode(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.-roomTypeCode"));	
		// # roomTypeName : 객실 타입 이름	
		vo.setRoomTypeName(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.hc:RoomTypeShortDescription.hc:Text"));
		// # roomTypeDescription : 객실 타입 설명	
		vo.setRoomTypeDescription(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.hc:RoomTypeDescription.hc:Text"));
		// # roomTypeDescription : 객실 타입 짧은설명	
		vo.setRoomTypeShortDescription(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.hc:RoomTypeShortDescription.hc:Text"));	
		// # roomNumber : 예약에 할당된 방 번호	
		vo.setRoomNumber(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:RoomTypes.hc:RoomType.hc:RoomNumber"));
		// # chainCode : 호텔 체인 코드	
		vo.setChainCode(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:HotelReference.-chainCode"));	
		// # hotelCode : 호텔 코드	
		vo.setHotelCode(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:HotelReference.-hotelCode"));	
		// # hotelReference  : 호텔명	
		vo.setHotelReference(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:HotelReference.#text")) ;
		// # totalCurrencyCode : 총 이용 금액 통화 단위 	
		vo.setTotalCurrencyCode(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:Total.-currencyCode"));	
		// # totalValue : 총 이용 금액	
		vo.setTotalValue(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:Total.#text"));	
		// # expectedtotalRoomRateAndPackages : 객실 이용 금액 + 패키지 비용 
		vo.setExpectedtotalRoomRateAndPackages(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:ExpectedCharges.-TotalRoomRateAndPackages"));	
		// # expectedTotalTaxesAndFees : 세금 포함 금액	
		vo.setExpectedTotalTaxesAndFees(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:ExpectedCharges.-TotalTaxesAndFees"));	
		// # eTaxInclusive : 세금 포함 여부	
		vo.setETaxInclusive(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:ExpectedCharges.-TaxInclusive"));	
		// # postingDate : 후 결제 날짜	
		vo.setPostingDate(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:ExpectedCharges.hc:ChargesForPostingDate.-PostingDate"));	
		// # startDate : 예약 시작 날짜		
		vo.setStartDate(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:TimeSpan.hc:StartDate"));	
		// # endDate : 예약 종료 날짜	
		vo.setEndDate(CommonUtill.pathMapGetString(infoMap, "r:RoomStays.hc:RoomStay.hc:TimeSpan.hc:EndDate"));
		
		List<Object> counts = (List<Object>)CommonUtill.pathMapGetObj(infoMap,"r:RoomStays.hc:RoomStay.hc:GuestCounts.hc:GuestCount");
		for(Object count : counts) {
			Map<String,Object> countMap =(Map<String,Object>) count;
			// Adult&Child count
			if(countMap.get("-ageQualifyingCode").equals(CommonString.ADULT)) vo.setAdult(countMap.get("-count").toString()) ;
			else if(countMap.get("-ageQualifyingCode").equals(CommonString.CHILD)) vo.setChild(countMap.get("-count").toString()) ;
		}

		// # nameId : 고객 고유 번호	
		vo.setNameId(CommonUtill.pathMapGetString(infoMap, "r:ResGuests.r:ResGuest.r:Profiles.Profile.ProfileIDs.c:UniqueID.#text"));	
		// # nameTitle : 존칭			
		vo.setNameTitle(CommonUtill.pathMapGetString(infoMap, "r:ResGuests.r:ResGuest.r:Profiles.Profile.Customer.PersonName.c:nameTitle"));	
		// # firstName : 이름			
		vo.setFirstName(CommonUtill.pathMapGetString(infoMap, "r:ResGuestsr:ResGuest.r:Profiles.Profile.Customer.PersonName.c:firstName"));	
		// # lastName : 성			
		vo.setLastName(CommonUtill.pathMapGetString(infoMap, "r:ResGuestsr:ResGuest.r:Profiles.Profile.Customer.PersonName.c:lastName")) ;	
		// # addressType : 주소 타입	
		vo.setAddressType(CommonUtill.pathMapGetString(infoMap, "r:ResGuestsr:ResGuest.r:Profiles.Profile.Addresses.NameAddress.-addressType"));	
		// # addressLine : 주소		
		vo.setAddressLine(CommonUtill.pathMapGetString(infoMap, "r:ResGuestsr:ResGuest.r:Profiles.Profile.Addresses.NameAddress.c:AddressLine"));	
		// # cityName : 도 이름		
		vo.setCityName(CommonUtill.pathMapGetString(infoMap, "r:ResGuestsr:ResGuest.r:Profiles.Profile.NameAddress.c:cityName"));	
		// # stateProv : 시 이름		
		vo.setStateProv(CommonUtill.pathMapGetString(infoMap, "r:ResGuestsr:ResGuest.r:Profiles.Profile.NameAddress.c:stateProv"));	
		// # countryCode : 국가 코드	
		vo.setCountryCode(CommonUtill.pathMapGetString(infoMap, "r:ResGuestsr:ResGuest.r:Profiles.Profile.NameAddress.c:countryCode")) ;	
		// # postalCode : 우편 번호	
		vo.setPostalCode(CommonUtill.pathMapGetString(infoMap, "r:ResGuestsr:ResGuest.r:Profiles.Profile.NameAddress.c:postalCode"));	
		
		// # cardType : 카드 타입		
		vo.setCardType(CommonUtill.pathMapGetString(infoMap, "r:ReservationPayments.r:ReservationPaymentInfo.r:CreditCard.-cardType"));	
		// # cardHolderName : 카드 소유자 이름	
		vo.setCardHolderName(CommonUtill.pathMapGetString(infoMap, "r:ReservationPayments.r:ReservationPaymentInfo.r:CreditCard.c:cardHolderName"));	
		// # cardNumber : 카드 번호	
		vo.setCardNumber(CommonUtill.pathMapGetString(infoMap, "r:ReservationPayments.r:ReservationPaymentInfo.r:CreditCard.c:cardNumber"));	
		// # expirationDate : 카드 만료 번호	
		vo.setExpirationDate(CommonUtill.pathMapGetString(infoMap, "r:ReservationPayments.r:ReservationPaymentInfo.r:CreditCard.c:expirationDate"));	
		
		return vo;
	}
	
}
