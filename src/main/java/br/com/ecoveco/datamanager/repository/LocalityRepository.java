package br.com.ecoveco.datamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoveco.datamanager.model.Locality;

public interface LocalityRepository extends JpaRepository<Locality, Long>{

}
