package com.jumia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumia.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findAllByPhoneStartsWith(String countrySuffix);

	long countAllByPhoneStartsWith(String countrySuffix);

}
