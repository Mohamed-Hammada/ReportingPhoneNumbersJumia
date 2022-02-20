package com.jumia.utiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumia.dto.CountriesMetaData;
import com.jumia.dto.CustomerDTO;
import com.jumia.metadata.CountiresMetadataSource;
import com.jumia.models.Customer;
import com.jumia.repository.CustomerRepository;

@Service
public class CustomerUtiles {
	@Autowired
	CountiresMetadataSource countiresMetadataSource;
	@Autowired
	private CustomerRepository customerRepository;

	public List<CustomerDTO> mapToCustomerDTO(List<Customer> customers) {
		return customers.stream().map(c -> {
			CountriesMetaData countriesMetaData = getCountriesMetaDataByPhoneNumber(c.getPhone());
			return CustomerDTO.builder().id(c.getId()).name(c.getName()).code(countriesMetaData.getCode())
					.country(countriesMetaData.getName()).phone(c.getPhone())
					.state(getPhoneNumberStatus(c.getPhone(), countriesMetaData.getRegex())).build();
		}).toList();
	}

	public List<CustomerDTO> filterByState(List<CustomerDTO> cd, String state) {
		if (state.equalsIgnoreCase("ALL"))
			return cd;
		return cd.stream().filter(e -> state.equalsIgnoreCase(e.getState())).toList();
	}

	private CountriesMetaData getCountriesMetaDataByPhoneNumber(String phoneNumber) {
		String key = phoneNumber.substring(0, 5);
		return countiresMetadataSource.getCountriesMetaDataMap().get(key);
	}

	private String getPhoneNumberStatus(String phone, String regex) {
		boolean isValid = phone.matches(regex);
		return isValid ? "Valid" : "Not Valid";
	}

	public List<Customer> getDbCustomers(String countrySuffix) {
		List<Customer> customer = null;

		if (countrySuffix.equalsIgnoreCase("ALL")) {
			customer = customerRepository.findAll();
		} else {
			customer = customerRepository.findAllByPhoneStartsWith(countrySuffix);
		}
		return customer;
	}

}
