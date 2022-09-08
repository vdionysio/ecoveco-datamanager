package br.com.ecoveco.datamanager.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ecoveco.datamanager.model.State;

public class StateDto {

	private String alphaCode;

	private Long id;

	public StateDto(State state) {
		this.alphaCode = state.getAlphaCode();
		this.id = state.getId();
	}

	public String getAlphaCode() {
		return alphaCode;
	}

	public Long getId() {
		return id;
	}

	public static List<StateDto> convertListToDto(List<State> stateList) {
		return stateList.stream().map(s -> new StateDto(s)).collect(Collectors.toList());
	}
}
