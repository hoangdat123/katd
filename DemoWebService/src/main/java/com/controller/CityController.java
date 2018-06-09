package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class CityController  { 	
	@Autowired
	private CityRepository cityService;
//	lấy tất cả
	@RequestMapping("/getall")
	public List<City> getAll(){
		return (List<City>) cityService.findAll();
	}
	//lấy theo id
	@RequestMapping("/{id}")
	public ResponseEntity<Object> getByID(@PathVariable("id") int id) {
		System.out.println("Fetching Id " + id);
		City c = cityService.findOne(id);
		if (c == null) {
			System.out.println("Id " + id + " not found");
			return new ResponseEntity<Object>("Id not found!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(c,HttpStatus.OK);
	}
	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody City c) {
		//ID tự tăng ..
		System.out.println("Added:: " + c);
		cityService.save(c);		
		return new ResponseEntity<Object>("Added Successly:\n"+c,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteByID(@PathVariable("id") int id) {
		City city=cityService.findOne(id);
		if(city==null || city.equals("")){
	        return new ResponseEntity<Object>("Not Found User with ID:" +id,HttpStatus.NOT_FOUND);

		}
		cityService.delete(id);
//		System.out.println("delete id:: " + id);
		return new ResponseEntity<Object>("Deleted Successly!\n",HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody City city) {
		City tam=cityService.findOne(city.getId());
		if(tam!=null){ //đã tồn tại thì update lại
			System.out.println("update id:: " + city.getId());
			cityService.save(city);		
			return new ResponseEntity<Object>("Updated: \n"+city,HttpStatus.OK);
		}
		System.out.println("Not exist id! ");		
		return new ResponseEntity<Object>("Not exist ID!",HttpStatus.NOT_FOUND);
	}

	


}
