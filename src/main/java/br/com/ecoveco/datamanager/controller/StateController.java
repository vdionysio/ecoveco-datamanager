package br.com.ecoveco.datamanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoveco.datamanager.controller.dto.StateDto;
import br.com.ecoveco.datamanager.model.State;
import br.com.ecoveco.datamanager.repository.StateRepository;

@RestController
@RequestMapping("/states")
public class StateController {

	private StateRepository stateRepository;
	
	public StateController(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<StateDto>> getAll() {
		List<State> states = stateRepository.findAll();
		return ResponseEntity.ok(StateDto.convertListToDto(states));
	}
}
