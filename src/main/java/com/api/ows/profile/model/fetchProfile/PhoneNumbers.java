package com.api.ows.profile.model.fetchProfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneNumbers {
	private String phoneType; 		
	private String phoneRole;
	private String phoneNumber; 	
}
