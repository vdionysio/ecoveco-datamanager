package br.com.ecoveco.datamanager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String alphaCode;

	@OneToMany(mappedBy = "state")
	private List<City> cities;
	
	public State() {
		
	}
	
	public State(Long id, String name, String alphaCode) {
		this.id = id;
		this.name = name;
		this.alphaCode = alphaCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlphaCode() {
		return alphaCode;
	}

	public void setAlphaCode(String alphaCode) {
		this.alphaCode = alphaCode;
	}

	@JsonManagedReference
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
