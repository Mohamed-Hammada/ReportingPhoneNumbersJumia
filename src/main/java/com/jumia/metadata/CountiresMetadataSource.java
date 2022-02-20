package com.jumia.metadata;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.dto.CountriesMetaData;

import lombok.Getter;

@Getter
@Service
public class CountiresMetadataSource {

	private Map<String, CountriesMetaData> countriesMetaDataMap;
	private List<CountriesMetaData> countriesMetaDataList;

	@PostConstruct
	public void loadCountriesMetaDataMap() {
		try {
			countriesMetaDataList = new ObjectMapper().readerForListOf(CountriesMetaData.class)
					.readValue(this.getClass().getResource("/CountriesMetaData.json"));
			countriesMetaDataMap = countriesMetaDataList.stream()
					.collect(Collectors.toMap(CountriesMetaData::getPhoneStartsWith, Function.identity()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}