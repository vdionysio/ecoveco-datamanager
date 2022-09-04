package br.com.ecoveco.datamanager.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecoveco.datamanager.model.City;
import br.com.ecoveco.datamanager.model.Locality;
import br.com.ecoveco.datamanager.model.State;
import br.com.ecoveco.datamanager.repository.CityRepository;
import br.com.ecoveco.datamanager.repository.LocalityRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
@WebMvcTest(LocalityController.class)
class LocalityControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@MockBean
	LocalityRepository localityRepository;
	@MockBean
	CityRepository cityRepository;

	State rioGrandeDoSul = new State(1L, "Rio Grande do Sul", "RS");
	City rioGrande = new City(1L, "Rio Grande", rioGrandeDoSul);
	Locality locality1 = new Locality(1L, "Saco do Mangueira", rioGrande);
	Locality locality2 = new Locality(2L, "Saco do Justino", rioGrande);
	Locality locality3 = new Locality(3L, "Saída Quitéria", rioGrande);

	@Test
	void testGetAll() throws Exception {
		List<Locality> localities = new ArrayList<Locality>(Arrays.asList(locality1, locality2, locality3));
		Mockito.when(localityRepository.findAll()).thenReturn(localities);
		
		mockMvc.perform(MockMvcRequestBuilders
			.get("/localities")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(3)))
			.andExpect(jsonPath("$[1].name", is("Saco do Justino")));
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	void testEdit() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
