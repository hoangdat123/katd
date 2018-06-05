package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.City;
@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

}
