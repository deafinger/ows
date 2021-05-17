package com.api.ows.profile.vo.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameLookupResVO {
	private String nameId;
	private String firstName;
    private String lastName;
	private String nameTitle;
	private String email;
}
