package br.com.ecoveco.datamanager.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BioticSample {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Position position;

	private LocalDateTime date;

	@OneToOne
	private BioticAttached bioticAttached;

	@OneToOne
	private BioticParams bioticParams;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BioticAttached getBioticAttached() {
		return bioticAttached;
	}

	public void setBioticAttached(BioticAttached bioticAttached) {
		this.bioticAttached = bioticAttached;
	}

	public BioticParams getBioticParams() {
		return bioticParams;
	}

	public void setBioticParams(BioticParams bioticParams) {
		this.bioticParams = bioticParams;
	}

}
