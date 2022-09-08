package br.com.ecoveco.datamanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/positions")
public class PositionController {

	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(null);
	}
}
