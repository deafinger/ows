package com.api.ows.profile.model.nameLookup;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailAddress {
	private String xmlns;
	private String nodeValue;
	private EmailAddress EmailAddress;
}
