	package com.countryservice.demo.controllers;
	
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;
	
	import com.countryservice.demo.beans.Country;
	import com.countryservice.demo.services.CountryService;
	
	@RestController
	public class CountryController {
		
		@Autowired
		CountryService countryservice;
		
		@GetMapping("/getcountries")
		public List<Country> getCountries()
		{
			return countryservice.getAllCountries();
		}
		
		@GetMapping("/getcountries/{id}")
		public ResponseEntity<Country> getCountryById(@PathVariable(value="id")int id)
		{
			try
			{
				Country country=countryservice.getCountrybyID(id);
				return new ResponseEntity<Country>(country,HttpStatus.OK);
			}
			catch(Exception e)
			{
			return 	new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@GetMapping("/getcountries/countryname")
		public ResponseEntity<Country> getCountryByName(@RequestParam(value="name")String countryName)
		{
			try
			{
				Country country=countryservice.getCountrybyName(countryName);
				return new ResponseEntity<Country>(country,HttpStatus.OK);
			}
			catch(Exception e)
			{
			return 	new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@PostMapping("/addcountry")
		public Country addCountry(@RequestBody Country  country)
		{
			return countryservice.addCountry(country);
		}
		
		@PutMapping("/updatecountry/{id}")
		public ResponseEntity<Country> updateCountry(@PathVariable(value="id")int id,@RequestBody Country country)
		{
			try
			{
			Country c1=countryservice.getCountrybyID(id);
			c1.setCountryName(country.getCountryName());
			c1.setCountryCapital(country.getCountryCapital());
			
			Country c2=countryservice.updateCountry(c1);
			return new ResponseEntity<Country>(c2,HttpStatus.OK);
			}
			catch(Exception e)
			{
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			
		}
		
		@DeleteMapping("/deletecountry/{id}")
		public AddResponse deleteCountry(@PathVariable(value="id")int id)
		{
			return countryservice.deleteCountry(id);
		}
	
	}
