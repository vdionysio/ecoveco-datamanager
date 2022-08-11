package br.com.ecoveco.datamanager.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ecoveco.datamanager.model.Locality;

public class LocalityDto {

	private String name;
	
	private String cityName;
	
	public LocalityDto(Locality locality) {
		this.name = locality.getName();
		this.cityName = locality.getCity().getName();
	}

	public String getName() {
		return name;
	}

	public String getCityName() {
		return cityName;
	}
	
	public static List<LocalityDto> convertListToDto(List<Locality> localityList) {
		return localityList.stream()
				.map(l -> new LocalityDto(l))
				.collect(Collectors.toList());
	}
}
