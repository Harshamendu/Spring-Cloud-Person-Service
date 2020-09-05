package com.harsha.person.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.harsha.person.Repository.PersonRepo;
import com.harsha.person.entity.Person;
import com.harsha.person.service.PersonService;

@Service("PersonService")
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepo personRepo;
	
	@Override
	public List<Person> findAllPersons() {
		 Iterable<Person> personItr = personRepo.findAll();
		 List<Person> personList = new ArrayList<>();
		 personItr.forEach(s -> personList.add(s));
		if (CollectionUtils.isEmpty(personList))
			return null;
		else
			return personList;
	}

	@Override
	@CachePut(value = "person", key = "#person.personId")
	public Person savePerson(Person person) {
		Person retPerson = personRepo.saveAndFlush(person);
		return retPerson;
	}

	@Override
	@CacheEvict(value = "person", key="#personId")
	public void removePerson(Long personId,Integer id) {
		personRepo.deleteById(id);
		
	}

	@Override
	@Cacheable(value = "person", key = "#personId", unless = "#result.followers < 12000")
	public Person getPersonById(Long personId) {
		System.out.println(" ****** From server 1 ********");
		Optional<Person> optPerson = personRepo.findByPersonId(personId);
		if(optPerson.isPresent())
			return optPerson.get();
		else 
			return null;
	}

	
//	@PostConstruct
//	public void init() {
//		System.out.println("***** in post construct ********");
//		Person p1 = new Person();
//		p1.setFirstName("Aliko");
//		p1.setLastName("Dangote");
//		p1.setAge("25");
//		p1.setSubscriptionType("Anual Subscriber");
//		p1.setPersonId(1L);
//		this.savePerson(p1);
//		
//		Person p2 = new Person();
//		p2.setFirstName("Bill");
//		p2.setLastName("Gates");
//		p2.setAge("52");
//		p2.setSubscriptionType("Monthly Subscriber");
//		p2.setPersonId(2L);
//		this.savePerson(p2);
//	}
	
}
