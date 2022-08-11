package br.com.ecoveco.datamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoveco.datamanager.model.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
