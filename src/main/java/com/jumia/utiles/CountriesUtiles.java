package com.jumia.utiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumia.metadata.CountiresMetadataSource;

@Service
public class CountriesUtiles {

	@Autowired
	CountiresMetadataSource countiresMetadataSource;

}
