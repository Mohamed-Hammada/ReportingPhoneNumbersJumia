package com.jumia.service;

import java.util.List;

import com.jumia.dto.CustomerDTO;

public interface ReportsService {

	List<CustomerDTO> getCustomers(String countrySuffix,String state);
}
