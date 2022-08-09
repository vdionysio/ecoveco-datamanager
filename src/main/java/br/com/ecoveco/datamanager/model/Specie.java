package br.com.ecoveco.datamanager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Specie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "ocurringSpecies")
	private List<BioticParams> occurring;

	@ManyToMany(mappedBy = "dominantSpecies")
	private List<BioticParams> dominant;

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

	public List<BioticParams> getOccurring() {
		return occurring;
	}

	public void setOccurring(List<BioticParams> occurring) {
		this.occurring = occurring;
	}

	public List<BioticParams> getDominant() {
		return dominant;
	}

	public void setDominant(List<BioticParams> dominant) {
		this.dominant = dominant;
	}

}
