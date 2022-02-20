package com.jumia.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Customer {
	@Id
	private long id;
	private String name;
	private String phone;
}
