package com.api.ows.profile.model.fetchProfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameID {
	private String type; 		// # type : 고유 Attribute
	private String nodeValue; 	// # nodeValue :  Profile의 고유의 값
	private String xmlns;
}
