package com.jumia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumia.dto.CustomerDTO;
import com.jumia.models.Customer;
import com.jumia.service.ReportsService;
import com.jumia.utiles.CustomerUtiles;

@Service
public class ReportsServiceImplementation implements ReportsService {

	@Autowired
	private CustomerUtiles customerUtiles;

	@Override
	public List<CustomerDTO> getCustomers(String countrySuffix, String state) {
		List<Customer> customer = customerUtiles.getDbCustomers(countrySuffix);
		List<CustomerDTO> cd = customerUtiles.mapToCustomerDTO(customer);
		cd = customerUtiles.filterByState(cd, state);
		return cd;
	}

}
