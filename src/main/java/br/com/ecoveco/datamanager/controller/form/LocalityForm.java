package br.com.ecoveco.datamanager.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.ObjectNotFoundException;

import br.com.ecoveco.datamanager.model.City;
import br.com.ecoveco.datamanager.model.Locality;
import br.com.ecoveco.datamanager.repository.CityRepository;

public class LocalityForm {

	@Size(min = 4, message = "Name must be greater than 4")
	private String name;
	
	@NotNull(message = "cityId must not be null")
	private Long cityId;

	public LocalityForm(String name, Long cityId) {
		this.name = name;
		this.cityId = cityId;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	public Locality convert(CityRepository cityRepository) {
		Optional<City> city = cityRepository.findById(cityId);
		if(city.isEmpty()) {
			throw new ObjectNotFoundException(cityId, "City");			
		}
		return new Locality(name, city.get());
	}
	
	public void update(Locality locality, CityRepository cityRepository) {
		Optional<City> city = cityRepository.findById(cityId);
		if(city.isEmpty()) {
			throw new ObjectNotFoundException(cityId, "City");			
		}
		locality.setCity(city.get());
		locality.setName(name);
	}
}
