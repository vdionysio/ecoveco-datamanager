package br.com.ecoveco.datamanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BioticAttached {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double macroalgaeCoverage;

	private double macroalgaeDensity;

	private double macroalgaeBiomass;

	private double macroalgaeCanopyHeight;

	private double macrofaunaDensity;

	private double macrofaunaBiomass;

	private double cyanophyceaeCoverage;

	private double cyanophyceaeBiomass;

	@OneToOne(mappedBy = "bioticAttached")
	private BioticSample bioticSample;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMacroalgaeCoverage() {
		return macroalgaeCoverage;
	}

	public void setMacroalgaeCoverage(double macroalgaeCoverage) {
		this.macroalgaeCoverage = macroalgaeCoverage;
	}

	public double getMacroalgaeDensity() {
		return macroalgaeDensity;
	}

	public void setMacroalgaeDensity(double macroalgaeDensity) {
		this.macroalgaeDensity = macroalgaeDensity;
	}

	public double getMacroalgaeBiomass() {
		return macroalgaeBiomass;
	}

	public void setMacroalgaeBiomass(double macroalgaeBiomass) {
		this.macroalgaeBiomass = macroalgaeBiomass;
	}

	public double getMacroalgaeCanopyHeight() {
		return macroalgaeCanopyHeight;
	}

	public void setMacroalgaeCanopyHeight(double macroalgaeCanopyHeight) {
		this.macroalgaeCanopyHeight = macroalgaeCanopyHeight;
	}

	public double getMacrofaunaDensity() {
		return macrofaunaDensity;
	}

	public void setMacrofaunaDensity(double macrofaunaDensity) {
		this.macrofaunaDensity = macrofaunaDensity;
	}

	public double getMacrofaunaBiomass() {
		return macrofaunaBiomass;
	}

	public void setMacrofaunaBiomass(double macrofaunaBiomass) {
		this.macrofaunaBiomass = macrofaunaBiomass;
	}

	public double getCyanophyceaeCoverage() {
		return cyanophyceaeCoverage;
	}

	public void setCyanophyceaeCoverage(double cyanophyceaeCoverage) {
		this.cyanophyceaeCoverage = cyanophyceaeCoverage;
	}

	public double getCyanophyceaeBiomass() {
		return cyanophyceaeBiomass;
	}

	public void setCyanophyceaeBiomass(double cyanophyceaeBiomass) {
		this.cyanophyceaeBiomass = cyanophyceaeBiomass;
	}

	public BioticSample getBioticSample() {
		return bioticSample;
	}

	public void setBioticSample(BioticSample bioticSample) {
		this.bioticSample = bioticSample;
	}

}
