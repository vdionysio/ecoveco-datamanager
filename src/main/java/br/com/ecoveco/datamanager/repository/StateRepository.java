package br.com.ecoveco.datamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoveco.datamanager.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
