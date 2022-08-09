package br.com.ecoveco.datamanager.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AbioticSample {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double deepth;

	private double sand;

	private double silt;

	private double clay;

	private double organicMatter;

	private double sedimentationMark;

	private double sedimentationRate;

	private double irradiance;

	private double transparency;

	private double salinity;

	private double waterTemperature;

	private String observation;

	private LocalDateTime date;

	@ManyToOne
	private Position position;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getDeepth() {
		return deepth;
	}

	public void setDeepth(double deepth) {
		this.deepth = deepth;
	}

	public double getSand() {
		return sand;
	}

	public void setSand(double sand) {
		this.sand = sand;
	}

	public double getSilt() {
		return silt;
	}

	public void setSilt(double silt) {
		this.silt = silt;
	}

	public double getClay() {
		return clay;
	}

	public void setClay(double clay) {
		this.clay = clay;
	}

	public double getOrganicMatter() {
		return organicMatter;
	}

	public void setOrganicMatter(double organicMatter) {
		this.organicMatter = organicMatter;
	}

	public double getSedimentationMark() {
		return sedimentationMark;
	}

	public void setSedimentationMark(double sedimentationMark) {
		this.sedimentationMark = sedimentationMark;
	}

	public double getSedimentationRate() {
		return sedimentationRate;
	}

	public void setSedimentationRate(double sedimentationRate) {
		this.sedimentationRate = sedimentationRate;
	}

	public double getIrradiance() {
		return irradiance;
	}

	public void setIrradiance(double irradiance) {
		this.irradiance = irradiance;
	}

	public double getTransparency() {
		return transparency;
	}

	public void setTransparency(double transparency) {
		this.transparency = transparency;
	}

	public double getSalinity() {
		return salinity;
	}

	public void setSalinity(double salinity) {
		this.salinity = salinity;
	}

	public double getWaterTemperature() {
		return waterTemperature;
	}

	public void setWaterTemperature(double waterTemperature) {
		this.waterTemperature = waterTemperature;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
