package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.City;
import com.repository.CityRepository;
import com.service.CityService;

@RestController
@RequestMapping("city")
public class CityController { 	
	@Autowired
	private CityRepository cityService;
//	lấy tất cả
	@RequestMapping("/getall")
	public List<City> getAll(){
		return (List<City>) cityService.findAll();
	}
	//lấy theo id
	@RequestMapping("/{id}")
	public City getByID(@PathVariable("id") int id) {
		System.out.println("Fetching Id " + id);
		City c = cityService.findOne(id);
		if (c == null) {
			System.out.println("Id " + id + " not found");
		}
		return c;
	}
	@PostMapping("/add")
	public City add(@RequestBody City c) {
		//ID tự tăng ..
		System.out.println("Added:: " + c);
		cityService.save(c);		
		return c;
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<City> deleteByID(@PathVariable("id") int id) {
		City tam=cityService.findOne(id);
		if(tam==null){
	        return ResponseEntity.notFound().build();

		}
		cityService.delete(id);
//		System.out.println("delete id:: " + id);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/update")
	public City update(@RequestBody City c) {
		City tam=cityService.findOne(c.getId());
		if(tam!=null){ //đã tồn tại thì update lại
			System.out.println("update id:: " + c.getId());
			cityService.save(c);		
			return c;
		}
		System.out.println("Not exsit id! ");		
		return tam;
	}

	


}
