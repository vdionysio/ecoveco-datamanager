package br.com.ecoveco.datamanager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class BioticParams {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double meadowCoverage;

	private double maxCanopyHeight1;

	private double maxCanopyHeight2;

	private double maxCanopyHeight3;

	private double maxCanopyHeight5;

	@OneToOne(mappedBy = "bioticParams")
	private BioticSample bioticSample;

	@ManyToMany
	@JoinTable(name = "ocurring_species", joinColumns = @JoinColumn(name = "specie_id"), inverseJoinColumns = @JoinColumn(name = "biotic_params_id"))
	private List<Specie> ocurringSpecies;

	@ManyToMany
	@JoinTable(name = "dominant_species", joinColumns = @JoinColumn(name = "specie_id"), inverseJoinColumns = @JoinColumn(name = "biotic_params_id"))
	private List<Specie> dominantSpecies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMeadowCoverage() {
		return meadowCoverage;
	}

	public void setMeadowCoverage(double meadowCoverage) {
		this.meadowCoverage = meadowCoverage;
	}

	public double getMaxCanopyHeight1() {
		return maxCanopyHeight1;
	}

	public void setMaxCanopyHeight1(double maxCanopyHeight1) {
		this.maxCanopyHeight1 = maxCanopyHeight1;
	}

	public double getMaxCanopyHeight2() {
		return maxCanopyHeight2;
	}

	public void setMaxCanopyHeight2(double maxCanopyHeight2) {
		this.maxCanopyHeight2 = maxCanopyHeight2;
	}

	public double getMaxCanopyHeight3() {
		return maxCanopyHeight3;
	}

	public void setMaxCanopyHeight3(double maxCanopyHeight3) {
		this.maxCanopyHeight3 = maxCanopyHeight3;
	}

	public double getMaxCanopyHeight5() {
		return maxCanopyHeight5;
	}

	public void setMaxCanopyHeight5(double maxCanopyHeight5) {
		this.maxCanopyHeight5 = maxCanopyHeight5;
	}

	public BioticSample getBioticSample() {
		return bioticSample;
	}

	public void setBioticSample(BioticSample bioticSample) {
		this.bioticSample = bioticSample;
	}

	public List<Specie> getOcurringSpecies() {
		return ocurringSpecies;
	}

	public void setOcurringSpecies(List<Specie> ocurringSpecies) {
		this.ocurringSpecies = ocurringSpecies;
	}

	public List<Specie> getDominantSpecies() {
		return dominantSpecies;
	}

	public void setDominantSpecies(List<Specie> dominantSpecies) {
		this.dominantSpecies = dominantSpecies;
	}

}
