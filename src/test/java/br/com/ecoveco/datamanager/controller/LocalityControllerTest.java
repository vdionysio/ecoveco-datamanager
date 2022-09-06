package br.com.ecoveco.datamanager.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecoveco.datamanager.controller.form.LocalityForm;
import br.com.ecoveco.datamanager.model.City;
import br.com.ecoveco.datamanager.model.Locality;
import br.com.ecoveco.datamanager.model.State;
import br.com.ecoveco.datamanager.repository.CityRepository;
import br.com.ecoveco.datamanager.repository.LocalityRepository;

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
	void getAll_success() throws Exception {
		List<Locality> localities = new ArrayList<Locality>(Arrays.asList(locality1, locality2, locality3));
		Mockito.when(localityRepository.findAll()).thenReturn(localities);

		mockMvc.perform(MockMvcRequestBuilders.get("/localities").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
			.andExpect(jsonPath("$[1].name", is("Saco do Justino")));
	}

	@Test
	void getById_success() throws Exception {
		Mockito.when(localityRepository.findById(locality1.getId())).thenReturn(Optional.of(locality1));

		mockMvc.perform(MockMvcRequestBuilders.get("/localities/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
			.andExpect(jsonPath("$.name", is("Saco do Mangueira")));
	}

	@Test
	void getById_fail() throws Exception {
		Mockito.when(localityRepository.findById(4L)).thenReturn(Optional.ofNullable(null));

		mockMvc.perform(MockMvcRequestBuilders.get("/localities/4").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(result -> assertTrue(
					result.getResolvedException() instanceof ObjectNotFoundException))
			.andExpect(result -> assertEquals("No row with the given identifier exists: [Locality#4]",
					result.getResolvedException().getMessage()));
	}

	@Test
	void create_success() throws Exception {
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		LocalityForm newLocality = new LocalityForm("Ilha da Pólvora", 1L);
		
		Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(rioGrande));
		Mockito.when(localityRepository.save(newLocality.convert(cityRepository))).thenReturn(null);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/localities")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(newLocality));
		
		mockMvc.perform(mockRequest)
	        .andExpect(status().isCreated())
	        .andExpect(jsonPath("$", notNullValue()))
	        .andExpect(jsonPath("$.name", is("Ilha da Pólvora")));
	}


	@Test
	void edit_success() throws Exception {
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		LocalityForm editedLocality = new LocalityForm("Ilha da Pólvora", 1L);

		Mockito.when(localityRepository.findById(locality1.getId())).thenReturn(Optional.of(locality1));
		Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(rioGrande));
		Mockito.when(localityRepository.save(editedLocality.convert(cityRepository))).thenReturn(null);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/localities/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(editedLocality));
		
		mockMvc.perform(mockRequest)
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", notNullValue()))
	        .andExpect(jsonPath("$.name", is("Ilha da Pólvora")));
	}

	@Test
	void editWithInvalidId_fail() throws Exception {
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		LocalityForm editedLocality = new LocalityForm("Ilha da Pólvora", 1L);

		Mockito.when(localityRepository.findById(5L)).thenReturn(Optional.ofNullable(null));
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/localities/5")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(editedLocality));
		
		mockMvc.perform(mockRequest)
			.andExpect(status().isNotFound())
			.andExpect(result -> assertTrue(
					result.getResolvedException() instanceof ObjectNotFoundException))
			.andExpect(result -> assertEquals("No row with the given identifier exists: [Locality#5]",
					result.getResolvedException().getMessage()));
	}

	@Test
	void deleteById_success() throws Exception {
		Mockito.when(localityRepository.findById(locality3.getId())).thenReturn(Optional.of(locality3));
		doNothing().when(localityRepository).deleteById(locality3.getId());

		mockMvc.perform(MockMvcRequestBuilders
			.delete("/localities/3")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}
	
	@Test
	void deleteById_notFound() throws Exception {
		Mockito.when(localityRepository.findById(4L)).thenReturn(Optional.ofNullable(null));

		mockMvc.perform(MockMvcRequestBuilders
			.delete("/localities/4")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
}
