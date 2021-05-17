package com.api.ows.profile.model.nameLookup;

import com.api.ows.common.soap.CommonString;
import com.api.ows.profile.vo.request.NameLookupReqVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameLookupBody {
	private String xmlns;
	private NameLookupCriteria NameLookupCriteria;
	
	@Builder
	public NameLookupBody(NameLookupReqVO param) {
		
		this.xmlns = CommonString.NAMEWSDL;
		this.NameLookupCriteria = new NameLookupCriteria().builder()
				.EmailAddress(
						new EmailAddress().builder()
								.xmlns(CommonString.NAME)
								.EmailAddress(
										new EmailAddress().builder()
											.nodeValue(param.getEmailAddress())
											.build()
										)
								.build()
						)
				.build();
		
		
	}
}
