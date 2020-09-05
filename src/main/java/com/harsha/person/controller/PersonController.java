package com.harsha.person.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harsha.person.entity.Person;
import com.harsha.person.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/getPersons")
	public @ResponseBody ResponseEntity<List<Person>> findAllPersons(){
		System.out.println(" ****** From server 1 ********");
		 List<Person> personList = personService.findAllPersons();
		 return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{personId}", method = RequestMethod.GET)
	public @ResponseBody Person getUser(@PathVariable Long personId) {
		Person person = personService.getPersonById(personId);
		return person;
//	  return new ResponseEntity<Person>(person,HttpStatus.OK);
	}

	@PostMapping(value = "/addPersons", produces =MediaType.APPLICATION_JSON)
	public @ResponseBody Person addPerson(@RequestBody Person person){
		System.out.println(" ****** From server 1 ********");
		 Person savedPerson= personService.savePerson(person);
		 return savedPerson;
//		 return new ResponseEntity<Person>(savedPerson,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{personId}")
	public ResponseEntity<?> deleteUserByID(@PathVariable Long personId) {
		System.out.println("****** From server 1 ********");
		Person person = personService.getPersonById(personId);
	  personService.removePerson(person.getPersonId(),person.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CacheEvict(value = "person", allEntries=true)
	@DeleteMapping("/evict")
	public ResponseEntity<?> clearCache() {
	 return null;
	}
}
