package com.api.ows.profile.model.nameLookup;


import com.api.ows.profile.model.nameLookup.FirstName.FirstNameBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameLookup {
	private String xmlns;
	private LastName LastName;
	private FirstName FirstName;
}
