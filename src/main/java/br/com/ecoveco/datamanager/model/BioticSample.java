package br.com.ecoveco.datamanager.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BioticSample {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime date;
	
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
	
	@ManyToOne
	private Position position;
}
