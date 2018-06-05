package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.City;
import com.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	CityRepository cityRepository;
	
	
	public Iterable<City> findAll() {
		return cityRepository.findAll();
	}
	public City findOne(int id){
		return cityRepository.findOne(id);
	}
	
	public void save(City task){
		cityRepository.save(task);
	}
	
	public void delete(int id){
		cityRepository.delete(id);
	}
}
