package com.jumia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerDTO {
	private long id;
	private String name;
	private String phone;
	private String code;
	private String state;
	private String country;
}