package br.com.ecoveco.datamanager.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ecoveco.datamanager.controller.dto.LocalityDto;
import br.com.ecoveco.datamanager.controller.form.LocalityForm;
import br.com.ecoveco.datamanager.model.Locality;
import br.com.ecoveco.datamanager.repository.CityRepository;
import br.com.ecoveco.datamanager.repository.LocalityRepository;

@RestController
@RequestMapping("/localities")
public class LocalityController {

	private LocalityRepository localityRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	public LocalityController(LocalityRepository localityRepository) {
		this.localityRepository = localityRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<LocalityDto>> getAll() {
		List<Locality> localities = localityRepository.findAll();
		
		return ResponseEntity.ok(LocalityDto.convertListToDto(localities));
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid LocalityForm localityForm, UriComponentsBuilder uriBuilder) {
		Locality locality = localityForm.convert(cityRepository);
		localityRepository.save(locality);
		URI uri = uriBuilder.path("/localities/{id}").buildAndExpand(locality.getId()).toUri();

		return ResponseEntity.created(uri).body(new LocalityDto(locality));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<Locality> locality = localityRepository.findById(id);
		if (locality.isEmpty()) {
			throw new ObjectNotFoundException(id, "City");
		}
		return ResponseEntity.ok(new LocalityDto(locality.get()));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody @Valid LocalityForm localityForm) {
		Optional<Locality> locality = localityRepository.findById(id);
		if (locality.isEmpty()) {
			throw new ObjectNotFoundException(id, "City");
		}

		localityForm.update(locality.get(), cityRepository);
		return ResponseEntity.ok(new LocalityDto(locality.get()));
	}
}
