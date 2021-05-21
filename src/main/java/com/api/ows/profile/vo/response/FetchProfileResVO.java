package com.api.ows.profile.vo.response;


import java.util.ArrayList;
import java.util.List;

import com.api.ows.profile.model.fetchProfile.PhoneNumbers;
import com.api.ows.profile.model.fetchProfile.Emails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FetchProfileResVO {
	private String nameID;
	private String firstName;
    private String lastName;
	private String nameTitle;
	private String addressLine;
	private String cityName;
	private String countryCode;
	private String postalCode;
	private List<PhoneNumbers> phoneNumbers;
	private List<Emails> emailAddress;
}