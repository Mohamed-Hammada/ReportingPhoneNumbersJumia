package com.jumia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountriesMetaData {
	private String name;
	private String code;
	private String phoneStartsWith;
	private String regex;
}
