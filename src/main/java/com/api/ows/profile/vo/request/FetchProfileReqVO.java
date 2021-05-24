package com.api.ows.profile.vo.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchProfileReqVO {
	private String nameID;
}
