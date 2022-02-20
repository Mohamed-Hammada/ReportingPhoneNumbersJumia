package com.jumia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jumia.dto.CustomerDTO;
import com.jumia.dto.DataSelected;
import com.jumia.metadata.CountiresMetadataSource;
import com.jumia.service.ReportsService;

@Controller
public class ReportsController {

	@Autowired
	private ReportsService reportsService;

	@Autowired
	CountiresMetadataSource countiresMetadataSource;

	@GetMapping("/")
	public String getData(ModelMap model) {

		return listBooks(model, DataSelected.builder().selectedCountrySuffix("ALL").selectedStatus("ALL").build());
	}

	@PostMapping(value = "/listCustomers")
	public String listBooks(ModelMap model, @ModelAttribute("dataSelected") DataSelected dataSelected) {

		if (dataSelected == null) {
			dataSelected = new DataSelected();
		}

		List<CustomerDTO> customerPage = reportsService.getCustomers(dataSelected.getSelectedCountrySuffix(),
				dataSelected.getSelectedStatus());
		model.addAttribute("countriesMetaDataList", countiresMetadataSource.getCountriesMetaDataList());
		model.addAttribute("customerPage", customerPage);
		model.addAttribute("dataSelected", dataSelected);

		return "index.html";
	}
}
